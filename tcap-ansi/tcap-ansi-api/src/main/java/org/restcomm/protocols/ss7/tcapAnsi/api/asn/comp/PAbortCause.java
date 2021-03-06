
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ParseException;

/**
 *
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public enum PAbortCause {

    /**
     * Unrecognized Package Type. Package Type is not defined in Chapter T1.114.3.
     * PackageType == unidirectional, queryWithPerm, queryWithoutPerm, response,
     *   conversationWithPerm, conversationWithoutPerm, abort
     */
    UnrecognizedPackageType(1),

    /**
     * An unexpected or undefined identifier was received within the Transaction portion.
     */
    IncorrectTransactionPortion(2),

    /**
     * A fundamental encoding problem (e.g., bad length).
     */
    BadlyStructuredTransactionPortion(3),

    /**
     * The received Transaction ID is derivable but
     * does not reflect a transaction currently in progress.
     */
    UnassignedRespondingTransactionID(4),

    /**
     * This problem is for further study.
     */
    PermissionToReleaseProblem(5),

    /**
     * Sufficient resources are not available at the Transaction sublayer
     * to establish a transaction.
     */
    ResourceUnavailable(6),

    /**
     * The identifier following the last field of the transaction portion was
     * not the dialogue portion identifier as defined in Chapter T1.114.3.
     */
    UnrecognizedDialoguePortionID(7),

    /**
     * A fundamental encoding problem (e.g., multiple application contexts in one dialogue portion).
     */
    BadlyStructuredDialoguePortion(8),

    /**
     * The dialogue portion is missing where it is mandatory
     * (e.g., in the first backward message after a Query with a proposed application context).
     */
    MissingDialoguePortion(9),

    /**
     * The contents of the Dialogue portion are inconsistent with the state of the transaction
     * (e.g., a Query with an empty Dialogue portion or a message after the first backward Conversation
     * that contains an application context).
     */
    InconsistentDialoguePortion(10);

    private int type = -1;

    PAbortCause(int t) {
        this.type = t;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    public static PAbortCause getFromInt(int t) throws ParseException {

        switch (t) {
            case 1:
                return UnrecognizedPackageType;
            case 2:
                return IncorrectTransactionPortion;
            case 3:
                return BadlyStructuredTransactionPortion;
            case 4:
                return UnassignedRespondingTransactionID;
            case 5:
                return PermissionToReleaseProblem;
            case 6:
                return ResourceUnavailable;
            case 7:
                return UnrecognizedDialoguePortionID;
            case 8:
                return BadlyStructuredDialoguePortion;
            case 9:
                return MissingDialoguePortion;
            case 10:
                return InconsistentDialoguePortion;
            default:
                throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion, "Wrong value of response: " + t);
        }
    }
}
