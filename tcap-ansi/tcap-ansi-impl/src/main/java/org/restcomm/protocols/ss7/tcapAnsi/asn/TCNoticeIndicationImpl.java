package org.restcomm.protocols.ss7.tcapAnsi.asn;

import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCNoticeIndication;

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
        return localAddress;
    }

    public void setLocalAddress(SccpAddress val) {
        localAddress = val;
    }

    public SccpAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(SccpAddress val) {
        remoteAddress = val;
    }

    public ReturnCauseValue getReportCause() {
        return reportCause;
    }

    public void setReportCause(ReturnCauseValue val) {
        reportCause = val;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog val) {
        dialog = val;
    }
}
