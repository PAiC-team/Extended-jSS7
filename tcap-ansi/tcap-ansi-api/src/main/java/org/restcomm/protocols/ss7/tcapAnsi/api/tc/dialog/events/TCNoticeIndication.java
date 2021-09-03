
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import java.io.Serializable;

import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface TCNoticeIndication extends Serializable {

    SccpAddress getLocalAddress();

    void setLocalAddress(SccpAddress val);

    SccpAddress getRemoteAddress();

    void setRemoteAddress(SccpAddress val);

    ReturnCauseValue getReportCause();

    void setReportCause(ReturnCauseValue val);

    /*
     * This value can be equal null if TCAP can not match any existant Dialog to the incoming data
     */
    Dialog getDialog();

    void setDialog(Dialog val);

}
