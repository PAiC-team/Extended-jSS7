
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingResult;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingRollOver;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;

/**
 *
 applyChargingReportGPRS OPERATION ::= { ARGUMENT ApplyChargingReportGPRSArg RETURN RESULT TRUE ERRORS {missingParameter |
 * unexpectedComponentSequence | unexpectedParameter | unexpectedDataValue | parameterOutOfRange | systemFailure | taskRefused |
 * unknownPDPID} CODE opcode-applyChargingReportGPRS} -- Direction gprsSSF -> gsmSCF,Timer Tacrg -- The ApplyChargingReportGPRS
 * operation provides the feedback from the gprsSCF to the gsmSCF -- CSE-controlled GPRS session charging mechanism.
 *
 * ApplyChargingReportGPRSArg ::= SEQUENCE { chargingResult [0] ChargingResult, qualityOfService [1] QualityOfService OPTIONAL,
 * active [2] BOOLEAN DEFAULT TRUE, pDPID [3] PDPID OPTIONAL, ..., chargingRollOver [4] ChargingRollOver OPTIONAL }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ApplyChargingReportGPRSRequest extends GprsMessage {

    ChargingResult getChargingResult();

    QualityOfService getQualityOfService();

    boolean getActive();

    PDPID getPDPID();

    ChargingRollOver getChargingRollOver();

}