
package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

/**
 * <p>
 * The TP-Status field indicates the status of a previously submitted SMS-SUBMIT and certain SMS COMMANDS for which a Status
 * -Report has been requested.
 * </p>
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public interface Status extends Serializable {

    /**
     * Short message transaction completed
     */

    // Short message received by the SME
    int SMS_RECEIVED = 0;

    // Short message forwarded by the SC to the SME but the SC is unable to
    // confirm delivery
    int SMS_UNABLE_TO_CONFIRM_DELIVERY = 1;

    // Short message replaced by the SC
    int SMS_REPLACED_BY_SC = 2;

    /**
     * Temporary error, SC still trying to transfer SM
     */

    int CONGESTION_STILL_TRYING = 32;

    int SME_BUSY_STILL_TRYING = 33;

    int NO_RESPOSNE_FROM_SME_STILL_TRYING = 34;

    int SERVICE_REJECTED_STILL_TRYING = 35;

    int QUALITY_OF_SERVICE_NOT_AVAILABLE_STILL_TRYING = 36;

    int ERROR_IN_SME_STILL_TRYING = 37;

    /**
     * Permanent error, SC is not making any more transfer attempts
     */
    int REMOTE_PROCEDURE_ERROR = 64;

    int INCOMPATIBLE_DESTINATION = 65;

    int CONNECTION_REJECTED_BY_SME = 66;

    int NOT_OBTAINABLE = 67;

    int QOS_NOT_AVAILABLE = 68;

    int NO_INTERWORKING_AVAILABLE = 69;

    int SMS_VALIDITY_PERIOD_EXPIRED = 70;

    int SMS_DELETED_BY_ORIGINATING_SME = 71;

    int SMS_DELETED_BY_ADMINISTRATOR = 72;

    // The SM may have previously existed in the SC but the SC no longer has
    // knowledge of it or the SM may never have previously existed in the SC
    int SMS_DOES_NOT_EXIST = 73;

    /**
     * Temporary error, SC is not making any more transfer attempts
     */

    int CONGESTION = 96;

    int SME_BUSY = 97;

    int NO_REPONSE_FROM_SME = 98;

    int SERVICE_REJECTED = 99;

    int QUALITY_OF_SERVICE_NOT_AVAILABLE = 100;

    int ERROR_IN_SME = 101;

    /**
     *
     * @return one of the status codes as declared above
     */
    int getCode();

}
