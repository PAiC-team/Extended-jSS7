
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
<code>
Additional-Number ::= CHOICE {
  msc-Number    [0] ISDN-AddressString,
  sgsn-Number   [1] ISDN-AddressString
}
-- additional-number can be either msc-number or sgsn-number
-- if received networkNode-number is msc-number then the
-- additional number is sgsn-number
-- if received networkNode-number is sgsn-number then the
-- additional number is msc-number
</code>
 *
 * @author amit bhayani
 *
 */
public interface AdditionalNumber extends Serializable {

    ISDNAddressString getMSCNumber();

    ISDNAddressString getSGSNNumber();

}
