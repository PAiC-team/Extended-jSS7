
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * ss-InvocationNotification OPERATION ::= { --Timer m ARGUMENT SS-InvocationNotificationArg RESULT SS-InvocationNotificationRes
 * -- optional ERRORS { dataMissing | unexpectedDataValue | unknownSubscriber} CODE local:72 }
 *
 * SS-InvocationNotificationArg ::= SEQUENCE { imsi [0] IMSI, msisdn [1] ISDN-AddressString, ss-Event [2] SS-Code, -- The
 * following SS-Code values are allowed : -- ect SS-Code ::= '00110001'B -- multiPTY SS-Code ::= '01010001'B -- cd SS-Code ::=
 * '00100100'B -- ccbs SS-Code ::= '01000100'B ss-EventSpecification [3] SS-EventSpecification OPTIONAL, extensionContainer [4]
 * ExtensionContainer OPTIONAL, ..., b-subscriberNumber [5] ISDN-AddressString OPTIONAL, ccbs-RequestState [6] CCBS-RequestState
 * OPTIONAL }
 *
 * SS-EventSpecification ::= SEQUENCE SIZE (1..2) OF AddressString
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSInvocationNotificationRequest extends SupplementaryMessage {

    IMSI getImsi();

    ISDNAddressString getMsisdn();

    SSCode getSsEvent();

    ArrayList<AddressString> getSsEventSpecification();

    MAPExtensionContainer getExtensionContainer();

    ISDNAddressString getBSubscriberNumber();

    CCBSRequestState getCcbsRequestState();

}