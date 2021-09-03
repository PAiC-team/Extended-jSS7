
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.GlobalCellId;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.TMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LongGroupId;

/**
 *
 MAP V3:
 *
 * sendGroupCallInfo OPERATION ::= { --Timer m ARGUMENT SendGroupCallInfoArg RESULT SendGroupCallInfoRes ERRORS { systemFailure
 * | ongoingGroupCall | unexpectedDataValue | dataMissing | teleserviceNotProvisioned | unknownSubscriber} CODE local:84 }
 *
 * SendGroupCallInfoArg ::= SEQUENCE { requestedInfo RequestedInfo, groupId Long-GroupId, teleservice Ext-TeleserviceCode,
 * cellId [0] GlobalCellId OPTIONAL, imsi [1] IMSI OPTIONAL, tmsi [2] TMSI OPTIONAL, additionalInfo [3] AdditionalInfo OPTIONAL,
 * talkerPriority [4] TalkerPriority OPTIONAL, cksn [5] Cksn OPTIONAL, extensionContainer [6] ExtensionContainer OPTIONAL, ... }
 *
 * Cksn ::= OCTET STRING (SIZE (1)) -- The internal structure is defined in 3GPP TS 24.008
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendGroupCallInfoRequest extends CallHandlingMessage {

     RequestedInfo getRequestedInfo();

     LongGroupId getGroupId();

     ExtTeleserviceCode getTeleservice();

     GlobalCellId getCellId();

     IMSI getImsi();

     TMSI getTmsi();

     AdditionalInfo getAdditionalInfo();

     TalkerPriority getTalkerPriority();

     byte[] getCksn();

     MAPExtensionContainer getExtensionContainer();

}
