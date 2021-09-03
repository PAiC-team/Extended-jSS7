
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
<code>
MNPInfoRes ::= SEQUENCE {
  routeingNumber           [0] RouteingNumber OPTIONAL,
  imsi                     [1] IMSI OPTIONAL,
  msisdn                   [2] ISDN-AddressString OPTIONAL,
  numberPortabilityStatus  [3] NumberPortabilityStatus OPTIONAL,
  extensionContainer       [4] ExtensionContainer OPTIONAL,
  ...
}
-- The IMSI parameter contains a generic IMSI, i.e. it is not tied necessarily to the
-- Subscriber. MCC and MNC values in this IMSI shall point to the Subscription Network of
-- the Subscriber. See 3GPP TS 23.066 [108].
</code>
 *
 * @author amit bhayani
 *
 */
public interface MNPInfoRes extends Serializable {

    RouteingNumber getRouteingNumber();

    IMSI getIMSI();

    ISDNAddressString getMSISDN();

    NumberPortabilityStatus getNumberPortabilityStatus();

    MAPExtensionContainer getExtensionContainer();

}
