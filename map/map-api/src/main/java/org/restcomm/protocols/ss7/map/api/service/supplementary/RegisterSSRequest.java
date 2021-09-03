
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
MAP V2:

registerSS OPERATION ::= {
  --Timer m
  ARGUMENT RegisterSS-Arg
  RESULT SS-Info
  -- optional
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | bearerServiceNotProvisioned | teleserviceNotProvisioned |
           callBarred | illegalSS-Operation | ss-ErrorStatus | ss-Incompatibility}
  CODE local:10
}

RegisterSS-Arg ::= SEQUENCE {
  ss-Code                SS-Code,
  basicService           BasicServiceCode OPTIONAL,
  forwardedToNumber      [4] AddressString OPTIONAL,
  forwardedToSubaddress  [6] ISDN-SubaddressString OPTIONAL,
  noReplyConditionTime   [5] NoReplyConditionTime OPTIONAL,
  ...,
  defaultPriority        [7] EMLPP-Priority OPTIONAL,
  nbrUser                [8] MC-Bearers OPTIONAL,
  longFTN-Supported      [9] NULL OPTIONAL
}

NoReplyConditionTime ::= INTEGER (5..30)

MC-Bearers ::= INTEGER (1..7)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RegisterSSRequest extends SupplementaryMessage {

    SSCode getSsCode();

    BasicServiceCode getBasicService();

    AddressString getForwardedToNumber();

    ISDNAddressString getForwardedToSubaddress();

    Integer getNoReplyConditionTime();

    EMLPPPriority getDefaultPriority();

    Integer getNbrUser();

    ISDNAddressString getLongFTNSupported();

}