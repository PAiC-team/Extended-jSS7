package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
<code>
SubscriberIdentity ::= CHOICE {
  imsi     [0] IMSI,
  msisdn   [1] ISDN-AddressString
}
</code>
 *
 * @author amit bhayani
 *
 */
public interface SubscriberIdentity extends Serializable {

    IMSI getIMSI();

    ISDNAddressString getMSISDN();

}
