package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import java.io.Serializable;

import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface TCNoticeIndication extends Serializable {

    SccpAddress getLocalAddress();

    void setLocalAddress(SccpAddress sccpCallingPartyAddress);

    SccpAddress getRemoteAddress();

    void setRemoteAddress(SccpAddress sccpCalledPartyAddress);

    ReturnCauseValue getReportCause();

    void setReportCause(ReturnCauseValue returnCauseValue);

    /*
     * This value can be equal null if TCAP can not match any existent Dialog to the incoming data
     */
    Dialog getDialog();

    void setDialog(Dialog val);

}
