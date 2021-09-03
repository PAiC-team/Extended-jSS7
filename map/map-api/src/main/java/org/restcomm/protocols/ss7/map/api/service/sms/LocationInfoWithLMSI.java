
package org.restcomm.protocols.ss7.map.api.service.sms;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;

/**
 *
<code>
LocationInfoWithLMSI ::= SEQUENCE {
  networkNode-Number    [1] ISDN-AddressString,
  lmsi                  LMSI OPTIONAL,
  extensionContainer    ExtensionContainer OPTIONAL,
  ...,
  gprsNodeIndicator     [5] NULL OPTIONAL,
  -- gprsNodeIndicator is set only if the SGSN number is sent as the
  -- Network Node Number
  additional-Number     [6] Additional-Number OPTIONAL
  -- NetworkNode-number can be either msc-number or sgsn-number

Additional-Number ::= CHOICE {
    msc-Number          [0] ISDN-AddressString,
    sgsn-Number         [1] ISDN-AddressString
}
-- additional-number can be either msc-number or sgsn-number
-- if received networkNode-number is msc-number then the
-- additional number is sgsn-number
-- if received networkNode-number is sgsn-number then the
-- additional number is msc-number
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LocationInfoWithLMSI extends Serializable {

    ISDNAddressString getNetworkNodeNumber();

    LMSI getLMSI();

    MAPExtensionContainer getExtensionContainer();

    boolean getGprsNodeIndicator();

    AdditionalNumber getAdditionalNumber();

}
