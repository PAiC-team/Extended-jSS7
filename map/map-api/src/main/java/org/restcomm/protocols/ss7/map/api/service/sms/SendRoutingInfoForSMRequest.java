
package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;

/**
 *
<code>
MAP V1-2-3:

MAP V3: sendRoutingInfoForSM OPERATION ::= { --Timer m
  ARGUMENT RoutingInfoForSM-Arg
  RESULT RoutingInfoForSM-Res
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | facilityNotSupported | unknownSubscriber | teleserviceNotProvisioned | callBarred | absentSubscriberSM }
  CODE local:45
}

MAP V2: SendRoutingInfoForSM ::= OPERATION --Timer m
  ARGUMENT routingInfoForSM-Arg RoutingInfoForSM-Arg
  RESULT routingInfoForSM-Res RoutingInfoForSM-Res
  ERRORS { SystemFailure, DataMissing, UnexpectedDataValue, FacilityNotSupported, UnknownSubscriber, TeleserviceNotProvisioned, AbsentSubscriber, CallBarred }

MAP V3: RoutingInfoForSM-Arg ::= SEQUENCE {
  msisdn                  [0] ISDN-AddressString,
  sm-RP-PRI               [1] BOOLEAN,
  serviceCentreAddress    [2] AddressString,
  extensionContainer      [6] ExtensionContainer OPTIONAL,
  ... ,
  gprsSupportIndicator    [7] NULL OPTIONAL,
  -- gprsSupportIndicator is set only if the SMS-GMSC supports
  -- receiving of two numbers from the HLR
  sm-RP-MTI               [8] SM-RP-MTI OPTIONAL,
  sm-RP-SMEA              [9] SM-RP-SMEA OPTIONAL,
  sm-deliveryNotIntended  [10] SM-DeliveryNotIntended OPTIONAL,
  ip-sm-gwGuidanceIndicator   [11] NULL OPTIONAL,
  imsi                    [12] IMSI OPTIONAL,
  t4-Trigger-Indicator    [14] NULL OPTIONAL,
  singleAttemptDelivery   [13] NULL OPTIONAL,
  correlationID           [15] CorrelationID OPTIONAL
}

MAP V2: RoutingInfoForSM-Arg ::= SEQUENCE {
  msisdn                  [0] ISDN-AddressString,
  sm-RP-PRI               [1] BOOLEAN,
  erviceCentreAddress     [2] AddressString,
  teleservice             [5] TeleserviceCode OPTIONAL,
  -- teleservice must be absent in version greater 1 ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 * @author eva ogallar
 *
 */
public interface SendRoutingInfoForSMRequest extends SmsMessage {

    ISDNAddressString getMsisdn();

    boolean getSm_RP_PRI();

    AddressString getServiceCentreAddress();

    MAPExtensionContainer getExtensionContainer();

    boolean getGprsSupportIndicator();

    SM_RP_MTI getSM_RP_MTI();

    SM_RP_SMEA getSM_RP_SMEA();

    SMDeliveryNotIntended getSmDeliveryNotIntended();

    boolean getIpSmGwGuidanceIndicator();

    IMSI getImsi();

    boolean getT4TriggerIndicator();

    boolean getSingleAttemptDelivery();

    CorrelationID getCorrelationID();

    // for MAP V1 only
    TeleserviceCode getTeleservice();

}
