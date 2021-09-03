
package org.restcomm.protocols.ss7.cap.api.EsiGprs;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;

/**
 *
 attachChangeOfPositionSpecificInformation [0] SEQUENCE { locationInformationGPRS [0] LocationInformationGPRS OPTIONAL, ... },
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AttachChangeOfPositionSpecificInformation extends Serializable {

    LocationInformationGPRS getLocationInformationGPRS();

}