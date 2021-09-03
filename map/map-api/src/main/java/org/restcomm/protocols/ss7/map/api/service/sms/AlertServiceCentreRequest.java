package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
 MAP V1-2:
 *
 * MAP V2: alertServiceCentre OPERATION ::= { --Timer s ARGUMENT AlertServiceCentreArg RETURN RESULT TRUE ERRORS { systemFailure
 * | dataMissing | unexpectedDataValue} CODE local:64 }
 *
 * MAP V1: alertServiceCentreWithoutResult ::= OPERATION --Timer s ARGUMENT alertServiceCentreArg AlertServiceCentreArg
 *
 *
 * MAP V1-2: AlertServiceCentreArg ::= SEQUENCE { msisdn ISDN-AddressString, serviceCentreAddress AddressString, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AlertServiceCentreRequest extends SmsMessage {

    ISDNAddressString getMsisdn();

    AddressString getServiceCentreAddress();

}
