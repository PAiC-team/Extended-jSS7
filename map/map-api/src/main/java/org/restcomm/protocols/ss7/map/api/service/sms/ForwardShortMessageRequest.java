package org.restcomm.protocols.ss7.map.api.service.sms;

/**
 *
 MAP V1-2:
 *
 * ForwardSM ::= OPERATION --Timer ml ARGUMENT forwardSM-Arg ForwardSM-Arg RESULT ERRORS { SystemFailure, DataMissing, --
 * DataMissing must not be used in version 1 UnexpectedDataValue, FacilityNotSupported, UnidentifiedSubscriber,
 * IllegalSubscriber, IllegalEquipment, -- IllegalEquipment must not be used in version 1 AbsentSubscriber,
 * SubscriberBusyForMT-SMS, -- SubscriberBusyForMT-SMS must not be used in version 1 SM-DeliveryFailure}
 *
 * ForwardSM-Arg ::= SEQUENCE { sm-RP-DA SM-RP-DA, sm-RP-OA SM-RP-OA, sm-RP-UI SignalInfo, moreMessagesToSend NULL OPTIONAL, --
 * moreMessagesToSend must be absent in version 1 ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ForwardShortMessageRequest extends SmsMessage {

    SM_RP_DA getSM_RP_DA();

    SM_RP_OA getSM_RP_OA();

    SmsSignalInfo getSM_RP_UI();

    boolean getMoreMessagesToSend();

}
