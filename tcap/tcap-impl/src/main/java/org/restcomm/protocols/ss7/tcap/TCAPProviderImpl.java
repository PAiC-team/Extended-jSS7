package org.restcomm.protocols.ss7.tcap;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javolution.util.FastMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.RemoteSccpStatus;
import org.restcomm.protocols.ss7.sccp.SccpConnection;
import org.restcomm.protocols.ss7.sccp.SccpListener;
import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.sccp.SignallingPointStatus;
import org.restcomm.protocols.ss7.sccp.message.MessageFactory;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpNoticeMessage;
import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.ErrorCause;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.RefusalCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCause;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCause;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory;
import org.restcomm.protocols.ss7.tcap.api.DialogPrimitiveFactory;
import org.restcomm.protocols.ss7.tcap.api.MessageType;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.TCListener;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.TRPseudoState;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.DraftParsedMessage;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDUImpl;
import org.restcomm.protocols.ss7.tcap.asn.DialogResponseAPDU;
import org.restcomm.protocols.ss7.tcap.asn.DialogServiceProviderType;
import org.restcomm.protocols.ss7.tcap.asn.InvokeImpl;
import org.restcomm.protocols.ss7.tcap.asn.ParseException;
import org.restcomm.protocols.ss7.tcap.asn.Result;
import org.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic;
import org.restcomm.protocols.ss7.tcap.asn.ResultType;
import org.restcomm.protocols.ss7.tcap.asn.TCAbortMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCNoticeIndicationImpl;
import org.restcomm.protocols.ss7.tcap.asn.TCUnidentifiedMessage;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.Utils;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCBeginMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCEndMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage;
import org.restcomm.protocols.ss7.tcap.tc.component.ComponentPrimitiveFactoryImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.DialogPrimitiveFactoryImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.DraftParsedMessageImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCBeginIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCContinueIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCEndIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCPAbortIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCUniIndicationImpl;
import org.restcomm.protocols.ss7.tcap.tc.dialog.events.TCUserAbortIndicationImpl;
import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;
import org.restcomm.ss7.congestion.MemoryCongestionMonitorImpl;

/**
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCAPProviderImpl implements TCAPProvider, SccpListener {

    private static final Logger logger = Logger.getLogger(TCAPProviderImpl.class); // listenres

    private transient List<TCListener> tcListeners = new CopyOnWriteArrayList<TCListener>();
    protected transient ScheduledExecutorService _EXECUTOR;
    // boundary for Uni directional dialogs :), tx id is always encoded
    // on 4 octets, so this is its max value
    // private static final long _4_OCTETS_LONG_FILL = 4294967295l;
    private transient ComponentPrimitiveFactory componentPrimitiveFactory;
    private transient DialogPrimitiveFactory dialogPrimitiveFactory;
    private transient SccpProvider sccpProvider;

    private transient MessageFactory messageFactory;
    private transient ParameterFactory parameterFactory;

    private transient TCAPStackImpl stack; // originating TX id ~=Dialog, its direct
    // mapping, but not described
    // explicitly...

//    private transient FastMap<Long, DialogImpl> dialogs = new FastMap <Long, DialogImpl>();
    private transient ConcurrentHashMap<Long, DialogImpl> dialogs = new ConcurrentHashMap <Long, DialogImpl>();

//    protected transient FastMap<PrevewDialogDataKey, PreviewDialogData> dialogPreviewList = new FastMap<PrevewDialogDataKey, PrevewDialogData>();
    protected transient ConcurrentHashMap<PreviewDialogDataKey, PreviewDialogData> dialogPreviewList = new ConcurrentHashMap<PreviewDialogDataKey, PreviewDialogData>();
    private transient FastMap<Integer, NetworkIdState> networkIdStateList = new FastMap<Integer, NetworkIdState>().shared();
    private NetworkIdStateListUpdater currentNetworkIdStateListUpdater;

    private AtomicInteger seqControl = new AtomicInteger(1);
    private int ssn;
    private long curDialogId = 0;
//    private AtomicLong currentDialogId = new AtomicLong(1);

    private int cumulativeCongestionLevel = 0;
    private int executorCongestionLevel = 0;
    private int executorCountWithCongestionLevel_1 = 0;
    private int executorCountWithCongestionLevel_2 = 0;
    private int executorCountWithCongestionLevel_3 = 0;
    private MemoryCongestionMonitorImpl memoryCongestionMonitor;
    private transient FastMap<String, Integer> lstUserPartCongestionLevel = new FastMap<String, Integer>();
    private int userPartCongestionLevel_1 = 0;
    private int userPartCongestionLevel_2 = 0;
    private int userPartCongestionLevel_3 = 0;

    protected TCAPProviderImpl(SccpProvider sccpProvider, TCAPStackImpl tcapStack, int ssn) {
        super();
        this.sccpProvider = sccpProvider;
        this.ssn = ssn;
        messageFactory = sccpProvider.getMessageFactory();
        parameterFactory = sccpProvider.getParameterFactory();
        this.stack = tcapStack;

        this.componentPrimitiveFactory = new ComponentPrimitiveFactoryImpl(this);
        this.dialogPrimitiveFactory = new DialogPrimitiveFactoryImpl(this.componentPrimitiveFactory);
    }

    public boolean getPreviewMode() {
        return this.stack.getPreviewMode();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPStack#addTCListener(org.mobicents .protocols.ss7.tcap.api.TCListener)
     */

    public void addTCListener(TCListener tcapListener) {
        if (this.tcListeners.contains(tcapListener)) {
        } else {
            this.tcListeners.add(tcapListener);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPStack#removeTCListener(org.mobicents .protocols.ss7.tcap.api.TCListener)
     */
    public void removeTCListener(TCListener tcapListener) {
        this.tcListeners.remove(tcapListener);

    }

    private boolean checkAvailableTxId(Long availableTxId) {
        if (!this.dialogs.containsKey(availableTxId))
            return true;
        else
            return false;
    }

    private synchronized Long getAvailableTxId() throws TCAPException {
        if (this.dialogs.size() >= this.stack.getMaxDialogs())
            throw new TCAPException("Current dialog count exceeds its maximum value");

        while (true) {
//            Long id;
//            if (!currentDialogId.compareAndSet(this.stack.getDialogIdRangeEnd(), this.stack.getDialogIdRangeStart() + 1)) {
//                id = currentDialogId.getAndIncrement();
//            } else {
//                id = this.stack.getDialogIdRangeStart();
//            }
//            if (checkAvailableTxId(id))
//                return id;

            if (this.curDialogId < this.stack.getDialogIdRangeStart())
                this.curDialogId = this.stack.getDialogIdRangeStart() - 1;
            if (++this.curDialogId > this.stack.getDialogIdRangeEnd())
                this.curDialogId = this.stack.getDialogIdRangeStart();
            Long id = this.curDialogId;
            if (checkAvailableTxId(id))
                return id;
        }
    }

    protected void resetDialogIdValueAfterRangeChange() {
        if (this.curDialogId < this.stack.getDialogIdRangeStart())
            this.curDialogId = this.stack.getDialogIdRangeStart();
        if (this.curDialogId >= this.stack.getDialogIdRangeEnd())
            this.curDialogId = this.stack.getDialogIdRangeEnd() - 1;

        // if (this.currentDialogId.longValue() < this.stack.getDialogIdRangeStart())
        // this.currentDialogId.set(this.stack.getDialogIdRangeStart());
        // if (this.currentDialogId.longValue() >= this.stack.getDialogIdRangeEnd())
        // this.currentDialogId.set(this.stack.getDialogIdRangeEnd() - 1);
    }

    // get next Seq Control value available
    protected int getNextSeqControl() {
        int res = seqControl.getAndIncrement();

        // if (!seqControl.compareAndSet(256, 1)) {
        // return seqControl.getAndIncrement();
        // } else {
        // return 0;
        // }

        // seqControl++;
        // if (seqControl > 255) {
        // seqControl = 0;
        //
        // }
        // return seqControl;

    // get next Seq Control value available

        if (this.stack.getSlsRangeType() == SlsRangeType.Odd) {
            if (res % 2 == 0)
                res++;
        } else if (this.stack.getSlsRangeType() == SlsRangeType.Even) {
            if (res %2 != 0)
                res++;
        }

        int lastSls = this.sccpProvider.getSccpStack().getLastSls();
        // int slsMask = this.sccpProvider.getSccpStack().getSlsMask();

        res = res & lastSls;

        return res;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPProvider#getComponentPrimitiveFactory()
     */
    public ComponentPrimitiveFactory getComponentPrimitiveFactory() {
        return this.componentPrimitiveFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPProvider#getDialogPrimitiveFactory ()
     */
    public DialogPrimitiveFactory getDialogPrimitiveFactory() {
        return this.dialogPrimitiveFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPProvider#getNewDialog(org.mobicents
     * .protocols.ss7.sccp.parameter.SccpAddress, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    public Dialog getNewDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) throws TCAPException {
        DialogImpl dialog = getNewDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, getNextSeqControl(), null);
        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateAllLocalEstablishedDialogsCount();
            this.stack.getCounterProviderImpl().updateAllEstablishedDialogsCount();
        }
        this.setSsnToDialog(dialog, sccpCallingPartyAddress.getSubsystemNumber());
        return dialog;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPProvider#getNewDialog(org.mobicents
     * .protocols.ss7.sccp.parameter.SccpAddress, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress, Long id)
     */
    public Dialog getNewDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, Long id) throws TCAPException {
        DialogImpl res = getNewDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, getNextSeqControl(), id);
        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateAllLocalEstablishedDialogsCount();
            this.stack.getCounterProviderImpl().updateAllEstablishedDialogsCount();
        }
        this.setSsnToDialog(res, sccpCallingPartyAddress.getSubsystemNumber());
        return res;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.TCAPProvider#getNewUnstructuredDialog
     * (org.restcomm.protocols.ss7.sccp.parameter.SccpAddress, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    public Dialog getNewUnstructuredDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) throws TCAPException {
        DialogImpl dialog = _getDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, false, getNextSeqControl(), null);
        this.setSsnToDialog(dialog, sccpCallingPartyAddress.getSubsystemNumber());
        return dialog;
    }

    private DialogImpl getNewDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, int seqControl, Long id) throws TCAPException {
        return _getDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, true, seqControl, id);
    }

    private DialogImpl _getDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, boolean structured, int seqControl, Long id)
            throws TCAPException {

        if (this.stack.getPreviewMode()) {
            throw new TCAPException("Can not create a Dialog in a PreviewMode");
        }

        if (sccpCallingPartyAddress == null) {
            throw new NullPointerException("LocalAddress must not be null");
        }

        // synchronized (this.dialogs) {

        if (id == null) {
            id = this.getAvailableTxId();
        } else {
            if (!checkAvailableTxId(id)) {
                throw new TCAPException("Suggested local TransactionId is already present in system: " + id);
            }
        }
        if (structured) {
            DialogImpl dialog = new DialogImpl(sccpCallingPartyAddress, sccpCalledPartyAddress, id, structured, this._EXECUTOR, this, seqControl,
                    this.stack.getPreviewMode());

            this.dialogs.put(id, dialog);
            if (this.stack.getStatisticsEnabled()) {
                this.stack.getCounterProviderImpl().updateMinDialogsCount(this.dialogs.size());
                this.stack.getCounterProviderImpl().updateMaxDialogsCount(this.dialogs.size());
            }

            return dialog;
        } else {
            DialogImpl dialog = new DialogImpl(sccpCallingPartyAddress, sccpCalledPartyAddress, id, structured, this._EXECUTOR, this, seqControl,
                    this.stack.getPreviewMode());
            return dialog;
        }

        // }

    }

    private void setSsnToDialog(DialogImpl dialog, int ssn) {
        if (ssn != this.ssn) {
            if (ssn <= 0 || !this.stack.isExtraSsnPresent(ssn))
                ssn = this.ssn;
        }
        dialog.setLocalSsn(ssn);
    }

    @Override
    public int getCurrentDialogsCount() {
        return this.dialogs.size();
    }

    public void send(byte[] data, boolean returnMessageOnError, SccpAddress destinationAddress, SccpAddress originatingAddress,
            int seqControl, int networkId, int localSsn, int remotePc) throws IOException {
        if (this.stack.getPreviewMode())
            return;

        SccpDataMessage msg = messageFactory.createDataMessageClass1(destinationAddress, originatingAddress, data, seqControl,
                localSsn, returnMessageOnError, null, null);
        msg.setNetworkId(networkId);
        msg.setOutgoingDpc(remotePc);
        sccpProvider.updateSPCongestion(ssn, getCumulativeCongestionLevel());
        sccpProvider.send(msg);
    }

    public int getMaxUserDataLength(SccpAddress sccpCalledPartyAddress, SccpAddress sccpCallingPartyAddress, int msgNetworkId) {
        return this.sccpProvider.getMaxUserDataLength(sccpCalledPartyAddress, sccpCallingPartyAddress, msgNetworkId);
    }

    public void deliver(DialogImpl dialog, TCBeginIndicationImpl tcapBeginIndication) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateTcBeginReceivedCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCBegin(tcapBeginIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }

    }

    public void deliver(DialogImpl dialog, TCContinueIndicationImpl tcapContinueIndication) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateTcContinueReceivedCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCContinue(tcapContinueIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }

    }

    public void deliver(DialogImpl dialog, TCEndIndicationImpl tcapEndIndication) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateTcEndReceivedCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCEnd(tcapEndIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }
    }

    public void deliver(DialogImpl dialog, TCPAbortIndicationImpl tcapAbortIndication) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateTcPAbortReceivedCount(dialog, tcapAbortIndication.getPAbortCause());
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCPAbort(tcapAbortIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }

    }

    public void deliver(DialogImpl dialog, TCUserAbortIndicationImpl tcapAbortIndication) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateTcUserAbortReceivedCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCUserAbort(tcapAbortIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }

    }

    public void deliver(DialogImpl dialog, TCUniIndicationImpl tcapUniIndication) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateTcUniReceivedCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCUni(tcapUniIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }
    }

    public void deliver(DialogImpl dialog, TCNoticeIndicationImpl tcapNoticeIndication) {
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onTCNotice(tcapNoticeIndication);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering data to transport layer.", e);
            }
        }
    }

    public void release(DialogImpl dialog) {
        Long did = dialog.getLocalDialogId();

        if (!dialog.getPreviewMode()) {

            // synchronized (this.dialogs) {

            this.dialogs.remove(did);
            if (this.stack.getStatisticsEnabled()) {
                this.stack.getCounterProviderImpl().updateMinDialogsCount(this.dialogs.size());
                this.stack.getCounterProviderImpl().updateMaxDialogsCount(this.dialogs.size());
            }

            // }

            this.doRelease(dialog);
        }
    }

    private void doRelease(DialogImpl dialog) {

        if (dialog.isStructured() && this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateDialogReleaseCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onDialogReleased(dialog);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering dialog release.", e);
            }
        }
    }

    /**
     * @param dialog
     */
    public void timeout(DialogImpl dialog) {

        if (this.stack.getStatisticsEnabled()) {
            this.stack.getCounterProviderImpl().updateDialogTimeoutCount(dialog);
        }
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onDialogTimeout(dialog);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering dialog release.", e);
            }
        }
    }

    @Override
    public TCAPStackImpl getStack() {
        return this.stack;
    }

    // ///////////////////////////////////////////
    // Some methods invoked by operation FSM //
    // //////////////////////////////////////////
    public Future createOperationTimer(Runnable operationTimerTask, long invokeTimeout) {

        return this._EXECUTOR.schedule(operationTimerTask, invokeTimeout, TimeUnit.MILLISECONDS);
    }

    public void operationTimedOut(InvokeImpl tapcInvokeRequest) {
        try {
            for (TCListener lst : this.tcListeners) {
                lst.onInvokeTimeout(tapcInvokeRequest);
            }
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Received exception while delivering Begin.", e);
            }
        }
    }

    void start() {
        logger.info("Starting TCAP Provider");

        this._EXECUTOR = Executors.newScheduledThreadPool(4, new DefaultThreadFactory("Tcap-Thread"));

        this.sccpProvider.registerSccpListener(ssn, this);
        logger.info("Registered SCCP listener with ssn " + ssn);

        List<Integer> extraSsns = this.stack.getExtraSsns();
        if (extraSsns != null) {
            for (Integer I1 : extraSsns) {
                if (I1 != null) {
                    int extraSsn = I1;
                    this.sccpProvider.registerSccpListener(extraSsn, this);
                    logger.info("Registered SCCP listener with extra ssn " + extraSsn);
                }
            }
        }

        // congestion caring
        updateNetworkIdStateList();
        this._EXECUTOR.scheduleWithFixedDelay(new CongestionExecutor(), 1000, 1000, TimeUnit.MILLISECONDS);
        memoryCongestionMonitor = new MemoryCongestionMonitorImpl();
        lstUserPartCongestionLevel.clear();
    }

    void stop() {
        stopNetworkIdStateList();

        this._EXECUTOR.shutdown();
        this.sccpProvider.deregisterSccpListener(ssn);

        List<Integer> extraSsns = this.stack.getExtraSsns();
        if (extraSsns != null) {
            for (Integer I1 : extraSsns) {
                if (I1 != null) {
                    int extraSsn = I1;
                    this.sccpProvider.deregisterSccpListener(extraSsn);
                }
            }
        }

        this.dialogs.clear();
        this.dialogPreviewList.clear();
    }

    protected void sendProviderAbort(PAbortCauseType providerAbortCause, byte[] remoteTransactionId, SccpAddress sccpCalledPartyAddress,
            SccpAddress sccpCallingPartyAddress, int seqControl, int networkId, int remotePc) {
        if (this.stack.getPreviewMode())
            return;

        TCAbortMessageImpl tcapAbortMessage = (TCAbortMessageImpl) TcapFactory.createTCAbortMessage();
        tcapAbortMessage.setDestinationTransactionId(remoteTransactionId);
        tcapAbortMessage.setPAbortCause(providerAbortCause);

        AsnOutputStream aos = new AsnOutputStream();
        try {
            tcapAbortMessage.encode(aos);
            if (this.stack.getStatisticsEnabled()) {
                this.stack.getCounterProviderImpl().updateTcPAbortSentCount(remoteTransactionId, providerAbortCause);
            }
            this.send(aos.toByteArray(), false, sccpCalledPartyAddress, sccpCallingPartyAddress, seqControl, networkId, sccpCallingPartyAddress.getSubsystemNumber(), remotePc);
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed to send message: ", e);
            }
        }
    }

    protected void sendProviderAbort(DialogServiceProviderType dialogServiceProviderType, byte[] remoteTransactionId, SccpAddress sccpCalledPartyAddress,
            SccpAddress sccpCallingPartyAddress, int seqControl, ApplicationContextName acn, int networkId, int remotePc) {
        if (this.stack.getPreviewMode())
            return;

        DialogPortion dialogPortion = TcapFactory.createDialogPortion();
        dialogPortion.setUnidirectional(false);

        DialogResponseAPDU dialogResponseAPDU = TcapFactory.createDialogAPDUResponse();
        dialogResponseAPDU.setDoNotSendProtocolVersion(this.getStack().getDoNotSendProtocolVersion());

        Result result = TcapFactory.createResult();
        result.setResultType(ResultType.RejectedPermanent);
        ResultSourceDiagnostic rsd = TcapFactory.createResultSourceDiagnostic();
        rsd.setDialogServiceProviderType(dialogServiceProviderType);
        dialogResponseAPDU.setResultSourceDiagnostic(rsd);
        dialogResponseAPDU.setResult(result);
        dialogResponseAPDU.setApplicationContextName(acn);
        dialogPortion.setDialogAPDU(dialogResponseAPDU);

        TCAbortMessageImpl msg = (TCAbortMessageImpl) TcapFactory.createTCAbortMessage();
        msg.setDestinationTransactionId(remoteTransactionId);
        msg.setDialogPortion(dialogPortion);

        AsnOutputStream aos = new AsnOutputStream();
        try {
            msg.encode(aos);
            if (this.stack.getStatisticsEnabled()) {
                this.stack.getCounterProviderImpl().updateTcPAbortSentCount(remoteTransactionId, PAbortCauseType.NoReasonGiven);
            }
            this.send(aos.toByteArray(), false, sccpCalledPartyAddress, sccpCallingPartyAddress, seqControl, networkId, sccpCallingPartyAddress.getSubsystemNumber(), remotePc);
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("Failed to send message: ", e);
            }
        }
    }

    public void onMessage(SccpDataMessage sccpDataMessage) {

        try {
            byte[] data = sccpDataMessage.getData();
            SccpAddress sccpCallingPartyAddress = sccpDataMessage.getCalledPartyAddress();
            SccpAddress sccpCalledPartyAddress = sccpDataMessage.getCallingPartyAddress();

            // FIXME: Qs state that OtxID and DtxID constitute to dialog id.....

            // asnData - it should pass
            AsnInputStream ais = new AsnInputStream(data);

            // this should have TC message tag :)
            int tag = ais.readTag();

            if (ais.getTagClass() != Tag.CLASS_APPLICATION) {
                unrecognizedPackageType(sccpDataMessage, sccpCallingPartyAddress, sccpCalledPartyAddress, ais, tag, sccpDataMessage.getNetworkId());
                return;
            }

            switch (tag) {
            // continue first, usually we will get more of those. small perf
            // boost
                case TCContinueMessage._TAG:
                    TCContinueMessage tcapContinueMessage;
                    try {
                        tcapContinueMessage = TcapFactory.createTCContinueMessage(ais);
                    } catch (ParseException e) {
                        logger.error("ParseException when parsing TCContinueMessage: " + e.toString(), e);

                        // parsing OriginatingTransactionId
                        ais = new AsnInputStream(data);
                        tag = ais.readTag();
                        TCUnidentifiedMessage tcapUnidentified = new TCUnidentifiedMessage();
                        tcapUnidentified.decode(ais);
                        if (tcapUnidentified.getOriginatingTransactionId() != null) {
                            if (e.getPAbortCauseType() != null) {
                                this.sendProviderAbort(e.getPAbortCauseType(), tcapUnidentified.getOriginatingTransactionId(),
                                        sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                            } else {
                                this.sendProviderAbort(PAbortCauseType.BadlyFormattedTxPortion,
                                    tcapUnidentified.getOriginatingTransactionId(), sccpCalledPartyAddress, sccpCallingPartyAddress,
                                    sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                            }
                        }
                        return;
                    }

                    if (this.stack.isCongControl_blockingIncomingTcapMessages() && cumulativeCongestionLevel >= 3) {
                        // rejecting of new incoming TCAP dialogs
                        this.sendProviderAbort(PAbortCauseType.ResourceLimitation, tcapContinueMessage.getOriginatingTransactionId(),
                                sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                        return;
                    }

                    long dialogId = Utils.decodeTransactionId(tcapContinueMessage.getDestinationTransactionId(), this.stack.getSwapTcapIdBytes());
                    DialogImpl dialog;
                    if (this.stack.getPreviewMode()) {
                        PreviewDialogDataKey ky1 = new PreviewDialogDataKey(sccpDataMessage.getIncomingDpc(), (sccpDataMessage
                                .getCalledPartyAddress().getGlobalTitle() != null ? sccpDataMessage.getCalledPartyAddress()
                                .getGlobalTitle().getDigits() : null), sccpDataMessage.getCalledPartyAddress().getSubsystemNumber(),
                                dialogId);
                        long dId = Utils.decodeTransactionId(tcapContinueMessage.getOriginatingTransactionId(), this.stack.getSwapTcapIdBytes());
                        PreviewDialogDataKey ky2 = new PreviewDialogDataKey(sccpDataMessage.getIncomingOpc(), (sccpDataMessage
                                .getCallingPartyAddress().getGlobalTitle() != null ? sccpDataMessage.getCallingPartyAddress()
                                .getGlobalTitle().getDigits() : null), sccpDataMessage.getCallingPartyAddress().getSubsystemNumber(),
                                dId);
                        dialog = (DialogImpl) this.getPreviewDialog(ky1, ky2, sccpCallingPartyAddress, sccpCalledPartyAddress, 0);
                        setSsnToDialog(dialog, sccpDataMessage.getCalledPartyAddress().getSubsystemNumber());
                    } else {
                        dialog = this.dialogs.get(dialogId);
                    }
                    if (dialog == null) {
                        logger.warn("TC-CONTINUE: No dialog/transaction for id: " + dialogId);
                        this.sendProviderAbort(PAbortCauseType.UnrecognizedTxID, tcapContinueMessage.getOriginatingTransactionId(),
                                sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                    } else {
                        dialog.processContinue(tcapContinueMessage, sccpCallingPartyAddress, sccpCalledPartyAddress);
                    }

                    break;

                case TCBeginMessage._TAG:
                    TCBeginMessage tcapBeginMessage;
                    try {
                        tcapBeginMessage = TcapFactory.createTCBeginMessage(ais);
                    } catch (ParseException e) {
                        logger.error("ParseException when parsing TCBeginMessage: " + e.toString(), e);

                        // parsing OriginatingTransactionId
                        ais = new AsnInputStream(data);
                        tag = ais.readTag();
                        TCUnidentifiedMessage tcUnidentified = new TCUnidentifiedMessage();
                        tcUnidentified.decode(ais);
                        if (tcUnidentified.getOriginatingTransactionId() != null) {
                            if (e.getPAbortCauseType() != null) {
                                this.sendProviderAbort(e.getPAbortCauseType(), tcUnidentified.getOriginatingTransactionId(),
                                        sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                            } else {
                                this.sendProviderAbort(PAbortCauseType.BadlyFormattedTxPortion,
                                        tcUnidentified.getOriginatingTransactionId(), sccpCalledPartyAddress, sccpCallingPartyAddress,
                                        sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                            }
                        }
                        return;
                    }
                    if (tcapBeginMessage.getDialogPortion() != null && tcapBeginMessage.getDialogPortion().getDialogAPDU() != null
                            && tcapBeginMessage.getDialogPortion().getDialogAPDU() instanceof DialogRequestAPDUImpl) {
                        DialogRequestAPDUImpl dlg = (DialogRequestAPDUImpl) tcapBeginMessage.getDialogPortion().getDialogAPDU();
                        if (dlg.getProtocolVersion() != null && !dlg.getProtocolVersion().isSupportedVersion()) {
                            logger.error("Unsupported protocol version of  has been received when parsing TCBeginMessage");
                            this.sendProviderAbort(DialogServiceProviderType.NoCommonDialogPortion,
                                tcapBeginMessage.getOriginatingTransactionId(), sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(),
                                    dlg.getApplicationContextName(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                            return;
                        }
                    }

                    if (this.stack.isCongControl_blockingIncomingTcapMessages() && cumulativeCongestionLevel >= 2) {
                        // rejecting of new incoming TCAP dialogs
                        this.sendProviderAbort(PAbortCauseType.ResourceLimitation, tcapBeginMessage.getOriginatingTransactionId(),
                                sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                        return;
                    }

                    dialog = null;
                    try {
                        if (this.stack.getPreviewMode()) {
                            long dId = Utils.decodeTransactionId(tcapBeginMessage.getOriginatingTransactionId(), this.stack.getSwapTcapIdBytes());
                            PreviewDialogDataKey ky = new PreviewDialogDataKey(sccpDataMessage.getIncomingOpc(), (sccpDataMessage
                                    .getCallingPartyAddress().getGlobalTitle() != null ? sccpDataMessage.getCallingPartyAddress()
                                    .getGlobalTitle().getDigits() : null), sccpDataMessage.getCallingPartyAddress()
                                    .getSubsystemNumber(), dId);
                            dialog = (DialogImpl) this.createPreviewDialog(ky, sccpCallingPartyAddress, sccpCalledPartyAddress, 0);
                            setSsnToDialog(dialog, sccpDataMessage.getCalledPartyAddress().getSubsystemNumber());
                        } else {
                            int remotePc = sccpDataMessage.getIncomingOpc();
                            dialog = (DialogImpl) this.getNewDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, sccpDataMessage.getSls(), null);
                            dialog.setRemotePc(remotePc);
                            setSsnToDialog(dialog, sccpDataMessage.getCalledPartyAddress().getSubsystemNumber());
                        }

                    } catch (TCAPException e) {
                        this.sendProviderAbort(PAbortCauseType.ResourceLimitation, tcapBeginMessage.getOriginatingTransactionId(),
                                sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), sccpDataMessage.getNetworkId(), sccpDataMessage.getIncomingOpc());
                        logger.error("Can not add a new dialog when receiving TCBeginMessage: " + e.getMessage(), e);
                        return;
                    }

                    if (this.stack.getStatisticsEnabled()) {
                        this.stack.getCounterProviderImpl().updateAllRemoteEstablishedDialogsCount();
                        this.stack.getCounterProviderImpl().updateAllEstablishedDialogsCount();
                    }
                    dialog.setNetworkId(sccpDataMessage.getNetworkId());
                    dialog.processBegin(tcapBeginMessage, sccpCallingPartyAddress, sccpCalledPartyAddress);

                    if (this.stack.getPreviewMode()) {
                        dialog.getPreviewDialogData().setLastACN(dialog.getApplicationContextName());
                        dialog.getPreviewDialogData().setOperationsSentB(dialog.operationsSent);
                        dialog.getPreviewDialogData().setOperationsSentA(dialog.operationsSentA);
                    }

                    break;

                case TCEndMessage._TAG:
                    TCEndMessage tcapEndMessage;
                    try {
                        tcapEndMessage = TcapFactory.createTCEndMessage(ais);
                    } catch (ParseException e) {
                        logger.error("ParseException when parsing TCEndMessage: " + e.toString(), e);
                        return;
                    }

                    if (this.stack.isCongControl_blockingIncomingTcapMessages() && cumulativeCongestionLevel >= 3) {
                        // rejecting of new incoming TCAP dialogs
                        return;
                    }

                    dialogId = Utils.decodeTransactionId(tcapEndMessage.getDestinationTransactionId(), this.stack.getSwapTcapIdBytes());
                    if (this.stack.getPreviewMode()) {
                        PreviewDialogDataKey ky = new PreviewDialogDataKey(sccpDataMessage.getIncomingDpc(), (sccpDataMessage
                                .getCalledPartyAddress().getGlobalTitle() != null ? sccpDataMessage.getCalledPartyAddress()
                                .getGlobalTitle().getDigits() : null), sccpDataMessage.getCalledPartyAddress().getSubsystemNumber(),
                                dialogId);
                        dialog = (DialogImpl) this.getPreviewDialog(ky, null, sccpCallingPartyAddress, sccpCalledPartyAddress, 0);
                        setSsnToDialog(dialog, sccpDataMessage.getCalledPartyAddress().getSubsystemNumber());
                    } else {
                        dialog = this.dialogs.get(dialogId);
                    }
                    if (dialog == null) {
                        logger.warn("TC-END: No dialog/transaction for id: " + dialogId);
                    } else {
                        dialog.processEnd(tcapEndMessage, sccpCallingPartyAddress, sccpCalledPartyAddress);

                        if (this.stack.getPreviewMode()) {
                            this.removePreviewDialog(dialog);
                        }
                    }
                    break;

                case TCAbortMessage._TAG:
                    TCAbortMessage tcapAbortMessage = null;
                    try {
                        tcapAbortMessage = TcapFactory.createTCAbortMessage(ais);
                    } catch (ParseException e) {
                        logger.error("ParseException when parsing TCAbortMessage: " + e.toString(), e);
                        return;
                    }

                    if (this.stack.isCongControl_blockingIncomingTcapMessages() && cumulativeCongestionLevel >= 3) {
                        // rejecting of new incoming TCAP dialogs
                        return;
                    }

                    dialogId = Utils.decodeTransactionId(tcapAbortMessage.getDestinationTransactionId(), this.stack.getSwapTcapIdBytes());
                    if (this.stack.getPreviewMode()) {
                        long dId = Utils.decodeTransactionId(tcapAbortMessage.getDestinationTransactionId(), this.stack.getSwapTcapIdBytes());
                        PreviewDialogDataKey ky = new PreviewDialogDataKey(sccpDataMessage.getIncomingDpc(), (sccpDataMessage
                                .getCalledPartyAddress().getGlobalTitle() != null ? sccpDataMessage.getCalledPartyAddress()
                                .getGlobalTitle().getDigits() : null), sccpDataMessage.getCalledPartyAddress().getSubsystemNumber(),
                                dId);
                        dialog = (DialogImpl) this.getPreviewDialog(ky, null, sccpCallingPartyAddress, sccpCalledPartyAddress, 0);
                        setSsnToDialog(dialog, sccpDataMessage.getCalledPartyAddress().getSubsystemNumber());
                    } else {
                        dialog = this.dialogs.get(dialogId);
                    }
                    if (dialog == null) {
                        logger.warn("TC-ABORT: No dialog/transaction for id: " + dialogId);
                    } else {
                        dialog.processAbort(tcapAbortMessage, sccpCallingPartyAddress, sccpCalledPartyAddress);

                        if (this.stack.getPreviewMode()) {
                            this.removePreviewDialog(dialog);
                        }
                    }
                    break;

                case TCUniMessage._TAG:
                    TCUniMessage tcapUniMessage;
                    try {
                        tcapUniMessage = TcapFactory.createTCUniMessage(ais);
                    } catch (ParseException e) {
                        logger.error("ParseException when parsing TCUniMessage: " + e.toString(), e);
                        return;
                    }

                    if (this.stack.isCongControl_blockingIncomingTcapMessages() && cumulativeCongestionLevel >= 3) {
                        // rejecting of new incoming TCAP dialogs
                        return;
                    }
                    int remotePc = sccpDataMessage.getIncomingOpc();
                    DialogImpl uniDialog = (DialogImpl) this.getNewUnstructuredDialog(sccpCallingPartyAddress, sccpCalledPartyAddress);
                    uniDialog.setRemotePc(remotePc);
                    setSsnToDialog(uniDialog, sccpDataMessage.getCalledPartyAddress().getSubsystemNumber());
                    uniDialog.processUni(tcapUniMessage, sccpCallingPartyAddress, sccpCalledPartyAddress);
                    break;

                default:
                    unrecognizedPackageType(sccpDataMessage, sccpCallingPartyAddress, sccpCalledPartyAddress, ais, tag, sccpDataMessage.getNetworkId());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Error while decoding Rx SccpMessage=%s", sccpDataMessage), e);
        }
    }

    private void unrecognizedPackageType(SccpDataMessage sccpDataMessage, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, AsnInputStream ais, int tag,
            int networkId) throws ParseException {
        if (this.stack.getPreviewMode()) {
            return;
        }

        logger.error(String.format("Rx unidentified tag=%s, tagClass=. SccpMessage=%s", tag, ais.getTagClass(), sccpDataMessage));
        TCUnidentifiedMessage tcapUnidentified = new TCUnidentifiedMessage();
        tcapUnidentified.decode(ais);

        if (tcapUnidentified.getOriginatingTransactionId() != null) {
            byte[] otid = tcapUnidentified.getOriginatingTransactionId();

            if (tcapUnidentified.getDestinationTransactionId() != null) {
                Long dtid = Utils.decodeTransactionId(tcapUnidentified.getDestinationTransactionId(), this.stack.getSwapTcapIdBytes());
                this.sendProviderAbort(PAbortCauseType.UnrecognizedMessageType, otid, sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), networkId, sccpDataMessage.getIncomingOpc());
            } else {
                this.sendProviderAbort(PAbortCauseType.UnrecognizedMessageType, otid, sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), networkId, sccpDataMessage.getIncomingOpc());
            }
        } else {
            this.sendProviderAbort(PAbortCauseType.UnrecognizedMessageType, new byte[0], sccpCalledPartyAddress, sccpCallingPartyAddress, sccpDataMessage.getSls(), networkId, sccpDataMessage.getIncomingOpc());
        }
    }

    public void onNotice(SccpNoticeMessage sccpNoticeMessage) {

        if (this.stack.getPreviewMode()) {
            return;
        }

        DialogImpl dialog = null;

        try {
            byte[] data = sccpNoticeMessage.getData();
            AsnInputStream ais = new AsnInputStream(data);

            // this should have TC message tag :)
            int tag = ais.readTag();

            TCUnidentifiedMessage tcapUnidentified = new TCUnidentifiedMessage();
            tcapUnidentified.decode(ais);

            if (tcapUnidentified.getOriginatingTransactionId() != null) {
                long otid = Utils.decodeTransactionId(tcapUnidentified.getOriginatingTransactionId(), this.stack.getSwapTcapIdBytes());
                dialog = this.dialogs.get(otid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Error while decoding Rx SccpNoticeMessage=%s", sccpNoticeMessage), e);
        }

        TCNoticeIndicationImpl tcapNoticeIndication = new TCNoticeIndicationImpl();
        tcapNoticeIndication.setRemoteAddress(sccpNoticeMessage.getCallingPartyAddress());
        tcapNoticeIndication.setLocalAddress(sccpNoticeMessage.getCalledPartyAddress());
        tcapNoticeIndication.setDialog(dialog);
        tcapNoticeIndication.setReportCause(sccpNoticeMessage.getReturnCause().getValue());

        if (dialog != null) {
            try {
                dialog.dialogLock.lock();

                this.deliver(dialog, tcapNoticeIndication);

                if (dialog.getState() != TRPseudoState.Active) {
                    dialog.release();
                }
            } finally {
                dialog.dialogLock.unlock();
            }
        } else {
            this.deliver(dialog, tcapNoticeIndication);
        }
    }

    protected  Dialog createPreviewDialog(PreviewDialogDataKey previewDialogDataKey, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress,
            int seqControl) throws TCAPException {

        if (this.dialogPreviewList.size() >= this.stack.getMaxDialogs())
            throw new TCAPException("Current dialog count exceeds its maximum value");

        Long dialogId = this.getAvailableTxIdPreview();
        PreviewDialogData pdd = new PreviewDialogData(this, dialogId);
        pdd.setPreviewDialogDataKey1(previewDialogDataKey);
        PreviewDialogData previewDialogData = this.dialogPreviewList.putIfAbsent(previewDialogDataKey, pdd);
        if (previewDialogData != null) {
            this.removePreviewDialog(previewDialogData);
            throw new TCAPException("Dialog with trId=" + previewDialogDataKey.origTxId
                    + " is already exists - we ignore it and drops current dialog");
        }

        DialogImpl dialog = new DialogImpl(sccpCallingPartyAddress, sccpCalledPartyAddress, seqControl, this._EXECUTOR, this, pdd, false);

        pdd.startIdleTimer();

        return dialog;

        // synchronized (this.dialogPreviewList) {
        // if (this.dialogPreviewList.size() >= this.stack.getMaxDialogs())
        // throw new TCAPException("Current dialog count exceeds its maximum value");
        //
        // // checking if a Dialog is current already exists
        // PreviewDialogData pddx = this.dialogPreviewList.get(ky);
        // if (pddx != null) {
        // this.removePreviewDialog(pddx);
        // throw new TCAPException("Dialog with trId=" + ky.origTxId +
        // " is already exists - we ignore it and drops curent dialog");
        // }
        //
        // Long dialogId = this.getAvailableTxIdPreview();
        // PreviewDialogData pdd = new PreviewDialogData(this, dialogId);
        // this.dialogPreviewList.put(ky, pdd);
        // DialogImpl di = new DialogImpl(sccpCallingPartyAddress, sccpCalledPartyAddress, seqControl, this._EXECUTOR, this, pdd, false);
        // pdd.setPrevewDialogDataKey1(ky);
        //
        // pdd.startIdleTimer();
        //
        // return di;
        // }
    }

    protected synchronized Long getAvailableTxIdPreview() throws TCAPException {
        while (true) {
            // Long id;
            // if (!currentDialogId.compareAndSet(this.stack.getDialogIdRangeEnd(), this.stack.getDialogIdRangeStart() + 1)) {
            // id = currentDialogId.getAndIncrement();
            // } else {
            // id = this.stack.getDialogIdRangeStart();
            // }
            // return id;

            if (this.curDialogId < this.stack.getDialogIdRangeStart())
                this.curDialogId = this.stack.getDialogIdRangeStart() - 1;
            if (++this.curDialogId > this.stack.getDialogIdRangeEnd())
                this.curDialogId = this.stack.getDialogIdRangeStart();
            Long id = this.curDialogId;
            return id;
        }
    }

    protected Dialog getPreviewDialog(PreviewDialogDataKey previewDialogDataKey, PreviewDialogDataKey ky2, SccpAddress sccpCallingPartyAddress,
            SccpAddress sccpCalledPartyAddress, int seqControl) {

        // synchronized (this.dialogPreviewList) {

        PreviewDialogData previewDialogData = this.dialogPreviewList.get(previewDialogDataKey);
        DialogImpl dialog;
        boolean sideB = false;
        if (previewDialogData != null) {
            sideB = previewDialogData.getPreviewDialogDataKey1().equals(previewDialogDataKey);
            dialog = new DialogImpl(sccpCallingPartyAddress, sccpCalledPartyAddress, seqControl, this._EXECUTOR, this, previewDialogData, sideB);
        } else {
            if (ky2 != null)
                previewDialogData = this.dialogPreviewList.get(ky2);
            if (previewDialogData != null) {
                sideB = previewDialogData.getPreviewDialogDataKey1().equals(previewDialogDataKey);
                dialog = new DialogImpl(sccpCallingPartyAddress, sccpCalledPartyAddress, seqControl, this._EXECUTOR, this, previewDialogData, sideB);
            } else {
                return null;
            }
        }

        previewDialogData.restartIdleTimer();

        if (previewDialogData.getPreviewDialogDataKey2() == null && ky2 != null) {
            if (previewDialogData.getPreviewDialogDataKey1().equals(previewDialogDataKey))
                previewDialogData.setPreviewDialogDataKey2(ky2);
            else
                previewDialogData.setPreviewDialogDataKey2(previewDialogDataKey);
            this.dialogPreviewList.put(previewDialogData.getPreviewDialogDataKey2(), previewDialogData);
        }

        return dialog;
        // }
    }

    protected void removePreviewDialog(DialogImpl dialog) {
        // synchronized (this.dialogPreviewList) {

        PreviewDialogData previewDialogData = this.dialogPreviewList.get(dialog.previewDialogData.getPreviewDialogDataKey1());
        if (previewDialogData == null && dialog.previewDialogData.getPreviewDialogDataKey2() != null) {
            previewDialogData = this.dialogPreviewList.get(dialog.previewDialogData.getPreviewDialogDataKey2());
        }

        if (previewDialogData != null)
            removePreviewDialog(previewDialogData);

        // }

        this.doRelease(dialog);
    }

    protected void removePreviewDialog(PreviewDialogData previewDialogData) {

        // synchronized (this.dialogPreviewList) {

        this.dialogPreviewList.remove(previewDialogData.getPreviewDialogDataKey1());
        if (previewDialogData.getPreviewDialogDataKey2() != null) {
            this.dialogPreviewList.remove(previewDialogData.getPreviewDialogDataKey2());
        }

        // }

        previewDialogData.stopIdleTimer();

        // TODO ??? : create Dialog and invoke "this.doRelease(di);"
    }

    @Override
    public DraftParsedMessage parseMessageDraft(byte[] data) {
        try {
            DraftParsedMessageImpl draftParsedMessage = new DraftParsedMessageImpl();

            AsnInputStream ais = new AsnInputStream(data);

            int tag = ais.readTag();

            if (ais.getTagClass() != Tag.CLASS_APPLICATION) {
                draftParsedMessage.setParsingErrorReason("Message tag class must be CLASS_APPLICATION");
                return draftParsedMessage;
            }

            switch (tag) {
                case TCContinueMessage._TAG:
                    AsnInputStream localAis = ais.readSequenceStream();

                    tag = localAis.readTag();
                    if (tag != TCContinueMessage._TAG_OTX || localAis.getTagClass() != Tag.CLASS_APPLICATION) {
                        draftParsedMessage.setParsingErrorReason("originatingTransactionId tag/tagClass is bad for TC-CONTINUE message");
                        return draftParsedMessage;
                    }
                    byte[] originatingTransactionId = localAis.readOctetString();
                    draftParsedMessage.setOriginationDialogId(Utils.decodeTransactionId(originatingTransactionId, this.stack.getSwapTcapIdBytes()));

                    tag = localAis.readTag();
                    if (tag != TCContinueMessage._TAG_DTX || localAis.getTagClass() != Tag.CLASS_APPLICATION) {
                        draftParsedMessage.setParsingErrorReason("destinationTransactionId tag/tagClass is bad for TC-CONTINUE message");
                        return draftParsedMessage;
                    }
                    byte[] destinationTransactionId = localAis.readOctetString();
                    draftParsedMessage.setDestinationDialogId(Utils.decodeTransactionId(destinationTransactionId, this.stack.getSwapTcapIdBytes()));

                    draftParsedMessage.setMessageType(MessageType.Continue);
                    break;

                case TCBeginMessage._TAG:
                    localAis = ais.readSequenceStream();

                    tag = localAis.readTag();
                    if (tag != TCBeginMessage._TAG_OTX || localAis.getTagClass() != Tag.CLASS_APPLICATION) {
                        draftParsedMessage.setParsingErrorReason("originatingTransactionId tag/tagClass is bad for TC-BEGIN message");
                        return draftParsedMessage;
                    }
                    originatingTransactionId = localAis.readOctetString();
                    draftParsedMessage.setOriginationDialogId(Utils.decodeTransactionId(originatingTransactionId, this.stack.getSwapTcapIdBytes()));

                    draftParsedMessage.setMessageType(MessageType.Begin);
                    break;

                case TCEndMessage._TAG:
                    localAis = ais.readSequenceStream();

                    tag = localAis.readTag();
                    if (tag != TCEndMessage._TAG_DTX || localAis.getTagClass() != Tag.CLASS_APPLICATION) {
                        draftParsedMessage.setParsingErrorReason("destinationTransactionId tag/tagClass is bad for TC-END message");
                        return draftParsedMessage;
                    }
                    destinationTransactionId = localAis.readOctetString();
                    draftParsedMessage.setDestinationDialogId(Utils.decodeTransactionId(destinationTransactionId, this.stack.getSwapTcapIdBytes()));

                    draftParsedMessage.setMessageType(MessageType.End);
                    break;

                case TCAbortMessage._TAG:
                    localAis = ais.readSequenceStream();

                    tag = localAis.readTag();
                    if (tag != TCAbortMessage._TAG_DTX || localAis.getTagClass() != Tag.CLASS_APPLICATION) {
                        draftParsedMessage.setParsingErrorReason("destinationTransactionId tag/tagClass is bad for TC-ABORT message");
                        return draftParsedMessage;
                    }
                    destinationTransactionId = localAis.readOctetString();
                    draftParsedMessage.setDestinationDialogId(Utils.decodeTransactionId(destinationTransactionId, this.stack.getSwapTcapIdBytes()));

                    draftParsedMessage.setMessageType(MessageType.Abort);
                    break;

                case TCUniMessage._TAG:
                    draftParsedMessage.setMessageType(MessageType.Unidirectional);
                    break;

                default:
                    draftParsedMessage.setParsingErrorReason("Unrecognized message tag");
                    break;
            }

            return draftParsedMessage;
        }
        catch (Exception e) {
            DraftParsedMessageImpl res = new DraftParsedMessageImpl();
            res.setParsingErrorReason("Exception when message parsing: " + e.getMessage());
            return res;
        }
    }

    public void onCoordResponse(int ssn, int multiplicityIndicator) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onState(int dpc, int ssn, boolean inService, int multiplicityIndicator) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPcState(int dpc, SignallingPointStatus status, Integer restrictedImportanceLevel, RemoteSccpStatus remoteSccpStatus) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNetworkIdState(int networkId, NetworkIdState networkIdState) {
        Integer ni = networkId;
        NetworkIdState prev = networkIdStateList.get(ni);
        if (!networkIdState.equals(prev)) {
            logger.warn("Outgoing congestion control: TCAP: onNetworkIdState: networkId=" + networkId + ", networkIdState="
                    + networkIdState);
        }

        networkIdStateList.put(ni, networkIdState);
    }

    @Override
    public void onConnectIndication(SccpConnection sccpConnection, SccpAddress sccpCalledPartyAddress, SccpAddress sccpCallingPartyAddress, ProtocolClass protocolClass, Credit credit, byte[] data, Importance importance) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void onConnectConfirm(SccpConnection sccpConnection, byte[] data) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDisconnectIndication(SccpConnection sccpConnection, ReleaseCause reason, byte[] data) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDisconnectIndication(SccpConnection sccpConnection, RefusalCause reason, byte[] data) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDisconnectIndication(SccpConnection sccpConnection, ErrorCause errorCause) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResetIndication(SccpConnection sccpConnection, ResetCause reason) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResetConfirm(SccpConnection sccpConnection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onData(SccpConnection sccpConnection, byte[] data) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDisconnectConfirm(SccpConnection sccpConnection) {
        // TODO Auto-generated method stub
    }

    @Override
    public FastMap<Integer, NetworkIdState> getNetworkIdStateList() {
        return networkIdStateList;
    }

    @Override
    public NetworkIdState getNetworkIdState(int networkId) {
        return networkIdStateList.get(networkId);
    }

    private void stopNetworkIdStateList() {
        NetworkIdStateListUpdater networkIdStateListUpdater = currentNetworkIdStateListUpdater;
        if (networkIdStateListUpdater != null)
            networkIdStateListUpdater.cancel();
    }

    private void updateNetworkIdStateList() {
        stopNetworkIdStateList();
        currentNetworkIdStateListUpdater = new NetworkIdStateListUpdater();
        this._EXECUTOR.schedule(currentNetworkIdStateListUpdater, 1000, TimeUnit.MILLISECONDS);

        networkIdStateList = this.sccpProvider.getNetworkIdStateList();

        int cntNotAvailable = 0;
        int cntCongLevel1 = 0;
        int cntCongLevel2 = 0;
        int cntCongLevel3 = 0;
        if (networkIdStateList != null) {
            for (NetworkIdState state : networkIdStateList.values()) {
                if (!state.isAvailable())
                    cntNotAvailable++;
                if (state.getCongLevel() >= 1) {
                    cntCongLevel1++;
                }
                if (state.getCongLevel() >= 2) {
                    cntCongLevel1++;
                    cntCongLevel2++;
                }
                if (state.getCongLevel() >= 3) {
                    cntCongLevel1++;
                    cntCongLevel2++;
                    cntCongLevel3++;
                }
            }
        }

        this.stack.getCounterProviderImpl().updateMaxNetworkIdAreasNotAvailable(cntNotAvailable);
        this.stack.getCounterProviderImpl().updateMaxNetworkIdAreasCongLevel_1(cntCongLevel1);
        this.stack.getCounterProviderImpl().updateMaxNetworkIdAreasCongLevel_2(cntCongLevel2);
        this.stack.getCounterProviderImpl().updateMaxNetworkIdAreasCongLevel_3(cntCongLevel3);
    }

    public int getNetworkIdAreasNotAvailableCount() {
        int cntNotAvailable = 0;
        for (NetworkIdState state : networkIdStateList.values()) {
            if (!state.isAvailable())
                cntNotAvailable++;
        }
        return cntNotAvailable;
    }

    public int getNetworkIdAreasCongLevel_1_Count() {
        int cntCongLevel1 = 0;
        for (NetworkIdState state : networkIdStateList.values()) {
            if (state.getCongLevel() >= 1) {
                cntCongLevel1++;
            }
        }
        return cntCongLevel1;
    }

    public int getNetworkIdAreasCongLevel_2_Count() {
        int cntCongLevel2 = 0;
        for (NetworkIdState state : networkIdStateList.values()) {
            if (state.getCongLevel() >= 2) {
                cntCongLevel2++;
            }
        }
        return cntCongLevel2;
    }

    public int getNetworkIdAreasCongLevel_3_Count() {
        int cntCongLevel3 = 0;
        for (NetworkIdState state : networkIdStateList.values()) {
            if (state.getCongLevel() >= 3) {
                cntCongLevel3++;
            }
        }
        return cntCongLevel3;
    }

    private class NetworkIdStateListUpdater implements Runnable, Serializable {
        private boolean isCancelled;

        public void cancel() {
            isCancelled = true;
        }

        @Override
        public void run() {
            if (isCancelled || !stack.isStarted())
                return;

            updateNetworkIdStateList();
        }
    }

    private class CongestionExecutor implements Runnable {

        @Override
        public void run() {
            // MTP3 Executor monitors
            ExecutorCongestionMonitor[] lst = sccpProvider.getExecutorCongestionMonitorList();
            int maxExecutorCongestionLevel = 0;
            int countExecutorCountWithCongestionLevel_1 = 0;
            int countExecutorCountWithCongestionLevel_2 = 0;
            int countExecutorCountWithCongestionLevel_3 = 0;
            for (ExecutorCongestionMonitor executorCongestionMonitor : lst) {
                int level = executorCongestionMonitor.getAlarmLevel();
                if (maxExecutorCongestionLevel < level)
                    maxExecutorCongestionLevel = level;
                if (level >= 1) {
                    countExecutorCountWithCongestionLevel_1++;
                }
                if (level >= 2) {
                    countExecutorCountWithCongestionLevel_1++;
                    countExecutorCountWithCongestionLevel_2++;
                }
                if (level >= 3) {
                    countExecutorCountWithCongestionLevel_1++;
                    countExecutorCountWithCongestionLevel_2++;
                    countExecutorCountWithCongestionLevel_3++;
                }
                try {
                    executorCongestionMonitor.setDelayThreshold_1(stack.getCongControl_ExecutorDelayThreshold_1());
                    executorCongestionMonitor.setDelayThreshold_2(stack.getCongControl_ExecutorDelayThreshold_2());
                    executorCongestionMonitor.setDelayThreshold_3(stack.getCongControl_ExecutorDelayThreshold_3());
                    executorCongestionMonitor.setBackToNormalDelayThreshold_1(stack.getCongControl_ExecutorBackToNormalDelayThreshold_1());
                    executorCongestionMonitor.setBackToNormalDelayThreshold_2(stack.getCongControl_ExecutorBackToNormalDelayThreshold_2());
                    executorCongestionMonitor.setBackToNormalDelayThreshold_3(stack.getCongControl_ExecutorBackToNormalDelayThreshold_3());
                } catch (Exception e) {
                    // this must not be
                }
            }
            executorCongestionLevel = maxExecutorCongestionLevel;
            executorCountWithCongestionLevel_1 = countExecutorCountWithCongestionLevel_1;
            executorCountWithCongestionLevel_2 = countExecutorCountWithCongestionLevel_2;
            executorCountWithCongestionLevel_3 = countExecutorCountWithCongestionLevel_3;

            stack.getCounterProviderImpl().updateMaxExecutorsCongLevel_1(executorCountWithCongestionLevel_1);
            stack.getCounterProviderImpl().updateMaxExecutorsCongLevel_2(executorCountWithCongestionLevel_2);
            stack.getCounterProviderImpl().updateMaxExecutorsCongLevel_3(executorCountWithCongestionLevel_3);

            // MemoryMonitor

            memoryCongestionMonitor.setMemoryThreshold1(stack.getCongControl_MemoryThreshold_1());
            memoryCongestionMonitor.setMemoryThreshold2(stack.getCongControl_MemoryThreshold_2());
            memoryCongestionMonitor.setMemoryThreshold3(stack.getCongControl_MemoryThreshold_3());
            memoryCongestionMonitor.setBackToNormalMemoryThreshold1(stack.getCongControl_BackToNormalMemoryThreshold_1());
            memoryCongestionMonitor.setBackToNormalMemoryThreshold2(stack.getCongControl_BackToNormalMemoryThreshold_2());
            memoryCongestionMonitor.setBackToNormalMemoryThreshold3(stack.getCongControl_BackToNormalMemoryThreshold_3());

            memoryCongestionMonitor.monitor();
            stack.getCounterProviderImpl().updateMaxMemoryCongLevel(memoryCongestionMonitor.getAlarmLevel());

            // cumulativeCongestionLevel
            int newCumulativeCongestionLevel = getCumulativeCongestionLevel();
            if (cumulativeCongestionLevel != newCumulativeCongestionLevel) {
                logger.warn("Outgoing congestion control: Changing of internal congestion level: " + cumulativeCongestionLevel
                        + "->" + newCumulativeCongestionLevel + "\n" + getCumulativeCongestionLevelString());
                cumulativeCongestionLevel = newCumulativeCongestionLevel;
            }
        }
    }

    @Override
    public synchronized void setUserPartCongestionLevel(String congestionObject, int level) {
        if (congestionObject != null) {
            if (level > 0) {
                lstUserPartCongestionLevel.put(congestionObject, level);
            } else {
                lstUserPartCongestionLevel.remove(congestionObject);
            }

            int cntUserPartCongestionLevel_1 = 0;
            int cntUserPartCongestionLevel_2 = 0;
            int cntUserPartCongestionLevel_3 = 0;
            for (Integer lev : lstUserPartCongestionLevel.values()) {
                if (lev >= 1) {
                    cntUserPartCongestionLevel_1++;
                }
                if (lev >= 2) {
                    cntUserPartCongestionLevel_1++;
                    cntUserPartCongestionLevel_2++;
                }
                if (lev >= 3) {
                    cntUserPartCongestionLevel_1++;
                    cntUserPartCongestionLevel_2++;
                    cntUserPartCongestionLevel_3++;
                }
            }
            userPartCongestionLevel_1 = cntUserPartCongestionLevel_1;
            userPartCongestionLevel_2 = cntUserPartCongestionLevel_2;
            userPartCongestionLevel_3 = cntUserPartCongestionLevel_3;

            stack.getCounterProviderImpl().updateMaxUserPartsCongLevel_1(userPartCongestionLevel_1);
            stack.getCounterProviderImpl().updateMaxUserPartsCongLevel_2(userPartCongestionLevel_2);
            stack.getCounterProviderImpl().updateMaxUserPartsCongLevel_3(userPartCongestionLevel_3);
        }
    }

    @Override
    public int getMemoryCongestionLevel() {
        return this.memoryCongestionMonitor.getAlarmLevel();
    }

    @Override
    public int getExecutorCongestionLevel() {
        return this.executorCongestionLevel;
    }

    public int getExecutorCountWithCongestionLevel_1() {
        return this.executorCountWithCongestionLevel_1;
    }

    public int getExecutorCountWithCongestionLevel_2() {
        return this.executorCountWithCongestionLevel_2;
    }

    public int getExecutorCountWithCongestionLevel_3() {
        return this.executorCountWithCongestionLevel_3;
    }

    public int getUserPartCongestionLevel_1() {
        return this.userPartCongestionLevel_1;
    }

    public int getUserPartCongestionLevel_2() {
        return this.userPartCongestionLevel_2;
    }

    public int getUserPartCongestionLevel_3() {
        return this.userPartCongestionLevel_3;
    }

    @Override
    public int getCumulativeCongestionLevel() {
        int level = 0;
        for (Integer lev : lstUserPartCongestionLevel.values()) {
            if (level < lev) {
                level = lev;
            }
        }
        int lev2 = getMemoryCongestionLevel();
        if (level < lev2) {
            level = lev2;
        }
        lev2 = getExecutorCongestionLevel();
        if (level < lev2) {
            level = lev2;
        }

        return level;
    }

    protected String getCumulativeCongestionLevelString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CongestionLevel=[");

        int i1 = 0;
        int lev2 = getMemoryCongestionLevel();
        if (lev2 > 0) {
            sb.append("MemoryCongestionLevel=");
            sb.append(lev2);
        }
        lev2 = getExecutorCongestionLevel();
        if (lev2 > 0) {
            if (i1 == 0)
                i1 = 1;
            else
                sb.append(", ");
            sb.append("ExecutorCongestionLevel=");
            sb.append(lev2);
        }
        for (Entry<String, Integer> entry : lstUserPartCongestionLevel.entrySet()) {
            lev2 = entry.getValue();
            if (lev2 > 0) {
                if (i1 == 0)
                    i1 = 1;
                else
                    sb.append(", ");
                sb.append("UserPartCongestion=");
                sb.append(entry.getKey());
                sb.append("-");
                sb.append(lev2);
            }
        }

        if (this.stack.isCongControl_blockingIncomingTcapMessages()) {
            lev2 = getCumulativeCongestionLevel();
            if (lev2 == 3) {
                sb.append(", all incoming TCAP messages will be rejected");
            }
            if (lev2 == 2) {
                sb.append(", new incoming TCAP dialogs will be rejected");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
