package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * mt-ForwardSM OPERATION ::= { --Timer ml ARGUMENT MT-ForwardSM-Arg RESULT MT-ForwardSM-Res -- optional ERRORS { systemFailure
 * | dataMissing | unexpectedDataValue | facilityNotSupported | unidentifiedSubscriber | illegalSubscriber | illegalEquipment |
 * subscriberBusyForMT-SMS | sm-DeliveryFailure | absentSubscriberSM} CODE local:44 }
 *
 * MT-ForwardSM-Arg ::= SEQUENCE { sm-RP-DA SM-RP-DA, sm-RP-OA SM-RP-OA, sm-RP-UI SignalInfo, moreMessagesToSend NULL OPTIONAL,
 * extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MtForwardShortMessageRequest extends SmsMessage {

    SM_RP_DA getSM_RP_DA();

    SM_RP_OA getSM_RP_OA();

    SmsSignalInfo getSM_RP_UI();

    boolean getMoreMessagesToSend();

    MAPExtensionContainer getExtensionContainer();

}
