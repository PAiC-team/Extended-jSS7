
package org.restcomm.protocols.ss7.tcap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.TCAPSendException;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.api.tc.component.OperationState;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.TRPseudoState;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TerminationType;
import org.restcomm.protocols.ss7.tcap.asn.AbortSource;
import org.restcomm.protocols.ss7.tcap.asn.AbortSourceType;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextNameImpl;
import org.restcomm.protocols.ss7.tcap.asn.DialogAPDU;
import org.restcomm.protocols.ss7.tcap.asn.DialogAPDUType;
import org.restcomm.protocols.ss7.tcap.asn.DialogAbortAPDU;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU;
import org.restcomm.protocols.ss7.tcap.asn.DialogResponseAPDU;
import org.restcomm.protocols.ss7.tcap.asn.DialogServiceProviderType;
import org.restcomm.protocols.ss7.tcap.asn.DialogServiceUserType;
import org.restcomm.protocols.ss7.tcap.asn.DialogUniAPDU;
import org.restcomm.protocols.ss7.tcap.asn.EncodeException;
import org.restcomm.protocols.ss7.tcap.asn.ErrorCodeImpl;
import org.restcomm.protocols.ss7.tcap.asn.InvokeImpl;
import org.restcomm.protocols.ss7.tcap.asn.OperationCodeImpl;
import org.restcomm.protocols.ss7.tcap.asn.ProblemImpl;
import org.restcomm.protocols.ss7.tcap.asn.Result;
import org.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic;
import org.restcomm.protocols.ss7.tcap.asn.ResultType;
import org.restcomm.protocols.ss7.tcap.asn.ReturnResultImpl;
import org.restcomm.protocols.ss7.tcap.asn.ReturnResultLastImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCAbortMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCBeginMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCContinueMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCEndMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCUniMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;
import org.restcomm.protocols.ss7.tcap.asn.Utils;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.InvokeProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tcap.asn.comp.ProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Reject;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnErrorProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCBeginMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCEndMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.DialogPrimitiveFactoryImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCBeginIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCContinueIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCEndIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCPAbortIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCUniIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCUserAbortIndicationImpl;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class DialogImpl implements Dialog {

    // timeout of remove task after TC_END
    private static final int _REMOVE_TIMEOUT = 30000;

    private static final Logger logger = Logger.getLogger(DialogImpl.class);

    private Object userObject;

    // lock... ech
    protected ReentrantLock dialogLock = new ReentrantLock();

    // values for timer timeouts
    private long removeTaskTimeout = _REMOVE_TIMEOUT;
    private long idleTaskTimeout;

    // sent/received acn, holds last acn/ui.
    private ApplicationContextName lastACN;
    private UserInformation lastUI; // optional

    private Long localTransactionIdObject;
    private long localTransactionId;
    private byte[] remoteTransactionId;
    private Long remoteTransactionIdObject;

    private SccpAddress localAddress;
    private SccpAddress remoteAddress;
    private int localSsn;
    private int remotePc = -1;

    private Future idleTimerFuture;
    private boolean idleTimerActionTaken = false;
    private boolean idleTimerInvoked = false;
    private TRPseudoState state = TRPseudoState.Idle;
    private boolean structured = true;
    // invokde ID space :)
    private static final boolean _INVOKEID_TAKEN = true;
    private static final boolean _INVOKEID_FREE = false;
    private static final int _INVOKE_TABLE_SHIFT = 128;

    private boolean[] invokeIDTable = new boolean[256];
    private int freeCount = invokeIDTable.length;
    private int lastInvokeIdIndex = _INVOKE_TABLE_SHIFT - 1;

    // only originating side keeps FSM, see: Q.771 - 3.1.5
    protected InvokeImpl[] operationsSent = new InvokeImpl[invokeIDTable.length];
    protected InvokeImpl[] operationsSentA = new InvokeImpl[invokeIDTable.length];
    private Set<Long> incomingInvokeList = new HashSet<Long>();
    private ScheduledExecutorService executor;

    // scheduled components list
    private List<Component> scheduledComponentList = new ArrayList<Component>();
    private TCAPProviderImpl provider;

    private int seqControl;

    // If the Dialogue Portion is sent in TCBegin message, the first received
    // Continue message should have the Dialogue Portion too
    private boolean dpSentInBegin = false;

    private boolean previewMode = false;
    protected PreviewDialogData previewDialogData;
    private long startDialogTime;
    private int networkId;

    private Boolean doNotSendProtocolVersion = null;

    private boolean isSwapTcapIdBytes;

    private static int getIndexFromInvokeId(Long l) {
        int tmp = l.intValue();
        return tmp + _INVOKE_TABLE_SHIFT;
    }

    private static Long getInvokeIdFromIndex(int index) {
        int tmp = index - _INVOKE_TABLE_SHIFT;
        return new Long(tmp);
    }

    /**
     * Creating a Dialog for normal mode
     *
     * @param sccpCallingPartyAddress
     * @param sccpCalledPartyAddress
     * @param origTransactionId
     * @param structured
     * @param executor
     * @param provider
     * @param seqControl
     * @param previewMode
     */
    protected DialogImpl(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, Long origTransactionId, boolean structured,
            ScheduledExecutorService executor, TCAPProviderImpl provider, int seqControl, boolean previewMode) {
        super();
        this.localAddress = sccpCallingPartyAddress;
        this.remoteAddress = sccpCalledPartyAddress;
        if (origTransactionId != null) {
            this.localTransactionIdObject = origTransactionId;
            this.localTransactionId = origTransactionId;
        }
        this.executor = executor;
        this.provider = provider;
        this.structured = structured;

        this.seqControl = seqControl;
        this.previewMode = previewMode;

        TCAPStack stack = this.provider.getStack();
        this.idleTaskTimeout = stack.getDialogIdleTimeout();
        this.isSwapTcapIdBytes = stack.getSwapTcapIdBytes();

        startDialogTime = System.currentTimeMillis();

        // start
        startIdleTimer();
    }

    /**
     * Create a Dialog for previewMode
     *
     * @param sccpCallingPartyAddress
     * @param sccpCalledPartyAddress
     * @param seqControl
     * @param executor
     * @param provider
     * @param pdd
     * @param sideB
     */
    protected DialogImpl(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, int seqControl, ScheduledExecutorService executor,
            TCAPProviderImpl provider, PreviewDialogData pdd, boolean sideB) {
        this.localAddress = sccpCallingPartyAddress;
        this.remoteAddress = sccpCalledPartyAddress;
        this.localTransactionIdObject = pdd.getDialogId();
        this.localTransactionId = pdd.getDialogId();
        this.executor = executor;
        this.provider = provider;
        this.structured = true;

        this.seqControl = seqControl;
        this.previewMode = true;

        TCAPStack stack = this.provider.getStack();
        this.idleTaskTimeout = stack.getDialogIdleTimeout();

        this.previewDialogData = pdd;
        this.lastACN = pdd.getLastACN();
        if (sideB) {
            if (pdd.getOperationsSentA() != null)
                this.operationsSent = pdd.getOperationsSentA();
            if (pdd.getOperationsSentB() != null)
                this.operationsSentA = pdd.getOperationsSentB();
        } else {
            if (pdd.getOperationsSentA() != null)
                this.operationsSentA = pdd.getOperationsSentA();
            if (pdd.getOperationsSentB() != null)
                this.operationsSent = pdd.getOperationsSentB();
        }

        for (InvokeImpl invoke : this.operationsSent) {
            if (invoke != null) {
                invoke.setDialog(this);
            }
        }
    }

    @Override
    public Boolean isDoNotSendProtocolVersion() {
        return this.doNotSendProtocolVersion;
    }

    @Override
    public void setDoNotSendProtocolVersion(Boolean isDoNotSendProtocolVersion) {
        this.doNotSendProtocolVersion = isDoNotSendProtocolVersion;
    }

    /**
     * Compute convergent option value as combination from dialog level value
     * and global value specified at stack level.
     *
     * @return
     */
    private boolean doNotSendProtocolVersion() {
        return doNotSendProtocolVersion != null ?
                doNotSendProtocolVersion  :
                provider.getStack().getDoNotSendProtocolVersion();
    }

    public void release() {
        if (!this.previewMode) {
            for (int i = 0; i < this.operationsSent.length; i++) {
                InvokeImpl invokeImpl = this.operationsSent[i];
                if (invokeImpl != null) {
                    invokeImpl.setState(OperationState.Idle);
                    // TODO whether to call operationTimedOut or not is still not clear
                    // operationTimedOut(invokeImpl);
                }
            }
        }

        if (this.isStructured() && this.provider.getStack().getStatisticsEnabled()) {
            long lg = System.currentTimeMillis() - this.startDialogTime;
            this.provider.getStack().getCounterProviderImpl().updateAllDialogsDuration(lg);
        }

        this.setState(TRPseudoState.Expunged);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getDialogId()
     */
    public Long getLocalDialogId() {
        return this.localTransactionIdObject;
    }

    /**
     *
     */
    public Long getRemoteDialogId() {
        if (this.remoteTransactionId != null && this.remoteTransactionIdObject == null) {
            this.remoteTransactionIdObject = Utils.decodeTransactionId(this.remoteTransactionId, isSwapTcapIdBytes);
        }

        return this.remoteTransactionIdObject;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getNewInvokeId()
     */
    public Long getNewInvokeId() throws TCAPException {
        try {
            this.dialogLock.lock();
            if (this.freeCount == 0) {
                throw new TCAPException("No free invokeId");
            }

            int tryCnt = 0;
            while (true) {
                if (++this.lastInvokeIdIndex >= this.invokeIDTable.length)
                    this.lastInvokeIdIndex = 0;
                if (this.invokeIDTable[this.lastInvokeIdIndex] == _INVOKEID_FREE) {
                    freeCount--;
                    this.invokeIDTable[this.lastInvokeIdIndex] = _INVOKEID_TAKEN;
                    return getInvokeIdFromIndex(this.lastInvokeIdIndex);
                }
                if (++tryCnt >= 256)
                    throw new TCAPException("No free invokeId");
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#cancelInvocation (java.lang.Long)
     */
    public boolean cancelInvocation(Long invokeId) throws TCAPException {
        if (this.previewMode)
            return false;

        try {
            this.dialogLock.lock();
            int index = getIndexFromInvokeId(invokeId);
            if (index < 0 || index >= this.operationsSent.length) {
                throw new TCAPException("Wrong invoke id passed.");
            }

            // lookup through send buffer.
            for (index = 0; index < this.scheduledComponentList.size(); index++) {
                Component cr = this.scheduledComponentList.get(index);
                if (cr.getType() == ComponentType.Invoke && cr.getInvokeId().equals(invokeId)) {
                    // lucky
                    // TCInvokeRequestImpl invoke = (TCInvokeRequestImpl) cr;
                    // there is no notification on cancel?
                    this.scheduledComponentList.remove(index);
                    ((InvokeImpl) cr).stopTimer();
                    ((InvokeImpl) cr).setState(OperationState.Idle);
                    return true;
                }
            }

            return false;
        } finally {
            this.dialogLock.unlock();
        }
    }

    private void freeInvokeId(Long invokeId) {
        try {
            this.dialogLock.lock();
            int index = getIndexFromInvokeId(invokeId);
            if (this.invokeIDTable[index] == _INVOKEID_TAKEN)
                this.freeCount++;
            this.invokeIDTable[index] = _INVOKEID_FREE;
        } finally {
            this.dialogLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getRemoteAddress()
     */
    public SccpAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getSsn()
     */
    @Override
    public int getLocalSsn() {
        return this.localSsn;
    }

    public void setLocalSsn(int newSsn) {
        this.localSsn = newSsn;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getLocalAddress()
     */
    public SccpAddress getLocalAddress() {
        return this.localAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#isEstabilished()
     */
    public boolean isEstablished() {
        return this.state == TRPseudoState.Active;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#isStructured()
     */
    public boolean isStructured() {
        return this.structured;
    }

    public void keepAlive() {
        if (this.previewMode)
            return;

        try {
            this.dialogLock.lock();
            if (this.idleTimerInvoked) {
                this.idleTimerActionTaken = true;
            }

        } finally {
            this.dialogLock.unlock();
        }

    }

    @Override
    public ReentrantLock getDialogLock() {
        return this.dialogLock;
    }

    /**
     * @return the acn
     */
    public ApplicationContextName getApplicationContextName() {
        return lastACN;
    }

    /**
     * @return the ui
     */
    public UserInformation getUserInformation() {
        return lastUI;
    }

    /**
     * Adding the new incoming invokeId into incomingInvokeList list
     *
     * @param invokeId
     * @return false: failure - this invokeId already present in the list
     */
    private boolean addIncomingInvokeId(Long invokeId) {
        synchronized (this.incomingInvokeList) {
            if (this.incomingInvokeList.contains(invokeId))
                return false;
            else {
                this.incomingInvokeList.add(invokeId);
                return true;
            }
        }
    }

    private void removeIncomingInvokeId(Long invokeId) {
        synchronized (this.incomingInvokeList) {
            this.incomingInvokeList.remove(invokeId);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.mobicents
     * .protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest)
     */
    public void send(TCBeginRequest tcapBeginRequest) throws TCAPSendException {

        if (this.previewMode)
            return;

        if (this.state != TRPseudoState.Idle) {
            throw new TCAPSendException("Can not send Begin in this state: " + this.state);
        }

        if (!this.isStructured()) {
            throw new TCAPSendException("Unstructured dialogs do not use Begin");
        }
        try {
            this.dialogLock.lock();
            this.idleTimerActionTaken = true;
            restartIdleTimer();
            TCBeginMessageImpl tcapBeginMessage = (TCBeginMessageImpl) TcapFactory.createTCBeginMessage();

            // build DP

            if (tcapBeginRequest.getApplicationContextName() != null) {
                this.dpSentInBegin = true;
                DialogPortion dialogPortion = TcapFactory.createDialogPortion();
                dialogPortion.setUnidirectional(false);
                DialogRequestAPDU dialogRequestAPDU = TcapFactory.createDialogAPDURequest();
                dialogRequestAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
                dialogPortion.setDialogAPDU(dialogRequestAPDU);
                dialogRequestAPDU.setApplicationContextName(tcapBeginRequest.getApplicationContextName());
                this.lastACN = tcapBeginRequest.getApplicationContextName();
                if (tcapBeginRequest.getUserInformation() != null) {
                    dialogRequestAPDU.setUserInformation(tcapBeginRequest.getUserInformation());
                    this.lastUI = tcapBeginRequest.getUserInformation();
                }
                tcapBeginMessage.setDialogPortion(dialogPortion);

                if (this.provider.getStack().getStatisticsEnabled()) {
                    String acn = ((ApplicationContextNameImpl) tcapBeginRequest.getApplicationContextName()).getStringValue();
                    this.provider.getStack().getCounterProviderImpl().updateOutgoingDialogsPerApplicationContextName(acn);
                }
            } else {
                if (this.provider.getStack().getStatisticsEnabled()) {
                    this.provider.getStack().getCounterProviderImpl().updateOutgoingDialogsPerApplicationContextName("");
                }
            }

            // now comps
            tcapBeginMessage.setOriginatingTransactionId(Utils.encodeTransactionId(this.localTransactionId, isSwapTcapIdBytes));
            if (this.scheduledComponentList.size() > 0) {
                Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
                this.prepareComponents(componentsToSend);
                tcapBeginMessage.setComponent(componentsToSend);
            }

            AsnOutputStream aos = new AsnOutputStream();
            try {
                tcapBeginMessage.encode(aos);
                this.setState(TRPseudoState.InitialSent);
                if (this.provider.getStack().getStatisticsEnabled()) {
                    this.provider.getStack().getCounterProviderImpl().updateTcBeginSentCount(this);
                }
                this.provider.send(aos.toByteArray(), tcapBeginRequest.getReturnMessageOnError(), this.remoteAddress, this.localAddress,
                        this.seqControl, this.networkId, this.localSsn, this.remotePc);
                this.scheduledComponentList.clear();
            } catch (Throwable e) {
                // FIXME: remove freshly added invokes to free invoke ID??
                // TODO: should we release this dialog because TC-BEGIN sending has been failed
                if (logger.isEnabledFor(Level.ERROR)) {
                    logger.error("Failed to send message: ", e);
                }
                throw new TCAPSendException("Failed to send TC-Begin message: " + e.getMessage(), e);
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.mobicents
     * .protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest)
     */
    public void send(TCContinueRequest tcapContinueRequest) throws TCAPSendException {

        if (this.previewMode)
            return;

        if (!this.isStructured()) {
            throw new TCAPSendException("Unstructured dialogs do not use Continue");
        }
        try {
            this.dialogLock.lock();
            if (this.state == TRPseudoState.InitialReceived) {
                this.idleTimerActionTaken = true;
                restartIdleTimer();
                TCContinueMessageImpl tcapContinueMessage = (TCContinueMessageImpl) TcapFactory.createTCContinueMessage();

                if (tcapContinueRequest.getApplicationContextName() != null) {

                    // set dialog portion
                    DialogPortion dialogPortion = TcapFactory.createDialogPortion();
                    dialogPortion.setUnidirectional(false);
                    DialogResponseAPDU dialogResponseAPDU = TcapFactory.createDialogAPDUResponse();
                    dialogResponseAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
                    dialogPortion.setDialogAPDU(dialogResponseAPDU);
                    dialogResponseAPDU.setApplicationContextName(tcapContinueRequest.getApplicationContextName());
                    if (tcapContinueRequest.getUserInformation() != null) {
                        dialogResponseAPDU.setUserInformation(tcapContinueRequest.getUserInformation());
                    }
                    // WHERE THE HELL THIS COMES FROM!!!!
                    // WHEN REJECTED IS USED !!!!!
                    Result result = TcapFactory.createResult();
                    result.setResultType(ResultType.Accepted);
                    ResultSourceDiagnostic resultSourceDiagnostic = TcapFactory.createResultSourceDiagnostic();
                    resultSourceDiagnostic.setDialogServiceUserType(DialogServiceUserType.Null);
                    dialogResponseAPDU.setResultSourceDiagnostic(resultSourceDiagnostic);
                    dialogResponseAPDU.setResult(result);
                    tcapContinueMessage.setDialogPortion(dialogPortion);

                }

                tcapContinueMessage.setOriginatingTransactionId(Utils.encodeTransactionId(this.localTransactionId, isSwapTcapIdBytes));
                tcapContinueMessage.setDestinationTransactionId(this.remoteTransactionId);
                if (this.scheduledComponentList.size() > 0) {
                    Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
                    this.prepareComponents(componentsToSend);
                    tcapContinueMessage.setComponent(componentsToSend);

                }
                // local address may change, lets check it;
                if (tcapContinueRequest.getOriginatingAddress() != null && !tcapContinueRequest.getOriginatingAddress().equals(this.localAddress)) {
                    this.localAddress = tcapContinueRequest.getOriginatingAddress();
                }
                AsnOutputStream aos = new AsnOutputStream();
                try {
                    tcapContinueMessage.encode(aos);
                    if (this.provider.getStack().getStatisticsEnabled()) {
                        this.provider.getStack().getCounterProviderImpl().updateTcContinueSentCount(this);
                    }
                    this.provider.send(aos.toByteArray(), tcapContinueRequest.getReturnMessageOnError(), this.remoteAddress,
                            this.localAddress, this.seqControl, this.networkId, this.localSsn, this.remotePc);
                    this.setState(TRPseudoState.Active);
                    this.scheduledComponentList.clear();
                } catch (Exception e) {
                    // FIXME: remove freshly added invokes to free invoke ID??
                    if (logger.isEnabledFor(Level.ERROR)) {
                        logger.error("Failed to send message: ", e);
                    }
                    throw new TCAPSendException("Failed to send TC-Continue message: " + e.getMessage(), e);
                }

            } else if (state == TRPseudoState.Active) {
                this.idleTimerActionTaken = true;
                restartIdleTimer();
                // in this we ignore acn and passed args(except qos)
                TCContinueMessageImpl tcapContinueMessage = (TCContinueMessageImpl) TcapFactory.createTCContinueMessage();

                tcapContinueMessage.setOriginatingTransactionId(Utils.encodeTransactionId(this.localTransactionId, isSwapTcapIdBytes));
                tcapContinueMessage.setDestinationTransactionId(this.remoteTransactionId);
                if (this.scheduledComponentList.size() > 0) {
                    Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
                    this.prepareComponents(componentsToSend);
                    tcapContinueMessage.setComponent(componentsToSend);

                }

                AsnOutputStream aos = new AsnOutputStream();
                try {
                    tcapContinueMessage.encode(aos);
                    this.provider.getStack().getCounterProviderImpl().updateTcContinueSentCount(this);
                    this.provider.send(aos.toByteArray(), tcapContinueRequest.getReturnMessageOnError(), this.remoteAddress,
                            this.localAddress, this.seqControl, this.networkId, this.localSsn, this.remotePc);
                    this.scheduledComponentList.clear();
                } catch (Exception e) {
                    // FIXME: remove freshly added invokes to free invoke ID??
                    if (logger.isEnabledFor(Level.ERROR)) {
                        logger.error("Failed to send message: ", e);
                    }
                    throw new TCAPSendException("Failed to send TC-Continue message: " + e.getMessage(), e);
                }
            } else {
                throw new TCAPSendException("Wrong state: " + this.state);
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.mobicents
     * .protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest)
     */
    public void send(TCEndRequest tcapEndRequest) throws TCAPSendException {

        if (this.previewMode)
            return;

        if (!this.isStructured()) {
            throw new TCAPSendException("Unstructured dialogs do not use End");
        }

        try {
            dialogLock.lock();
            TCEndMessageImpl tcapEndMessage = null;

            if (state == TRPseudoState.InitialReceived) {
                // TC-END request primitive issued in response to a TC-BEGIN
                // indication primitive
                this.idleTimerActionTaken = true;
                stopIdleTimer();

                if (tcapEndRequest.getTerminationType() != TerminationType.Basic)
                    // we do not send TC-END in PreArranged closing case
                    return;

                tcapEndMessage = (TCEndMessageImpl) TcapFactory.createTCEndMessage();
                tcapEndMessage.setDestinationTransactionId(this.remoteTransactionId);

                if (this.scheduledComponentList.size() > 0) {
                    Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
                    this.prepareComponents(componentsToSend);
                    tcapEndMessage.setComponent(componentsToSend);

                }

                ApplicationContextName acn = tcapEndRequest.getApplicationContextName();
                if (acn != null) { // acn & DialogPortion is absent in TCAP V1

                    // set dialog portion
                    DialogPortion dialogPortion = TcapFactory.createDialogPortion();
                    dialogPortion.setUnidirectional(false);
                    DialogResponseAPDU dialogResponseAPDU = TcapFactory.createDialogAPDUResponse();
                    dialogResponseAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
                    dialogPortion.setDialogAPDU(dialogResponseAPDU);

                    dialogResponseAPDU.setApplicationContextName(tcapEndRequest.getApplicationContextName());
                    if (tcapEndRequest.getUserInformation() != null) {
                        dialogResponseAPDU.setUserInformation(tcapEndRequest.getUserInformation());
                    }

                    // WHERE THE HELL THIS COMES FROM!!!!
                    // WHEN REJECTED IS USED !!!!!
                    Result result = TcapFactory.createResult();
                    result.setResultType(ResultType.Accepted);
                    ResultSourceDiagnostic rsd = TcapFactory.createResultSourceDiagnostic();
                    rsd.setDialogServiceUserType(DialogServiceUserType.Null);
                    dialogResponseAPDU.setResultSourceDiagnostic(rsd);
                    dialogResponseAPDU.setResult(result);
                    tcapEndMessage.setDialogPortion(dialogPortion);
                }
                // local address may change, lets check it
                if (tcapEndRequest.getOriginatingAddress() != null && !tcapEndRequest.getOriginatingAddress().equals(this.localAddress)) {
                    this.localAddress = tcapEndRequest.getOriginatingAddress();
                }

            } else if (state == TRPseudoState.Active) {
                restartIdleTimer();

                if (tcapEndRequest.getTerminationType() != TerminationType.Basic)
                    // we do not send TC-END in PreArranged closing case
                    return;

                tcapEndMessage = (TCEndMessageImpl) TcapFactory.createTCEndMessage();

                tcapEndMessage.setDestinationTransactionId(this.remoteTransactionId);
                if (this.scheduledComponentList.size() > 0) {
                    Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
                    this.prepareComponents(componentsToSend);
                    tcapEndMessage.setComponent(componentsToSend);

                }

                // ITU - T Q774 Section 3.2.2.1 Dialogue Control

                // when a dialogue portion is received inopportunely (e.g. a
                // dialogue APDU is received during the active state of a
                // transaction).

                // Don't set the Application Context or Dialogue Portion in
                // Active state

            } else {
                throw new TCAPSendException(String.format("State is not %s or %s: it is %s", TRPseudoState.Active,
                        TRPseudoState.InitialReceived, this.state));
            }

            AsnOutputStream aos = new AsnOutputStream();
            try {
                tcapEndMessage.encode(aos);
                if (this.provider.getStack().getStatisticsEnabled()) {
                    this.provider.getStack().getCounterProviderImpl().updateTcEndSentCount(this);
                }
                this.provider.send(aos.toByteArray(), tcapEndRequest.getReturnMessageOnError(), this.remoteAddress, this.localAddress,
                        this.seqControl, this.networkId, this.localSsn, this.remotePc);

                this.scheduledComponentList.clear();
            } catch (Exception e) {
                // FIXME: remove freshly added invokes to free invoke ID??
                if (logger.isEnabledFor(Level.ERROR)) {
                    logger.error("Failed to send message: ", e);
                }
                throw new TCAPSendException("Failed to send TC-End message: " + e.getMessage(), e);
            } finally {
                // FIXME: is this proper place - should we not release in case
                // of error ?
                release();
            }
        } finally {
            dialogLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#sendUni()
     */
    public void send(TCUniRequest tcapUniRequest) throws TCAPSendException {

        if (this.previewMode)
            return;

        if (this.isStructured()) {
            throw new TCAPSendException("Structured dialogs do not use Uni");
        }

        try {
            this.dialogLock.lock();
            TCUniMessageImpl tcapUniMessage = (TCUniMessageImpl) TcapFactory.createTCUniMessage();

            if (tcapUniRequest.getApplicationContextName() != null) {
                DialogPortion dialogPortion = TcapFactory.createDialogPortion();
                DialogUniAPDU dialogUniAPDU = TcapFactory.createDialogAPDUUni();
                dialogUniAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
                dialogUniAPDU.setApplicationContextName(tcapUniRequest.getApplicationContextName());
                if (tcapUniRequest.getUserInformation() != null) {
                    dialogUniAPDU.setUserInformation(tcapUniRequest.getUserInformation());
                }
                dialogPortion.setUnidirectional(true);
                dialogPortion.setDialogAPDU(dialogUniAPDU);
                tcapUniMessage.setDialogPortion(dialogPortion);

            }

            if (this.scheduledComponentList.size() > 0) {
                Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
                this.prepareComponents(componentsToSend);
                tcapUniMessage.setComponent(componentsToSend);

            }

            AsnOutputStream aos = new AsnOutputStream();
            try {
                tcapUniMessage.encode(aos);
                if (this.provider.getStack().getStatisticsEnabled()) {
                    this.provider.getStack().getCounterProviderImpl().updateTcUniSentCount(this);
                }
                this.provider.send(aos.toByteArray(), tcapUniRequest.getReturnMessageOnError(), this.remoteAddress, this.localAddress,
                        this.seqControl, this.networkId, this.localSsn, this.remotePc);
                this.scheduledComponentList.clear();
            } catch (Exception e) {
                if (logger.isEnabledFor(Level.ERROR)) {
                    logger.error("Failed to send message: ", e);
                }
                throw new TCAPSendException("Failed to send TC-Uni message: " + e.getMessage(), e);
            } finally {
                release();
            }
        } finally {
            this.dialogLock.unlock();
        }
    }

    public void send(TCUserAbortRequest tcapUserAbortRequest) throws TCAPSendException {

        if (this.previewMode)
            return;

        // is abort allowed in "Active" state ?
        if (!isStructured()) {
            throw new TCAPSendException("Unstructured dialog can not be aborted!");
        }

        try {
            this.dialogLock.lock();

            if (this.state == TRPseudoState.InitialReceived || this.state == TRPseudoState.Active) {
                // allowed
                DialogPortion dialogPortion = null;
                if (tcapUserAbortRequest.getUserInformation() != null || tcapUserAbortRequest.getDialogServiceUserType() != null) {
                    // User information can be absent in TCAP V1

                    dialogPortion = TcapFactory.createDialogPortion();
                    dialogPortion.setUnidirectional(false);

                    if (tcapUserAbortRequest.getDialogServiceUserType() != null) {
                        // ITU T Q.774 Read Dialogue end on page 12 and 3.2.2
                        // Abnormal
                        // procedures on page 13 and 14
                        DialogResponseAPDU dialogResponseAPDU = TcapFactory.createDialogAPDUResponse();
                        dialogResponseAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
                        dialogResponseAPDU.setApplicationContextName(tcapUserAbortRequest.getApplicationContextName());
                        dialogResponseAPDU.setUserInformation(tcapUserAbortRequest.getUserInformation());

                        Result result = TcapFactory.createResult();
                        result.setResultType(ResultType.RejectedPermanent);
                        ResultSourceDiagnostic rsd = TcapFactory.createResultSourceDiagnostic();
                        rsd.setDialogServiceUserType(tcapUserAbortRequest.getDialogServiceUserType());
                        dialogResponseAPDU.setResultSourceDiagnostic(rsd);
                        dialogResponseAPDU.setResult(result);
                        dialogPortion.setDialogAPDU(dialogResponseAPDU);
                    } else {
                        // When a BEGIN message has been received (i.e. the
                        // dialogue
                        // is
                        // in the "Initiation Received" state) containing a
                        // Dialogue
                        // Request (AARQ) APDU, the TC-User can abort for any
                        // user
                        // defined reason. In such a situation, the TC-User
                        // issues a
                        // TC-U-ABORT request primitive with the Abort Reason
                        // parameter
                        // absent or with set to any value other than either
                        // "application-context-name-not-supported" or
                        // dialogue-refused". In such a case, a Dialogue Abort (ABRT) APDU is generated with abort-source coded
                        // as "dialogue-service-user",
                        // and supplied as the User Data parameter of the
                        // TR-U-ABORT
                        // request primitive. User information (if any) provided
                        // in
                        // the
                        // TC-U-ABORT request primitive is coded in the
                        // user-information
                        // field of the ABRT APDU.
                        DialogAbortAPDU dialogAbortAPDU = TcapFactory.createDialogAPDUAbort();

                        AbortSource abortSource = TcapFactory.createAbortSource();
                        abortSource.setAbortSourceType(AbortSourceType.User);
                        dialogAbortAPDU.setAbortSource(abortSource);
                        dialogAbortAPDU.setUserInformation(tcapUserAbortRequest.getUserInformation());
                        dialogPortion.setDialogAPDU(dialogAbortAPDU);
                    }
                }

                TCAbortMessageImpl tcapAbortMessage = (TCAbortMessageImpl) TcapFactory.createTCAbortMessage();
                tcapAbortMessage.setDestinationTransactionId(this.remoteTransactionId);
                tcapAbortMessage.setDialogPortion(dialogPortion);

                if (state == TRPseudoState.InitialReceived) {
                    // local address may change, lets check it
                    if (tcapUserAbortRequest.getOriginatingAddress() != null && !tcapUserAbortRequest.getOriginatingAddress().equals(this.localAddress)) {
                        this.localAddress = tcapUserAbortRequest.getOriginatingAddress();
                    }
                }

                // no components
                AsnOutputStream aos = new AsnOutputStream();
                try {
                    tcapAbortMessage.encode(aos);
                    if (this.provider.getStack().getStatisticsEnabled()) {
                        this.provider.getStack().getCounterProviderImpl().updateTcUserAbortSentCount(this);
                    }
                    this.provider.send(aos.toByteArray(), tcapUserAbortRequest.getReturnMessageOnError(), this.remoteAddress,
                            this.localAddress, this.seqControl, this.networkId, this.localSsn, this.remotePc);

                    this.scheduledComponentList.clear();
                } catch (Exception e) {
                    // FIXME: remove freshly added invokes to free invoke ID??
                    if (logger.isEnabledFor(Level.ERROR)) {
                        e.printStackTrace();
                        logger.error("Failed to send message: ", e);
                    }
                    throw new TCAPSendException("Failed to send TC-U-Abort message: " + e.getMessage(), e);
                } finally {
                    release();
                }
            } else if (this.state == TRPseudoState.InitialSent) {
                release();
            }
        } finally {
            this.dialogLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#sendComponent(org
     * .mobicents.protocols.ss7.tcap.api.tc.component.ComponentRequest)
     */
    public void sendComponent(Component componentRequest) throws TCAPSendException {

        if (this.previewMode)
            return;

        if (this.provider.getStack().getStatisticsEnabled()) {
            switch (componentRequest.getType()) {
                case Invoke:
                    this.provider.getStack().getCounterProviderImpl().updateInvokeSentCount(this, (Invoke) componentRequest);

                    Invoke invoke = (Invoke) componentRequest;
                    OperationCodeImpl operationCode = (OperationCodeImpl) invoke.getOperationCode();
                    if (operationCode != null) {
                        this.provider.getStack().getCounterProviderImpl()
                                .updateOutgoingInvokesPerOperationCode(operationCode.getStringValue());
                    }
                    break;
                case ReturnResult:
                    this.provider.getStack().getCounterProviderImpl().updateReturnResultSentCount(this);
                    break;
                case ReturnResultLast:
                    this.provider.getStack().getCounterProviderImpl().updateReturnResultLastSentCount(this);
                    break;
                case ReturnError:
                    this.provider.getStack().getCounterProviderImpl().updateReturnErrorSentCount(this);

                    ReturnError re = (ReturnError) componentRequest;
                    ErrorCodeImpl ec = (ErrorCodeImpl) re.getErrorCode();
                    if (ec != null) {
                        this.provider.getStack().getCounterProviderImpl().updateOutgoingErrorsPerErrorCode(ec.getStringValue());
                    }
                    break;
                case Reject:
                    this.provider.getStack().getCounterProviderImpl().updateRejectSentCount(this);

                    Reject reject = (Reject) componentRequest;
                    ProblemImpl problem = (ProblemImpl) reject.getProblem();
                    if (problem != null) {
                        this.provider.getStack().getCounterProviderImpl().updateOutgoingRejectPerProblem(problem.getStringValue());
                    }
                    break;
            }
        }

        try {
            this.dialogLock.lock();
            if (componentRequest.getType() == ComponentType.Invoke) {
                InvokeImpl invoke = (InvokeImpl) componentRequest;

                // check if its taken!
                int invokeIndex = DialogImpl.getIndexFromInvokeId(invoke.getInvokeId());
                if (this.operationsSent[invokeIndex] != null) {
                    throw new TCAPSendException("There is already operation with such invoke id!");
                }

                invoke.setState(OperationState.Pending);
                invoke.setDialog(this);

                // if the Invoke timeout value has not be reset by TCAP-User
                // for this invocation we are setting it to the the TCAP stack
                // default value
                if (invoke.getTimeout() == TCAPStackImpl._EMPTY_INVOKE_TIMEOUT)
                    invoke.setTimeout(this.provider.getStack().getInvokeTimeout());
            } else {
                if (componentRequest.getType() != ComponentType.ReturnResult) {
                    // we are sending a response and removing invokeId from
                    // incomingInvokeList
                    this.removeIncomingInvokeId(componentRequest.getInvokeId());
                }
            }
            this.scheduledComponentList.add(componentRequest);
        } finally {
            this.dialogLock.unlock();
        }
    }

    public void processInvokeWithoutAnswer(Long invokeId) {
        if (this.previewMode)
            return;
        this.removeIncomingInvokeId(invokeId);
    }

    private void prepareComponents(Component[] components) {
        int index = 0;
        while (this.scheduledComponentList.size() > index) {
            Component component = this.scheduledComponentList.get(index);
            if (component.getType() == ComponentType.Invoke) {
                InvokeImpl in = (InvokeImpl) component;
                // FIXME: check not null?
                this.operationsSent[this.getIndexFromInvokeId(in.getInvokeId())] = in;
                in.setState(OperationState.Sent);
            }

            components[index++] = component;
        }
    }

    public int getMaxUserDataLength() {
        return this.provider.getMaxUserDataLength(remoteAddress, localAddress, this.networkId);
    }

    public int getDataLength(TCBeginRequest tcapBeginRequest) throws TCAPSendException {
        TCBeginMessageImpl tcapBeginMessage = (TCBeginMessageImpl) TcapFactory.createTCBeginMessage();

        if (tcapBeginRequest.getApplicationContextName() != null) {
            DialogPortion dialogPortion = TcapFactory.createDialogPortion();
            dialogPortion.setUnidirectional(false);
            DialogRequestAPDU dialogRequestAPDU = TcapFactory.createDialogAPDURequest();
            dialogRequestAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
            dialogPortion.setDialogAPDU(dialogRequestAPDU);
            dialogRequestAPDU.setApplicationContextName(tcapBeginRequest.getApplicationContextName());
            if (tcapBeginRequest.getUserInformation() != null) {
                dialogRequestAPDU.setUserInformation(tcapBeginRequest.getUserInformation());
            }
            tcapBeginMessage.setDialogPortion(dialogPortion);
        }

        // now comps
        tcapBeginMessage.setOriginatingTransactionId(Utils.encodeTransactionId(this.localTransactionId, isSwapTcapIdBytes));
        if (this.scheduledComponentList.size() > 0) {
            Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
            for (int index = 0; index < this.scheduledComponentList.size(); index++) {
                componentsToSend[index] = this.scheduledComponentList.get(index);
            }
            tcapBeginMessage.setComponent(componentsToSend);
        }

        AsnOutputStream aos = new AsnOutputStream();
        try {
            tcapBeginMessage.encode(aos);
        } catch (EncodeException e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed to encode message while length testing: ", e);
            }
            throw new TCAPSendException("Error encoding TCBeginRequest", e);
        }
        return aos.size();
    }

    public int getDataLength(TCContinueRequest tcapContinueRequest) throws TCAPSendException {

        TCContinueMessageImpl tcapContinueMessage = (TCContinueMessageImpl) TcapFactory.createTCContinueMessage();

        if (tcapContinueRequest.getApplicationContextName() != null) {

            // set dialog portion
            DialogPortion dialogPortion = TcapFactory.createDialogPortion();
            dialogPortion.setUnidirectional(false);
            DialogResponseAPDU dialogResponseAPDU = TcapFactory.createDialogAPDUResponse();
            dialogResponseAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
            dialogPortion.setDialogAPDU(dialogResponseAPDU);
            dialogResponseAPDU.setApplicationContextName(tcapContinueRequest.getApplicationContextName());
            if (tcapContinueRequest.getUserInformation() != null) {
                dialogResponseAPDU.setUserInformation(tcapContinueRequest.getUserInformation());
            }
            // WHERE THE HELL THIS COMES FROM!!!!
            // WHEN REJECTED IS USED !!!!!
            Result res = TcapFactory.createResult();
            res.setResultType(ResultType.Accepted);
            ResultSourceDiagnostic rsd = TcapFactory.createResultSourceDiagnostic();
            rsd.setDialogServiceUserType(DialogServiceUserType.Null);
            dialogResponseAPDU.setResultSourceDiagnostic(rsd);
            dialogResponseAPDU.setResult(res);
            tcapContinueMessage.setDialogPortion(dialogPortion);

        }

        tcapContinueMessage.setOriginatingTransactionId(Utils.encodeTransactionId(this.localTransactionId, isSwapTcapIdBytes));
        tcapContinueMessage.setDestinationTransactionId(this.remoteTransactionId);
        if (this.scheduledComponentList.size() > 0) {
            Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
            for (int index = 0; index < this.scheduledComponentList.size(); index++) {
                componentsToSend[index] = this.scheduledComponentList.get(index);
            }
            tcapContinueMessage.setComponent(componentsToSend);
        }

        AsnOutputStream aos = new AsnOutputStream();
        try {
            tcapContinueMessage.encode(aos);
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed to encode message while length testing: ", e);
            }
            throw new TCAPSendException("Error encoding TCContinueRequest", e);
        }

        return aos.size();
    }

    public int getDataLength(TCEndRequest tcapEndRequest) throws TCAPSendException {

        // TC-END request primitive issued in response to a TC-BEGIN
        // indication primitive
        TCEndMessageImpl tcapEndMessage = (TCEndMessageImpl) TcapFactory.createTCEndMessage();
        tcapEndMessage.setDestinationTransactionId(this.remoteTransactionId);

        if (this.scheduledComponentList.size() > 0) {
            Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
            for (int index = 0; index < this.scheduledComponentList.size(); index++) {
                componentsToSend[index] = this.scheduledComponentList.get(index);
            }
            tcapEndMessage.setComponent(componentsToSend);
        }

        if (state == TRPseudoState.InitialReceived) {
            ApplicationContextName acn = tcapEndRequest.getApplicationContextName();
            if (acn != null) { // acn & DialogPortion is absent in TCAP V1

                // set dialog portion
                DialogPortion dialogPortion = TcapFactory.createDialogPortion();
                dialogPortion.setUnidirectional(false);
                DialogResponseAPDU dialogResponseAPDU = TcapFactory.createDialogAPDUResponse();
                dialogResponseAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
                dialogPortion.setDialogAPDU(dialogResponseAPDU);

                dialogResponseAPDU.setApplicationContextName(tcapEndRequest.getApplicationContextName());
                if (tcapEndRequest.getUserInformation() != null) {
                    dialogResponseAPDU.setUserInformation(tcapEndRequest.getUserInformation());
                }

                // WHERE THE HELL THIS COMES FROM!!!!
                // WHEN REJECTED IS USED !!!!!
                Result result = TcapFactory.createResult();
                result.setResultType(ResultType.Accepted);
                ResultSourceDiagnostic resultSourceDiagnostic = TcapFactory.createResultSourceDiagnostic();
                resultSourceDiagnostic.setDialogServiceUserType(DialogServiceUserType.Null);
                dialogResponseAPDU.setResultSourceDiagnostic(resultSourceDiagnostic);
                dialogResponseAPDU.setResult(result);
                tcapEndMessage.setDialogPortion(dialogPortion);
            }
        }

        AsnOutputStream aos = new AsnOutputStream();
        try {
            tcapEndMessage.encode(aos);
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed to encode message while length testing: ", e);
            }
            throw new TCAPSendException("Error encoding TCEndRequest", e);
        }

        return aos.size();
    }

    public int getDataLength(TCUniRequest tcapUniRequest) throws TCAPSendException {

        TCUniMessageImpl tcapUniMessage = (TCUniMessageImpl) TcapFactory.createTCUniMessage();

        if (tcapUniRequest.getApplicationContextName() != null) {
            DialogPortion dialogPortion = TcapFactory.createDialogPortion();
            DialogUniAPDU dialogUniAPDU = TcapFactory.createDialogAPDUUni();
            dialogUniAPDU.setDoNotSendProtocolVersion(doNotSendProtocolVersion());
            dialogUniAPDU.setApplicationContextName(tcapUniRequest.getApplicationContextName());
            if (tcapUniRequest.getUserInformation() != null) {
                dialogUniAPDU.setUserInformation(tcapUniRequest.getUserInformation());
            }
            dialogPortion.setUnidirectional(true);
            dialogPortion.setDialogAPDU(dialogUniAPDU);
            tcapUniMessage.setDialogPortion(dialogPortion);

        }

        if (this.scheduledComponentList.size() > 0) {
            Component[] componentsToSend = new Component[this.scheduledComponentList.size()];
            for (int index = 0; index < this.scheduledComponentList.size(); index++) {
                componentsToSend[index] = this.scheduledComponentList.get(index);
            }
            tcapUniMessage.setComponent(componentsToSend);

        }

        AsnOutputStream aos = new AsnOutputStream();
        try {
            tcapUniMessage.encode(aos);
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed to encode message while length testing: ", e);
            }
            throw new TCAPSendException("Error encoding TCUniRequest", e);
        }

        return aos.size();
    }

    // /////////////////
    // LOCAL METHODS //
    // ///////////////

    /**
     * @param remoteTransactionId the remoteTransactionId to set
     */
    void setRemoteTransactionId(byte[] remoteTransactionId) {
        this.remoteTransactionId = remoteTransactionId;
    }

    /**
     * @param sccpCallingPartyAddress the localAddress to set
     */
    public void setLocalAddress(SccpAddress sccpCallingPartyAddress) {
        this.localAddress = sccpCallingPartyAddress;
    }

    /**
     * @param sccpCalledPartyAddress the remoteAddress to set
     */
    public void setRemoteAddress(SccpAddress sccpCalledPartyAddress) {
        this.remoteAddress = sccpCalledPartyAddress;
    }

    void processUni(TCUniMessage tcapUniMessage, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) {

        try {
            this.dialogLock.lock();

            try {
                this.setRemoteAddress(sccpCalledPartyAddress);
                this.setLocalAddress(sccpCallingPartyAddress);

                // no dialog portion!
                // convert to indications
                TCUniIndicationImpl tcapUniIndication = (TCUniIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                        .getDialogPrimitiveFactory()).createUniIndication(this);

                tcapUniIndication.setDestinationAddress(sccpCallingPartyAddress);
                tcapUniIndication.setOriginatingAddress(sccpCalledPartyAddress);
                // now comps
                Component[] comps = tcapUniMessage.getComponent();
                tcapUniIndication.setComponents(comps);

                processRcvdComp(comps);

                if (tcapUniMessage.getDialogPortion() != null) {
                    // it should be dialog req?
                    DialogPortion dialogPortion = tcapUniMessage.getDialogPortion();
                    DialogUniAPDU dialogUniAPDU = (DialogUniAPDU) dialogPortion.getDialogAPDU();
                    this.lastACN = dialogUniAPDU.getApplicationContextName();
                    this.lastUI = dialogUniAPDU.getUserInformation();
                    tcapUniIndication.setApplicationContextName(this.lastACN);
                    tcapUniIndication.setUserInformation(this.lastUI);
                }

                // lets deliver to provider, this MUST not throw anything
                this.provider.deliver(this, tcapUniIndication);

            } finally {
                this.release();
            }
        } finally {
            this.dialogLock.unlock();
        }
    }

    private void processRcvdComp(Component[] comps) {
        if (this.provider.getStack().getStatisticsEnabled()) {
            for (Component comp : comps) {
                switch (comp.getType()) {
                    case Invoke:
                        this.provider.getStack().getCounterProviderImpl().updateInvokeReceivedCount(this, (Invoke) comp);

                        Invoke inv = (Invoke) comp;
                        OperationCodeImpl oc = (OperationCodeImpl) inv.getOperationCode();
                        if (oc != null) {
                            this.provider.getStack().getCounterProviderImpl()
                                    .updateIncomingInvokesPerOperationCode(oc.getStringValue());
                        }
                        break;
                    case ReturnResult:
                        this.provider.getStack().getCounterProviderImpl().updateReturnResultReceivedCount(this);
                        break;
                    case ReturnResultLast:
                        this.provider.getStack().getCounterProviderImpl().updateReturnResultLastReceivedCount(this);
                        break;
                    case ReturnError:
                        this.provider.getStack().getCounterProviderImpl().updateReturnErrorReceivedCount(this);

                        ReturnError re = (ReturnError) comp;
                        ErrorCodeImpl ec = (ErrorCodeImpl) re.getErrorCode();
                        if (ec != null) {
                            this.provider.getStack().getCounterProviderImpl()
                                    .updateIncomingErrorsPerErrorCode(ec.getStringValue());
                        }
                        break;
                    case Reject:
                        Reject rej = (Reject) comp;
                        if (!rej.isLocalOriginated()) {
                            this.provider.getStack().getCounterProviderImpl().updateRejectReceivedCount(this);

                            ProblemImpl prob = (ProblemImpl) rej.getProblem();
                            if (prob != null) {
                                this.provider.getStack().getCounterProviderImpl()
                                        .updateIncomingRejectPerProblem(prob.getStringValue());
                            }
                        }
                        break;
                }
            }
        }
    }

    protected void processBegin(TCBeginMessage msg, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) {

        TCBeginIndicationImpl tcapBeginIndication;
        try {
            this.dialogLock.lock();

            if (!this.previewMode) {
                // this is invoked ONLY for server.
                if (state != TRPseudoState.Idle) {
                    // should we terminate dialog here?
                    if (logger.isEnabledFor(Level.ERROR)) {
                        logger.error("Received Begin primitive, but state is not: " + TRPseudoState.Idle + ". Dialog: " + this);
                    }
                    this.sendAbnormalDialog();
                    return;
                }
                restartIdleTimer();
            }

            // lets setup
            this.setRemoteAddress(sccpCalledPartyAddress);
            this.setLocalAddress(sccpCallingPartyAddress);
            this.setRemoteTransactionId(msg.getOriginatingTransactionId());
            // convert to indications
            tcapBeginIndication = (TCBeginIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider.getDialogPrimitiveFactory())
                    .createBeginIndication(this);

            tcapBeginIndication.setDestinationAddress(sccpCallingPartyAddress);
            tcapBeginIndication.setOriginatingAddress(sccpCalledPartyAddress);

            // if APDU and context data present, lets store it
            DialogPortion dialogPortion = msg.getDialogPortion();

            if (dialogPortion != null) {
                // this should not be null....
                DialogAPDU apdu = dialogPortion.getDialogAPDU();
                if (apdu.getType() != DialogAPDUType.Request) {
                    if (logger.isEnabledFor(Level.ERROR)) {
                        logger.error("Received non-Request APDU: " + apdu.getType() + ". Dialog: " + this);
                    }
                    this.sendAbnormalDialog();
                    return;
                }
                DialogRequestAPDU requestAPDU = (DialogRequestAPDU) apdu;
                this.lastACN = requestAPDU.getApplicationContextName();
                this.lastUI = requestAPDU.getUserInformation();
                tcapBeginIndication.setApplicationContextName(this.lastACN);
                tcapBeginIndication.setUserInformation(this.lastUI);
            }
            if (this.provider.getStack().getStatisticsEnabled()) {
                if (tcapBeginIndication.getApplicationContextName() != null) {
                    String acn = ((ApplicationContextNameImpl) tcapBeginIndication.getApplicationContextName()).getStringValue();
                    this.provider.getStack().getCounterProviderImpl().updateIncomingDialogsPerApplicationContextName(acn);
                } else {
                    this.provider.getStack().getCounterProviderImpl().updateIncomingDialogsPerApplicationContextName("");
                }
            }

            tcapBeginIndication.setComponents(processOperationsState(msg.getComponent()));
            if (!this.previewMode) {
                // change state - before we deliver
                this.setState(TRPseudoState.InitialReceived);
            }

            // lets deliver to provider
            this.provider.deliver(this, tcapBeginIndication);

        } finally {
            this.dialogLock.unlock();
        }
    }

    protected void processContinue(TCContinueMessage tcapContinueMessage, SccpAddress localAddress, SccpAddress sccpCalledPartyAddress) {

        TCContinueIndicationImpl tcContinueIndication;
        try {
            this.dialogLock.lock();

            if (this.previewMode) {
                tcContinueIndication = (TCContinueIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                        .getDialogPrimitiveFactory()).createContinueIndication(this);
                this.setRemoteTransactionId(tcapContinueMessage.getOriginatingTransactionId());

                // here we will receive DialogResponse APDU - if request was
                // present!
                DialogPortion dialogPortion = tcapContinueMessage.getDialogPortion();
                if (dialogPortion != null) {
                    // this should not be null....
                    DialogAPDU apdu = dialogPortion.getDialogAPDU();
                    if (apdu.getType() == DialogAPDUType.Response) {
                        DialogResponseAPDU responseAPDU = (DialogResponseAPDU) apdu;
                        // this will be present if APDU is present.
                        if (!responseAPDU.getApplicationContextName().equals(this.lastACN)) {
                            this.lastACN = responseAPDU.getApplicationContextName();
                        }
                        if (responseAPDU.getUserInformation() != null) {
                            this.lastUI = responseAPDU.getUserInformation();
                        }
                        tcContinueIndication.setApplicationContextName(responseAPDU.getApplicationContextName());
                        tcContinueIndication.setUserInformation(responseAPDU.getUserInformation());
                    }
                }
                tcContinueIndication.setOriginatingAddress(sccpCalledPartyAddress);
                // now comps
                tcContinueIndication.setComponents(processOperationsState(tcapContinueMessage.getComponent()));

                // lets deliver to provider
                this.provider.deliver(this, tcContinueIndication);
            } else {

                if (state == TRPseudoState.InitialSent) {
                    restartIdleTimer();
                    tcContinueIndication = (TCContinueIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                            .getDialogPrimitiveFactory()).createContinueIndication(this);

                    // in continue remote address MAY change be changed, so lets
                    // update!
                    this.setRemoteAddress(sccpCalledPartyAddress);
                    this.setRemoteTransactionId(tcapContinueMessage.getOriginatingTransactionId());
                    tcContinueIndication.setOriginatingAddress(sccpCalledPartyAddress);

                    // here we will receive DialogResponse APDU - if request was
                    // present!
                    DialogPortion dialogPortion = tcapContinueMessage.getDialogPortion();
                    if (dialogPortion != null) {
                        // this should not be null....
                        DialogAPDU apdu = dialogPortion.getDialogAPDU();
                        if (apdu.getType() != DialogAPDUType.Response) {
                            if (logger.isEnabledFor(Level.ERROR)) {
                                logger.error("Received non-Response APDU: " + apdu.getType() + ". Dialog: " + this);
                            }
                            this.sendAbnormalDialog();
                            return;
                        }
                        DialogResponseAPDU responseAPDU = (DialogResponseAPDU) apdu;
                        // this will be present if APDU is present.
                        if (!responseAPDU.getApplicationContextName().equals(this.lastACN)) {
                            this.lastACN = responseAPDU.getApplicationContextName();
                        }
                        if (responseAPDU.getUserInformation() != null) {
                            this.lastUI = responseAPDU.getUserInformation();
                        }
                        tcContinueIndication.setApplicationContextName(responseAPDU.getApplicationContextName());
                        tcContinueIndication.setUserInformation(responseAPDU.getUserInformation());
                    } else if (this.dpSentInBegin) {
                        // ITU - T Q.774 3.2.2 : Abnormal procedure page 13

                        // when a dialogue portion is missing when its presence
                        // is
                        // mandatory (e.g. an AARQ APDU was sent in a Begin
                        // message,
                        // but
                        // no AARE APDU was received in the first backward
                        // Continue
                        // message) or when a dialogue portion is received
                        // inopportunely
                        // (e.g. a dialogue APDU is received during the active
                        // state
                        // of
                        // a transaction). At the side where the abnormality is
                        // detected, a TC-P-ABORT indication primitive is issued
                        // to
                        // the
                        // local TC-user with the "P-Abort" parameter in the
                        // primitive
                        // set to "abnormal dialogue". At the same time, a
                        // TR-U-ABORT
                        // request primitive is issued to the transaction
                        // sub-layer
                        // with
                        // an ABRT APDU as user data. The abort-source field of
                        // the
                        // ABRT
                        // APDU is set to "dialogue-service-provider" and the
                        // user
                        // information field is absent.

                        sendAbnormalDialog();
                        return;

                    }
                    tcContinueIndication.setOriginatingAddress(sccpCalledPartyAddress);
                    // now comps
                    tcContinueIndication.setComponents(processOperationsState(tcapContinueMessage.getComponent()));
                    // change state
                    this.setState(TRPseudoState.Active);

                    // lets deliver to provider
                    this.provider.deliver(this, tcContinueIndication);

                } else if (state == TRPseudoState.Active) {
                    restartIdleTimer();
                    // XXX: here NO APDU will be present, hence, no ACN/UI change
                    tcContinueIndication = (TCContinueIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                            .getDialogPrimitiveFactory()).createContinueIndication(this);

                    tcContinueIndication.setOriginatingAddress(sccpCalledPartyAddress);

                    // now comps
                    tcContinueIndication.setComponents(processOperationsState(tcapContinueMessage.getComponent()));

                    // lets deliver to provider
                    this.provider.deliver(this, tcContinueIndication);

                } else {
                    if (logger.isEnabledFor(Level.ERROR)) {
                        logger.error("Received Continue primitive, but state is not proper: " + this.state + ", Dialog: " + this);
                    }
                    this.sendAbnormalDialog();
                    return;
                }
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    protected void processEnd(TCEndMessage tcapEndMessage, SccpAddress localAddress, SccpAddress sccpCalledPartyAddress) {
        TCEndIndicationImpl tcEndIndication = null;
        try {
            this.dialogLock.lock();

            try {
                if (this.previewMode) {
                    tcEndIndication = (TCEndIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                            .getDialogPrimitiveFactory()).createEndIndication(this);

                    DialogPortion dialogPortion = tcapEndMessage.getDialogPortion();
                    if (dialogPortion != null) {
                        DialogAPDU apdu = dialogPortion.getDialogAPDU();
                        if (apdu.getType() == DialogAPDUType.Response) {
                            DialogResponseAPDU responseAPDU = (DialogResponseAPDU) apdu;
                            // this will be present if APDU is present.
                            if (!responseAPDU.getApplicationContextName().equals(this.lastACN)) {
                                this.lastACN = responseAPDU.getApplicationContextName();
                            }
                            if (responseAPDU.getUserInformation() != null) {
                                this.lastUI = responseAPDU.getUserInformation();
                            }
                            tcEndIndication.setApplicationContextName(responseAPDU.getApplicationContextName());
                            tcEndIndication.setUserInformation(responseAPDU.getUserInformation());
                        }
                    }
                    // now comps
                    tcEndIndication.setComponents(processOperationsState(tcapEndMessage.getComponent()));

                    this.provider.deliver(this, tcEndIndication);
                } else {
                    restartIdleTimer();
                    tcEndIndication = (TCEndIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                            .getDialogPrimitiveFactory()).createEndIndication(this);

                    if (state == TRPseudoState.InitialSent) {
                        // in end remote address MAY change be changed, so lets
                        // update!
                        this.setRemoteAddress(sccpCalledPartyAddress);
                        tcEndIndication.setOriginatingAddress(sccpCalledPartyAddress);
                    }

                    DialogPortion dialogPortion = tcapEndMessage.getDialogPortion();
                    if (dialogPortion != null) {
                        DialogAPDU dialogAPDU = dialogPortion.getDialogAPDU();
                        if (dialogAPDU.getType() != DialogAPDUType.Response) {
                            if (logger.isEnabledFor(Level.ERROR)) {
                                logger.error("Received non-Response APDU: " + dialogAPDU.getType() + ". Dialog: " + this);
                            }
                            // we do not send "this.sendAbnormalDialog()"
                            // because no sense to send an answer to TC-END
                            return;
                        }
                        DialogResponseAPDU responseAPDU = (DialogResponseAPDU) dialogAPDU;
                        // this will be present if APDU is present.
                        if (!responseAPDU.getApplicationContextName().equals(this.lastACN)) {
                            this.lastACN = responseAPDU.getApplicationContextName();
                        }
                        if (responseAPDU.getUserInformation() != null) {
                            this.lastUI = responseAPDU.getUserInformation();
                        }
                        tcEndIndication.setApplicationContextName(responseAPDU.getApplicationContextName());
                        tcEndIndication.setUserInformation(responseAPDU.getUserInformation());

                    }
                    // now comps
                    tcEndIndication.setComponents(processOperationsState(tcapEndMessage.getComponent()));

                    this.provider.deliver(this, tcEndIndication);
                }
            } finally {
                release();
            }
        } finally {
            this.dialogLock.unlock();
        }
    }

    protected void processAbort(TCAbortMessage tcapAbortMessage, SccpAddress localAddress2, SccpAddress remoteAddress2) {
        try {
            this.dialogLock.lock();
            try {
                Boolean IsAareApdu = false;
                Boolean IsAbrtApdu = false;
                ApplicationContextName acn = null;
                Result result = null;
                ResultSourceDiagnostic resultSourceDiagnostic = null;
                AbortSource abortSource = null;
                UserInformation userInfo = null;
                DialogPortion dp = tcapAbortMessage.getDialogPortion();
                if (dp != null) {
                    DialogAPDU apdu = dp.getDialogAPDU();
                    if (apdu != null && apdu.getType() == DialogAPDUType.Abort) {
                        IsAbrtApdu = true;
                        DialogAbortAPDU abortApdu = (DialogAbortAPDU) apdu;
                        abortSource = abortApdu.getAbortSource();
                        userInfo = abortApdu.getUserInformation();
                    }
                    if (apdu != null && apdu.getType() == DialogAPDUType.Response) {
                        IsAareApdu = true;
                        DialogResponseAPDU dialogResponseAPDU = (DialogResponseAPDU) apdu;
                        acn = dialogResponseAPDU.getApplicationContextName();
                        result = dialogResponseAPDU.getResult();
                        resultSourceDiagnostic = dialogResponseAPDU.getResultSourceDiagnostic();
                        userInfo = dialogResponseAPDU.getUserInformation();
                    }
                }

                PAbortCauseType providerAbortCauseType = tcapAbortMessage.getPAbortCause();
                if (providerAbortCauseType == null) {
                    if ((abortSource != null && abortSource.getAbortSourceType() == AbortSourceType.Provider)) {
                        providerAbortCauseType = PAbortCauseType.AbnormalDialogue;
                    }
                    if ((resultSourceDiagnostic != null && resultSourceDiagnostic.getDialogServiceProviderType() != null)) {
                        if (resultSourceDiagnostic.getDialogServiceProviderType() == DialogServiceProviderType.NoCommonDialogPortion)
                            providerAbortCauseType = PAbortCauseType.NoCommonDialoguePortion;
                        else
                            providerAbortCauseType = PAbortCauseType.NoReasonGiven;
                    }
                }

                if (providerAbortCauseType != null) {

                    // its TC-P-Abort
                    TCPAbortIndicationImpl tcAbortIndication = (TCPAbortIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                            .getDialogPrimitiveFactory()).createPAbortIndication(this);
                    tcAbortIndication.setPAbortCause(providerAbortCauseType);

                    this.provider.deliver(this, tcAbortIndication);

                } else {
                    // its TC-U-Abort
                    TCUserAbortIndicationImpl tcapUserAbortIndication = (TCUserAbortIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                            .getDialogPrimitiveFactory()).createUAbortIndication(this);
                    if (IsAareApdu)
                        tcapUserAbortIndication.SetAareApdu();
                    if (IsAbrtApdu)
                        tcapUserAbortIndication.SetAbrtApdu();
                    tcapUserAbortIndication.setUserInformation(userInfo);
                    tcapUserAbortIndication.setAbortSource(abortSource);
                    tcapUserAbortIndication.setApplicationContextName(acn);
                    tcapUserAbortIndication.setResultSourceDiagnostic(resultSourceDiagnostic);

                    if (state == TRPseudoState.InitialSent) {
                        // in userAbort remote address MAY change be changed, so lets
                        // update!
                        this.setRemoteAddress(remoteAddress);
                        tcapUserAbortIndication.setOriginatingAddress(remoteAddress);
                    }

                    this.provider.deliver(this, tcapUserAbortIndication);
                }
            } finally {
                release();
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    protected void sendAbnormalDialog() {

        if (this.previewMode)
            return;

        TCPAbortIndicationImpl tcapAbortIndication;
        try {
            this.dialogLock.lock();

            try {
                if (this.remoteTransactionId == null) {
                    // no remoteTransactionId - we can not send back TC-ABORT
                    return;
                }

                // sending to the remote side
                DialogPortion dialogPortion = TcapFactory.createDialogPortion();
                dialogPortion.setUnidirectional(false);

                DialogAbortAPDU dialogAbortAPDU = TcapFactory.createDialogAPDUAbort();

                AbortSource abortSource = TcapFactory.createAbortSource();
                abortSource.setAbortSourceType(AbortSourceType.Provider);

                dialogAbortAPDU.setAbortSource(abortSource);
                dialogPortion.setDialogAPDU(dialogAbortAPDU);

                TCAbortMessageImpl tcapAbortMessage = (TCAbortMessageImpl) TcapFactory.createTCAbortMessage();
                tcapAbortMessage.setDestinationTransactionId(this.remoteTransactionId);
                tcapAbortMessage.setDialogPortion(dialogPortion);

                AsnOutputStream aos = new AsnOutputStream();
                try {
                    tcapAbortMessage.encode(aos);
                    if (this.provider.getStack().getStatisticsEnabled()) {
                        this.provider.getStack().getCounterProviderImpl().updateTcPAbortSentCount(this.remoteTransactionId,
                                PAbortCauseType.NoReasonGiven);
                    }
                    this.provider.send(aos.toByteArray(), false, this.remoteAddress, this.localAddress, this.seqControl,
                            this.networkId, this.localSsn, this.remotePc);
                } catch (Exception e) {
                    if (logger.isEnabledFor(Level.ERROR)) {
                        logger.error("Failed to send message: ", e);
                    }
                }

                // sending to the local side
                tcapAbortIndication = (TCPAbortIndicationImpl) ((DialogPrimitiveFactoryImpl) this.provider
                        .getDialogPrimitiveFactory()).createPAbortIndication(this);
                tcapAbortIndication.setPAbortCause(PAbortCauseType.AbnormalDialogue);
                // tcAbortIndication.setLocalProviderOriginated(true);

                this.provider.deliver(this, tcapAbortIndication);
            } finally {
                this.release();
                // this.scheduledComponentList.clear();
            }
        } finally {
            this.dialogLock.unlock();
        }
    }

    protected Component[] processOperationsState(Component[] components) {
        if (components == null) {
            return null;
        }

        List<Component> resultingIndications = new ArrayList<Component>();
        for (Component ci : components) {
            Long invokeId;
            if (ci.getType() == ComponentType.Invoke)
                invokeId = ((InvokeImpl) ci).getLinkedId();
            else
                invokeId = ci.getInvokeId();
            InvokeImpl invoke = null;
            int index = 0;
            if (invokeId != null) {
                index = getIndexFromInvokeId(invokeId);
                invoke = this.operationsSent[index];
            }

            switch (ci.getType()) {

                case Invoke:
                    if (invokeId != null && invoke == null) {
                        logger.error(String.format("Rx : %s but no sent Invoke for linkedId exists", ci));

                        Problem problem = new ProblemImpl();
                        problem.setInvokeProblemType(InvokeProblemType.UnrechognizedLinkedID);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else {
                        if (invoke != null) {
                            ((InvokeImpl) ci).setLinkedInvoke(invoke);
                        }

                        if (this.previewMode) {
                            resultingIndications.add(ci);
                            index = getIndexFromInvokeId(ci.getInvokeId());
                            this.operationsSentA[index] = (InvokeImpl) ci;
                            ((InvokeImpl) ci).setDialog(this);
                            ((InvokeImpl) ci).setState(OperationState.Sent);
                        } else {
                            if (!this.addIncomingInvokeId(ci.getInvokeId())) {
                                logger.error(String.format("Rx : %s but there is already Invoke with this invokeId", ci));

                                Problem p = new ProblemImpl();
                                p.setInvokeProblemType(InvokeProblemType.DuplicateInvokeID);
                                this.addReject(resultingIndications, ci.getInvokeId(), p);
                            } else {
                                resultingIndications.add(ci);
                            }
                        }
                    }
                    break;

                case ReturnResult:

                    if (invoke == null) {
                        logger.error(String.format("Rx : %s but there is no corresponding Invoke", ci));

                        Problem problem = new ProblemImpl();
                        problem.setReturnResultProblemType(ReturnResultProblemType.UnrecognizedInvokeID);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else if (invoke.getInvokeClass() != InvokeClass.Class1 && invoke.getInvokeClass() != InvokeClass.Class3) {
                        logger.error(String.format("Rx : %s but Invoke class is not 1 or 3", ci));

                        Problem problem = new ProblemImpl();
                        problem.setReturnResultProblemType(ReturnResultProblemType.ReturnResultUnexpected);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else {
                        resultingIndications.add(ci);
                        ReturnResultImpl returnResult = (ReturnResultImpl) ci;
                        if (returnResult.getOperationCode() == null)
                            returnResult.setOperationCode(invoke.getOperationCode());
                    }
                    break;

                case ReturnResultLast:

                    if (invoke == null) {
                        logger.error(String.format("Rx : %s but there is no corresponding Invoke", ci));
                        Problem problem = new ProblemImpl();
                        problem.setType(ProblemType.ReturnResult);
                        problem.setReturnResultProblemType(ReturnResultProblemType.UnrecognizedInvokeID);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else if (invoke.getInvokeClass() != InvokeClass.Class1 && invoke.getInvokeClass() != InvokeClass.Class3) {
                        logger.error(String.format("Rx : %s but Invoke class is not 1 or 3", ci));
                        Problem problem = new ProblemImpl();
                        problem.setReturnResultProblemType(ReturnResultProblemType.ReturnResultUnexpected);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else {
                        invoke.onReturnResultLast();
                        if (invoke.isSuccessReported()) {
                            resultingIndications.add(ci);
                        }
                        ReturnResultLastImpl returnResultLast = (ReturnResultLastImpl) ci;
                        if (returnResultLast.getOperationCode() == null)
                            returnResultLast.setOperationCode(invoke.getOperationCode());
                    }
                    break;

                case ReturnError:
                    if (invoke == null) {
                        logger.error(String.format("Rx : %s but there is no corresponding Invoke", ci));

                        Problem problem = new ProblemImpl();
                        problem.setReturnErrorProblemType(ReturnErrorProblemType.UnrecognizedInvokeID);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else if (invoke.getInvokeClass() != InvokeClass.Class1 && invoke.getInvokeClass() != InvokeClass.Class2) {
                        logger.error(String.format("Rx : %s but Invoke class is not 1 or 2", ci));
                        Problem problem = new ProblemImpl();
                        problem.setReturnErrorProblemType(ReturnErrorProblemType.ReturnErrorUnexpected);
                        this.addReject(resultingIndications, ci.getInvokeId(), problem);
                    } else {
                        invoke.onError();
                        if (invoke.isErrorReported()) {
                            resultingIndications.add(ci);
                        }
                    }
                    break;

                case Reject:
                    Reject reject = (Reject) ci;
                    if (invoke != null) {
                        // If the Reject Problem is the InvokeProblemType we
                        // should move the invoke to the idle state
                        Problem problem = reject.getProblem();
                        if (!reject.isLocalOriginated() && problem.getInvokeProblemType() != null)
                            invoke.onReject();
                    }
                    if (reject.isLocalOriginated() && this.isStructured()) {
                        try {
                            // this is a local originated Reject - we are rejecting an incoming component
                            // we need to send a Reject also to a peer
                            this.sendComponent(reject);
                        } catch (TCAPSendException e) {
                            logger.error("TCAPSendException when sending Reject component : Dialog: " + this, e);
                        }
                    }
                    resultingIndications.add(ci);
                    break;

                default:
                    resultingIndications.add(ci);
                    break;
            }
        }

        components = new Component[resultingIndications.size()];
        components = resultingIndications.toArray(components);

        processRcvdComp(components);

        return components;
    }

    private void addReject(List<Component> resultingIndications, Long invokeId, Problem problem) {
        try {
            Reject reject = TcapFactory.createComponentReject();
            reject.setLocalOriginated(true);
            reject.setInvokeId(invokeId);
            reject.setProblem(problem);

            resultingIndications.add(reject);

            if (this.isStructured())
                this.sendComponent(reject);
        } catch (TCAPSendException e) {
            logger.error(String.format("Error sending Reject component", e));
        }
    }

    protected void setState(TRPseudoState newState) {
        try {
            this.dialogLock.lock();
            // add checks?
            if (this.state == TRPseudoState.Expunged) {
                return;
            }
            this.state = newState;
            if (newState == TRPseudoState.Expunged) {
                stopIdleTimer();
                provider.release(this);
            }
        } finally {
            this.dialogLock.unlock();
        }

    }

    private void startIdleTimer() {
        if (!this.structured)
            return;
        if (this.previewMode)
            return;

        try {
            this.dialogLock.lock();
            if (this.idleTimerFuture != null) {
                throw new IllegalStateException();
            }

            IdleTimerTask t = new IdleTimerTask();
            t.dialog = this;
            this.idleTimerFuture = this.executor.schedule(t, this.idleTaskTimeout, TimeUnit.MILLISECONDS);

        } finally {
            this.dialogLock.unlock();
        }
    }

    private void stopIdleTimer() {
        if (!this.structured)
            return;

        try {
            this.dialogLock.lock();
            if (this.idleTimerFuture != null) {
                this.idleTimerFuture.cancel(false);
                this.idleTimerFuture = null;
            }

        } finally {
            this.dialogLock.unlock();
        }
    }

    private void restartIdleTimer() {
        stopIdleTimer();
        startIdleTimer();
    }

    private class IdleTimerTask implements Runnable {
        DialogImpl dialog;

        public void run() {
            try {
                dialogLock.lock();
                dialog.idleTimerFuture = null;

                dialog.idleTimerActionTaken = false;
                dialog.idleTimerInvoked = true;
                provider.timeout(dialog);
                // send abort
                if (dialog.idleTimerActionTaken) {
                    startIdleTimer();
                } else {
                    if (remoteTransactionId != null)
                        sendAbnormalDialog();
                    else
                        release();
                }

            } finally {
                dialog.idleTimerInvoked = false;
                dialogLock.unlock();
            }
        }

    }

    // ////////////////////
    // IND like methods //
    // ///////////////////
    public void operationEnded(InvokeImpl tcInvokeRequestImpl) {
        try {
            this.dialogLock.lock();
            // this op died cause of timeout, TC-L-CANCEL!
            int index = getIndexFromInvokeId(tcInvokeRequestImpl.getInvokeId());
            freeInvokeId(tcInvokeRequestImpl.getInvokeId());
            this.operationsSent[index] = null;
            // lets call listener
            // This is done actually with COmponentIndication ....
        } finally {
            this.dialogLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#operationEnded(
     * org.restcomm.protocols.ss7.tcap.tc.component.TCInvokeRequestImpl)
     */
    public void operationTimedOut(InvokeImpl invoke) {
        // this op died cause of timeout, TC-L-CANCEL!
        try {
            this.dialogLock.lock();
            int index = getIndexFromInvokeId(invoke.getInvokeId());
            freeInvokeId(invoke.getInvokeId());
            this.operationsSent[index] = null;
            // lets call listener

        } finally {
            this.dialogLock.unlock();

        }
        this.provider.operationTimedOut(invoke);
    }

    // TC-TIMER-RESET
    public void resetTimer(Long invokeId) throws TCAPException {
        try {
            this.dialogLock.lock();
            int index = getIndexFromInvokeId(invokeId);
            InvokeImpl invoke = operationsSent[index];
            if (invoke == null) {
                throw new TCAPException("No operation with this ID");
            }
            invoke.startTimer();
        } finally {
            this.dialogLock.unlock();
        }
    }

    public TRPseudoState getState() {
        return this.state;
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    public boolean getPreviewMode() {
        return this.previewMode;
    }

    public PreviewDialogData getPreviewDialogData() {
        return this.previewDialogData;
    }

    public long getIdleTaskTimeout() {
        return this.idleTaskTimeout;
    }

    public void setIdleTaskTimeout(long idleTaskTimeoutMs) {
        this.idleTaskTimeout = idleTaskTimeoutMs;
    }

    public int getRemotePc() {
        return remotePc;
    }

    public void setRemotePc(int remotePc) {
        this.remotePc = remotePc;
    }

    public long getStartTimeDialog() {
        return this.startDialogTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return super.toString() + ": Local[" + this.getLocalDialogId() + "] Remote[" + this.getRemoteDialogId()
                + "], LocalAddress[" + localAddress + "] RemoteAddress[" + this.remoteAddress + "]";
    }
}
