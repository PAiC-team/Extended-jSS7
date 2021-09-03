
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
 ServingNodeAddress ::= CHOICE { msc-Number [0] ISDN-AddressString, sgsn-Number [1] ISDN-AddressString, mme-Number [2]
 * DiameterIdentity }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ServingNodeAddress extends Serializable {

    ISDNAddressString getMscNumber();

    ISDNAddressString getSgsnNumber();

    DiameterIdentity getMmeNumber();

}
