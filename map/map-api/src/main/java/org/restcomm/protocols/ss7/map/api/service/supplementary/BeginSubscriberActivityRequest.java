
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
 MAP V1: BeginSubscriberActivity ::= OPERATION--Timer m ARGUMENT beginSubscriberActivityArg BeginSubscriberActivityArg
 *
 * BeginSubscriberActivityArg ::= SEQUENCE { imsi IMSI, originatingEntityNumber ISDN-AddressString }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BeginSubscriberActivityRequest extends SupplementaryMessage {

    IMSI getImsi();

    ISDNAddressString getOriginatingEntityNumber();

}
