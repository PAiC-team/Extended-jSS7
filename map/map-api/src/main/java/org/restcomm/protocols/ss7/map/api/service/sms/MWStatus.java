package org.restcomm.protocols.ss7.map.api.service.sms;

import java.io.Serializable;

/**
 *
<code>
MW-Status ::= BIT STRING {
  sc-AddressNotIncluded (0), mnrf-Set (1), mcef-Set (2) , mnrg-Set (3)
} (SIZE (6..16))
-- exception handling:
-- bits 4 to 15 shall be ignored if received and not understood
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MWStatus extends Serializable {

    boolean getScAddressNotIncluded();

    boolean getMnrfSet();

    boolean getMcefSet();

    boolean getMnrgSet();

}
