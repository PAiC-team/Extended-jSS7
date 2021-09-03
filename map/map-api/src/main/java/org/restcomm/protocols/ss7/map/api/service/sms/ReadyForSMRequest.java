
package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MAP V2-3:

MAP V3: readyForSM OPERATION ::= {
  --Timer m
  ARGUMENT ReadyForSM-Arg
  RESULT ReadyForSM-Res
  -- optional
  ERRORS { dataMissing | unexpectedDataValue | facilityNotSupported | unknownSubscriber }
  CODE local:66
}

MAP V2:
ReadyForSM ::= OPERATION
--Timer m
ARGUMENT readyForSM-Arg
ReadyForSM-Arg
RESULT
ERRORS { DataMissing, UnexpectedDataValue, FacilityNotSupported, UnknownSubscriber }


MAP V3:

ReadyForSM-Arg ::= SEQUENCE {
  imsi                   [0] IMSI,
  alertReason            AlertReason,
  alertReasonIndicator   NULL OPTIONAL,
  -- alertReasonIndicator is set only when the alertReason
  -- sent to HLR is for GPRS
  extensionContainer     ExtensionContainer OPTIONAL,
  ...,
  additionalAlertReasonIndicator [1] NULL OPTIONAL
  -- additionalAlertReasonIndicator is set only when the alertReason
  -- sent to HLR is for IP-SM-GW
}

MAP V2:

ReadyForSM-Arg ::= SEQUENCE {
  imsi         [0] IMSI,
  alertReason  AlertReason,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReadyForSMRequest extends SmsMessage {

    IMSI getImsi();

    AlertReason getAlertReason();

    boolean getAlertReasonIndicator();

    MAPExtensionContainer getExtensionContainer();

    boolean getAdditionalAlertReasonIndicator();

}
