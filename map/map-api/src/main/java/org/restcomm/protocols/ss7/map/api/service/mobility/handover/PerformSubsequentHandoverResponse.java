
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 RESULT accessSignalInfo ExternalSignalInfo
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PerformSubsequentHandoverResponse extends MobilityMessage {

    ExternalSignalInfo getAccessSignalInfo();

}
