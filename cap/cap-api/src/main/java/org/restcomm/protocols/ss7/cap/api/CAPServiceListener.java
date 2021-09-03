
package org.restcomm.protocols.ss7.cap.api;

import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceListener {
    /**
     * Invoked when TC-U-ERROR primitive is received from the other peer
     *
     * @param capDialog
     * @param invokeId
     * @param capErrorMessage
     */
    void onErrorComponent(CAPDialog capDialog, Long invokeId, CAPErrorMessage capErrorMessage);

    /**
     * Invoked when TC-U-REJECT primitive is received from the other peer
     *
     * @param capDialog
     * @param invokeId This parameter is optional and may be the null
     * @param problem
     * @param isLocalOriginated true: local originated Reject (rejecting a bad incoming primitive by a local side) false: remote
     *        originated Reject (rejecting a bad outgoing primitive by a peer)
     */
    void onRejectComponent(CAPDialog capDialog, Long invokeId, Problem problem, boolean isLocalOriginated);

    /**
     * Invoked when no answer from the other peer for a long time - for sending the a reject for the Invoke
     *
     * @param capDialog
     * @param invokeId
     */
    void onInvokeTimeout(CAPDialog capDialog, Long invokeId);

    /**
     * Called when any CAPMessage received (Invoke, ReturnResult, ReturnResultLast components) This component will be invoked
     * before the special service component
     *
     * @param capMessage
     */
    void onCAPMessage(CAPMessage capMessage);

}
