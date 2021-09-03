
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
 pDPContextEstablishmentAcknowledgementSpecificInformation [5] SEQUENCE { accessPointName [0] AccessPointName {bound}
 * OPTIONAL, chargingID [1] GPRSChargingID OPTIONAL, endUserAddress [2] EndUserAddress {bound} OPTIONAL, qualityOfService [3]
 * QualityOfService OPTIONAL, locationInformationGPRS [4] LocationInformationGPRS OPTIONAL, timeAndTimeZone [5] TimeAndTimezone
 * {bound} OPTIONAL, ..., gGSNAddress [6] GSN-Address OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDPContextEstablishmentAcknowledgementSpecificInformation extends Serializable {

    AccessPointName getAccessPointName();

    GPRSChargingID getChargingID();

    EndUserAddress getEndUserAddress();

    QualityOfService getQualityOfService();

    LocationInformationGPRS getLocationInformationGPRS();

    TimeAndTimezone getTimeAndTimezone();

    GSNAddress getGSNAddress();

}