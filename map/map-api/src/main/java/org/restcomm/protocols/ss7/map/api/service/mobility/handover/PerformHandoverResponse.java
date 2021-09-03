
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V1: PerformHO-Res ::= SEQUENCE { handoverNumber ISDN-AddressString, accessSignalInfo ExternalSignalInfo}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PerformHandoverResponse extends MobilityMessage {

    ISDNAddressString getHandoverNumber();

    ExternalSignalInfo getAccessSignalInfo();

}
