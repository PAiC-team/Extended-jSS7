
package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.restcomm.protocols.ss7.tcap.asn.ParseException;

/**
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 */
public enum PAbortCauseType {

    // it is encoded as INT

    /**
     * The message type is not one of those defined (Begin, Continue, End, Abort, Uni)
     */
    UnrecognizedMessageType(0),

    /**
     * A transaction ID has been received for which a transaction does not exist at the receiving node.
     */
    UnrecognizedTxID(1),

    /**
     * The transaction portion of the received message does not conform to the X.209 encoding rules as outlined in 4.1/Q.773.
     */
    BadlyFormattedTxPortion(2),

    /**
     * The elemental structure within the transaction portion of the received message does not conform to the rules for the
     * transaction portion defined in 3.1/Q.773.
     */
    IncorrectTxPortion(3),

    /**
     * Sufficient resources are not available to start a transaction. Now it is limited by total active Dialogs count <= max
     * possible
     */
    ResourceLimitation(4),

    // Next vanues are not there in specs, but used locally
    /**
     * - TC-Abort has been received with abortSourceType==Provider and no AbortSource is given - abnormal dialog state for a
     * receiving message - bad dialog APDU type received for current Dialog state
     */
    AbnormalDialogue(126),

    /**
     * TC-Abort has been received with NoCommonDialoguePortion reason
     */
    NoCommonDialoguePortion(127),

    /**
     * TC-Abort has been received with NoReasonGiven reason
     */
    NoReasonGiven(128);

    private int type = -1;

    PAbortCauseType(int t) {
        this.type = t;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    public static PAbortCauseType getFromInt(int providerAbortCauseType) throws ParseException {

        switch (providerAbortCauseType) {
            case 0:
                return UnrecognizedMessageType;
            case 1:
                return UnrecognizedTxID;
            case 2:
                return BadlyFormattedTxPortion;
            case 3:
                return IncorrectTxPortion;
            case 4:
                return ResourceLimitation;
            case 126:
                return AbnormalDialogue;
            case 127:
                return NoCommonDialoguePortion;
            case 128:
                return NoReasonGiven;
            default:
                throw new ParseException(null, null, "Wrong value of response: " + providerAbortCauseType);
        }
    }
}
