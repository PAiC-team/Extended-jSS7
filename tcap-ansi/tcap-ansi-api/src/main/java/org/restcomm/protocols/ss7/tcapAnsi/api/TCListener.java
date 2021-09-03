
package org.restcomm.protocols.ss7.tcapAnsi.api;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCConversationIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCNoticeIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCPAbortIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCQueryIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCResponseIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCUniIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCUserAbortIndication;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface TCListener {

    void onTCUni(TCUniIndication ind);

    void onTCQuery(TCQueryIndication ind);

    void onTCConversation(TCConversationIndication ind);

    void onTCResponse(TCResponseIndication ind);

    void onTCUserAbort(TCUserAbortIndication ind);

    void onTCPAbort(TCPAbortIndication ind);

    void onTCNotice(TCNoticeIndication ind);

    /**
     * Called once dialog is released. It is invoked once primitives are delivered. Indicates that stack has no reference, and
     * dialog object is considered invalid.
     *
     * @param d
     */
    void onDialogReleased(Dialog d);

    /**
     *
     * @param tcInvokeRequest
     */
    void onInvokeTimeout(Invoke tcInvokeRequest);

    /**
     * Called once dialog times out. Once this method is called, dialog cant be used anymore.
     *
     * @param d
     */
    void onDialogTimeout(Dialog d);

}
