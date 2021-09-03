package org.restcomm.protocols.ss7.tools.traceparser;

import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.DialogImpl;
import org.restcomm.protocols.ss7.tcap.TCAPProviderImpl;
import org.restcomm.protocols.ss7.tcap.api.TCAPSendException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.TRPseudoState;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.Return;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCBeginMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCEndMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class DialogImplWrapper extends DialogImpl {

    private int acnValue;
    private int acnVersion;
    protected int curOpcOrig;
    protected int curOpc;

    public DialogImplWrapper(SccpAddress localAddress, SccpAddress remoteAddress, Long origTransactionId, boolean structured,
            ScheduledExecutorService executor, TCAPProviderImpl provider, int seqControl) {
        super(localAddress, remoteAddress, origTransactionId, structured, executor, provider, seqControl, true);
    }

    public int getAcnValue() {
        return acnValue;
    }

    public int getAcnVersion() {
        return acnVersion;
    }

    public void setAcnValue(int v) {
        acnValue = v;
    }

    public void setAcnVersion(int v) {
        acnVersion = v;
    }

    public void SetStateActive() {
        this.setState(TRPseudoState.Active);
    }

    private HashMap<Long, Invoke> invLst = new HashMap<Long, Invoke>();

    @Override
    protected Component[] processOperationsState(Component[] components) {

        if (components == null)
            return null;

        for (Component c : components) {
            // adding 1 to InvokeId: excluding 0 value
            c.setInvokeId(c.getInvokeId() + 1);

            boolean rev = false;
            if (curOpc == this.curOpcOrig) {
                if (c.getType() != ComponentType.Invoke)
                    rev = true;
            } else {
                if (c.getType() == ComponentType.Invoke)
                    rev = true;
            }
            if (rev) {
                c.setInvokeId(-c.getInvokeId());
            }

            switch (c.getType()) {
                case Invoke:
                    invLst.put(c.getInvokeId(), (Invoke) c);
                    break;

                case ReturnResult:
                case ReturnResultLast:
                    Return rr = (Return) c;
                    Invoke inv = invLst.get(c.getInvokeId());
                    if (rr.getOperationCode() == null) {
                        if (inv != null) {
                            rr.setOperationCode(inv.getOperationCode());
                        }
                    }
                    break;
            }
        }

        return components;
    }

    @Override
    public void processAbort(TCAbortMessage msg, SccpAddress localAddress2, SccpAddress remoteAddress2) {
        super.processAbort(msg, localAddress2, remoteAddress2);
    }

    @Override
    public void processEnd(TCEndMessage msg, SccpAddress localAddress2, SccpAddress remoteAddress2) {
        super.processEnd(msg, localAddress2, remoteAddress2);
    }

    @Override
    public void processContinue(TCContinueMessage msg, SccpAddress localAddress2, SccpAddress remoteAddress2) {
        super.processContinue(msg, localAddress2, remoteAddress2);
    }

    @Override
    public void processBegin(TCBeginMessage msg, SccpAddress localAddress2, SccpAddress remoteAddress2) {
        super.processBegin(msg, localAddress2, remoteAddress2);
    }

    public void send(TCUserAbortRequest event) throws TCAPSendException {
    }
}
