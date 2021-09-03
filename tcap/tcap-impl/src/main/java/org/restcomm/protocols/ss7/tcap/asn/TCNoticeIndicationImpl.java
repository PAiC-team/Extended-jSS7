package org.restcomm.protocols.ss7.tcap.asn;

import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCNoticeIndication;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TCNoticeIndicationImpl implements TCNoticeIndication {

    private SccpAddress localAddress;
    private SccpAddress remoteAddress;
    private ReturnCauseValue reportCause;
    private Dialog dialog;

    public SccpAddress getLocalAddress() {
        return this.localAddress;
    }

    public void setLocalAddress(SccpAddress sccpCallingPartyAddress) {
        this.localAddress = sccpCallingPartyAddress;
    }

    public SccpAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    public void setRemoteAddress(SccpAddress sccpCalledPartyAddress) {
        this.remoteAddress = sccpCalledPartyAddress;
    }

    public ReturnCauseValue getReportCause() {
        return this.reportCause;
    }

    public void setReportCause(ReturnCauseValue returnCauseValue) {
        this.reportCause = returnCauseValue;
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
