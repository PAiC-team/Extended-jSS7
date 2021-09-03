package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V1-2-3:
 *
 * MAP V3: reportSM-DeliveryStatus OPERATION ::= { --Timer s ARGUMENT ReportSM-DeliveryStatusArg RESULT
 * ReportSM-DeliveryStatusRes -- optional ERRORS { dataMissing | unexpectedDataValue | unknownSubscriber |
 * messageWaitingListFull} CODE local:47 }
 *
 * MAP V2: ReportSM-DeliveryStatus ::= OPERATION --Timer s ARGUMENT reportSM-DeliveryStatusArg ReportSM-DeliveryStatusArg RESULT
 * storedMSISDN ISDN-AddressString -- optional -- storedMSISDN must be absent in version 1 -- storedMSISDN must be present in
 * version greater 1 ERRORS { DataMissing, -- DataMissing must not be used in version 1 UnexpectedDataValue, UnknownSubscriber,
 * MessageWaitingListFull}
 *
 * MAP V3: ReportSM-DeliveryStatusArg ::= SEQUENCE { msisdn ISDN-AddressString, serviceCentreAddress AddressString,
 * sm-DeliveryOutcome SM-DeliveryOutcome, absentSubscriberDiagnosticSM [0] AbsentSubscriberDiagnosticSM OPTIONAL,
 * extensionContainer [1] ExtensionContainer OPTIONAL, ..., gprsSupportIndicator [2] NULL OPTIONAL, -- gprsSupportIndicator is
 * set only if the SMS-GMSC supports -- handling of two delivery outcomes deliveryOutcomeIndicator [3] NULL OPTIONAL, --
 * DeliveryOutcomeIndicator is set when the SM-DeliveryOutcome -- is for GPRS additionalSM-DeliveryOutcome [4]
 * SM-DeliveryOutcome OPTIONAL, -- If received, additionalSM-DeliveryOutcome is for GPRS -- If DeliveryOutcomeIndicator is set,
 * then AdditionalSM-DeliveryOutcome shall be absent additionalAbsentSubscriberDiagnosticSM [5] AbsentSubscriberDiagnosticSM
 * OPTIONAL -- If received additionalAbsentSubscriberDiagnosticSM is for GPRS -- If DeliveryOutcomeIndicator is set, then
 * AdditionalAbsentSubscriberDiagnosticSM -- shall be absent }
 *
 * MAP V2: ReportSM-DeliveryStatusArg ::= SEQUENCE { msisdn ISDN-AddressString, serviceCentreAddress AddressString,
 * sm-DeliveryOutcome SM-DeliveryOutcome OPTIONAL, -- sm-DeliveryOutcome must be absent in version 1 -- sm-DeliveryOutcome must
 * be present in version greater 1 ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReportSMDeliveryStatusRequest extends SmsMessage {

    ISDNAddressString getMsisdn();

    AddressString getServiceCentreAddress();

    SMDeliveryOutcome getSMDeliveryOutcome();

    Integer getAbsentSubscriberDiagnosticSM();

    MAPExtensionContainer getExtensionContainer();

    boolean getGprsSupportIndicator();

    boolean getDeliveryOutcomeIndicator();

    SMDeliveryOutcome getAdditionalSMDeliveryOutcome();

    Integer getAdditionalAbsentSubscriberDiagnosticSM();

}
