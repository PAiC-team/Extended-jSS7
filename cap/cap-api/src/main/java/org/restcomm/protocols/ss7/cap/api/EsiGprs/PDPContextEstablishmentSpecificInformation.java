
package org.restcomm.protocols.ss7.cap.api.EsiGprs;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPInitiationType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;

/**
 *
 pDPContextEstablishmentSpecificInformation [4] SEQUENCE { accessPointName [0] AccessPointName {bound} OPTIONAL,
 * endUserAddress [1] EndUserAddress {bound} OPTIONAL, qualityOfService [2] QualityOfService OPTIONAL, locationInformationGPRS
 * [3] LocationInformationGPRS OPTIONAL, timeAndTimeZone [4] TimeAndTimezone {bound} OPTIONAL, pDPInitiationType [5]
 * PDPInitiationType OPTIONAL, ..., secondaryPDP-context [6] NULL OPTIONAL },
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDPContextEstablishmentSpecificInformation extends Serializable {

    AccessPointName getAccessPointName();

    EndUserAddress getEndUserAddress();

    QualityOfService getQualityOfService();

    LocationInformationGPRS getLocationInformationGPRS();

    TimeAndTimezone getTimeAndTimezone();

    PDPInitiationType getPDPInitiationType();

    boolean getSecondaryPDPContext();

}