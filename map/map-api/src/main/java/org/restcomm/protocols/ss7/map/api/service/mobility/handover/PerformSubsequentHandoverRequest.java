
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import org.restcomm.protocols.ss7.map.api.primitives.GlobalCellId;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V1:
 *
 * PerformSubsequentHandover ::= OPERATION --Timer m ARGUMENT performSubsequentHO-Arg PerformSubsequentHO-Arg RESULT
 * accessSignalInfo ExternalSignalInfo ERRORS { UnexpectedDataValue, UnknownBaseStation, UnknownMSC, InvalidTargetBaseStation,
 * SubsequentHandoverFailure}
 *
 * PerformSubsequentHO-Arg ::= SEQUENCE { targetCellId GlobalCellId, servingCellId GlobalCellId, targetMSC-Number
 * ISDN-AddressString, classmarkInfo [10] ClassmarkInfo OPTIONAL}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PerformSubsequentHandoverRequest extends MobilityMessage {

    GlobalCellId getTargetCellId();

    GlobalCellId getServingCellId();

    ISDNAddressString getTargetMSCNumber();

    ClassmarkInfo getClassmarkInfo();

}
