
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.EsiGprs.DetachSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.DisconnectSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PDPContextEstablishmentAcknowledgementSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PDPContextEstablishmentSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PdpContextChangeOfPositionSpecificInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;

/**
 *
 GPRSEventSpecificInformation {PARAMETERS-BOUND : bound} ::= CHOICE { attachChangeOfPositionSpecificInformation [0] SEQUENCE {
 * locationInformationGPRS [0] LocationInformationGPRS OPTIONAL, ... }, pdp-ContextchangeOfPositionSpecificInformation [1]
 * SEQUENCE { accessPointName [0] AccessPointName {bound} OPTIONAL, chargingID [1] GPRSChargingID OPTIONAL,
 * locationInformationGPRS [2] LocationInformationGPRS OPTIONAL, endUserAddress [3] EndUserAddress {bound} OPTIONAL,
 * qualityOfService [4] QualityOfService OPTIONAL, timeAndTimeZone [5] TimeAndTimezone {bound} OPTIONAL, ..., gGSNAddress [6]
 * GSN-Address OPTIONAL }, detachSpecificInformation [2] SEQUENCE { initiatingEntity [0] InitiatingEntity OPTIONAL, ...,
 * routeingAreaUpdate [1] NULL OPTIONAL }, disconnectSpecificInformation [3] SEQUENCE { initiatingEntity [0] InitiatingEntity
 * OPTIONAL, ..., routeingAreaUpdate [1] NULL OPTIONAL }, pDPContextEstablishmentSpecificInformation [4] SEQUENCE {
 * accessPointName [0] AccessPointName {bound} OPTIONAL, endUserAddress [1] EndUserAddress {bound} OPTIONAL, qualityOfService
 * [2] QualityOfService OPTIONAL, locationInformationGPRS [3] LocationInformationGPRS OPTIONAL, timeAndTimeZone [4]
 * TimeAndTimezone {bound} OPTIONAL, pDPInitiationType [5] PDPInitiationType OPTIONAL, ..., secondaryPDP-context [6] NULL
 * OPTIONAL }, pDPContextEstablishmentAcknowledgementSpecificInformation [5] SEQUENCE { accessPointName [0] AccessPointName
 * {bound} OPTIONAL, chargingID [1] GPRSChargingID OPTIONAL, endUserAddress [2] EndUserAddress {bound} OPTIONAL,
 * qualityOfService [3] QualityOfService OPTIONAL, locationInformationGPRS [4] LocationInformationGPRS OPTIONAL, timeAndTimeZone
 * [5] TimeAndTimezone {bound} OPTIONAL, ..., gGSNAddress [6] GSN-Address OPTIONAL } }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSEventSpecificInformation extends Serializable {

    LocationInformationGPRS getLocationInformationGPRS();

    PdpContextChangeOfPositionSpecificInformation getPdpContextChangeOfPositionSpecificInformation();

    DetachSpecificInformation getDetachSpecificInformation();

    DisconnectSpecificInformation getDisconnectSpecificInformation();

    PDPContextEstablishmentSpecificInformation getPDPContextEstablishmentSpecificInformation();

    PDPContextEstablishmentAcknowledgementSpecificInformation getPDPContextEstablishmentAcknowledgementSpecificInformation();

}