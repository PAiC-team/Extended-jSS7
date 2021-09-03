
package org.restcomm.protocols.ss7.cap.api.EsiGprs;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;

/**
 *
 pdp-ContextchangeOfPositionSpecificInformation [1] SEQUENCE { accessPointName [0] AccessPointName {bound} OPTIONAL,
 * chargingID [1] GPRSChargingID OPTIONAL, locationInformationGPRS [2] LocationInformationGPRS OPTIONAL, endUserAddress [3]
 * EndUserAddress {bound} OPTIONAL, qualityOfService [4] QualityOfService OPTIONAL, timeAndTimeZone [5] TimeAndTimezone {bound}
 * OPTIONAL, ..., gGSNAddress [6] GSN-Address OPTIONAL },
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PdpContextChangeOfPositionSpecificInformation extends Serializable {

    AccessPointName getAccessPointName();

    GPRSChargingID getChargingID();

    LocationInformationGPRS getLocationInformationGPRS();

    EndUserAddress getEndUserAddress();

    QualityOfService getQualityOfService();

    TimeAndTimezone getTimeAndTimezone();

    GSNAddress getGSNAddress();

}