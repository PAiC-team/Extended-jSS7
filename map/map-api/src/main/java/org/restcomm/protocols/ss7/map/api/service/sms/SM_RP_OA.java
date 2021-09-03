package org.restcomm.protocols.ss7.map.api.service.sms;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 * SM-RP-OA ::= CHOICE { msisdn [2] ISDN-AddressString, serviceCentreAddressOA [4] AddressString, noSM-RP-OA [5] NULL}
 *
 * Only one method getMsisdn() or getServiceCentreAddressOA() will return the non null value If all these methods return null -
 * this means noSM-RP-OA value
 *
 * @author sergey vetyutnev
 *
 */
public interface SM_RP_OA extends Serializable {

    ISDNAddressString getMsisdn();

    AddressString getServiceCentreAddressOA();

}
