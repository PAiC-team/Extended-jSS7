package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * mo-ForwardSM OPERATION ::= { --Timer ml ARGUMENT MO-ForwardSM-Arg RESULT MO-ForwardSM-Res -- optional ERRORS { systemFailure
 * | unexpectedDataValue | facilityNotSupported | sm-DeliveryFailure} CODE local:46 }
 *
 * MO-ForwardSM-Arg ::= SEQUENCE { sm-RP-DA SM-RP-DA, sm-RP-OA SM-RP-OA, sm-RP-UI SignalInfo, extensionContainer
 * ExtensionContainer OPTIONAL, ... , imsi IMSI OPTIONAL }
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MoForwardShortMessageRequest extends SmsMessage {

    SM_RP_DA getSM_RP_DA();

    SM_RP_OA getSM_RP_OA();

    SmsSignalInfo getSM_RP_UI();

    MAPExtensionContainer getExtensionContainer();

    IMSI getIMSI();

}
