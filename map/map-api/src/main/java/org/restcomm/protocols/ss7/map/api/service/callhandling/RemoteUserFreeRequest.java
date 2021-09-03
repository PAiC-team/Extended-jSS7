
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CCBSFeature;

/**
 *
 MAP V3:
 *
 * remoteUserFree OPERATION ::= { --Timer ml ARGUMENT RemoteUserFreeArg RESULT RemoteUserFreeRes ERRORS { unexpectedDataValue |
 * dataMissing | incompatibleTerminal | absentSubscriber | systemFailure | busySubscriber} CODE local:75 }
 *
 * RemoteUserFreeArg ::= SEQUENCE{ imsi [0] IMSI, callInfo [1] ExternalSignalInfo, ccbs-Feature [2] CCBS-Feature,
 * translatedB-Number [3] ISDN-AddressString, replaceB-Number [4] NULL OPTIONAL, alertingPattern [5] AlertingPattern OPTIONAL,
 * extensionContainer [6] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RemoteUserFreeRequest extends CallHandlingMessage {

     IMSI getImsi();

     ExternalSignalInfo getCallInfo();

     CCBSFeature getCcbsFeature();

     ISDNAddressString getTranslatedBNumber();

     boolean getReplaceBNumber();

     AlertingPattern getAlertingPattern();

     MAPExtensionContainer getExtensionContainer();

}
