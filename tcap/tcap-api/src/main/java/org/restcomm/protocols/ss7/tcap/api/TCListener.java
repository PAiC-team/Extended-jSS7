
package org.restcomm.protocols.ss7.tcap.api;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCNoticeIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface TCListener {

    // dialog handlers
    /**
     * Invoked for TC_UNI. See Q.771 3.1.2.2.2.1
     */
    void onTCUni(TCUniIndication tcUniIndication);

    /**
     * Invoked for TC_BEGIN. See Q.771 3.1.2.2.2.1
     */
    void onTCBegin(TCBeginIndication tcBeginIndication);

    /**
     * Invoked for TC_CONTINUE dialog primitive. See Q.771 3.1.2.2.2.2/3.1.2.2.2.3
     *
     * @param tcapContinueIndication
     */
    void onTCContinue(TCContinueIndication tcapContinueIndication);

    /**
     * Invoked for TC_END dialog primitive. See Q.771 3.1.2.2.2.4
     *
     * @param tcapEndIndication
     */
    void onTCEnd(TCEndIndication tcapEndIndication);

    /**
     * Invoked for TC-U-Abort primitive(P-Abort-Cause is present.). See Q.771 3.1.2.2.2.4
     *
     * @param tcapUserAbortIndication
     */
    void onTCUserAbort(TCUserAbortIndication tcapUserAbortIndication);

    /**
     * Invoked TC-P-Abort (when dialog has been terminated by some unpredicatable environment cause). See Q.771 3.1.4.2
     *
     * @param tcapProviderAbortIndication
     */
    void onTCPAbort(TCPAbortIndication tcapProviderAbortIndication);

    /**
     * Invoked when TC-Notice primitive has been received. A TC-NOTICE indication primitive is only passed to the TC-user if the
     * requested service (i.e. transfer of components) cannot be provided (the network layer cannot deliver the embedded message
     * to the remote node) and the TC-user requested the return option in the Quality of Service parameter of the dialogue
     * handling request primitive.
     *
     * @param tcapNoticeIndication
     */
    void onTCNotice(TCNoticeIndication tcapNoticeIndication);

    /**
     * Called once dialog is released. It is invoked once primitives are delivered. Indicates that stack has no reference, and
     * dialog object is considered invalid.
     *
     * @param tcapDialog
     */
    void onDialogReleased(Dialog tcapDialog);

    /**
     *
     * @param tcapInvokeRequest
     */
    void onInvokeTimeout(Invoke tcapInvokeRequest);

    /**
     * Called once dialog times out. Once this method is called, dialog cant be used anymore.
     *
     * @param tcapDialog
     */
    void onDialogTimeout(Dialog tcapDialog);

}
