
package org.restcomm.protocols.ss7.cap.EsiGprs;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PDPContextEstablishmentAcknowledgementSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.cap.primitives.TimeAndTimezoneImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.AccessPointNameImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.EndUserAddressImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.QualityOfServiceImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GPRSChargingIDImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationGPRSImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDPContextEstablishmentAcknowledgementSpecificInformationImpl extends SequenceBase implements
        PDPContextEstablishmentAcknowledgementSpecificInformation {

    public static final int _ID_accessPointName = 0;
    public static final int _ID_gprsChargingID = 1;
    public static final int _ID_locationInformationGPRS = 2;
    public static final int _ID_endUserAddress = 3;
    public static final int _ID_qualityOfService = 4;
    public static final int _ID_timeAndTimezone = 5;
    public static final int _ID_gsnAddress = 6;

    public static final int _ID_PDPContextEstablishmentAcknowledgementSpecificInformation = 5;

    private AccessPointName accessPointName;
    private GPRSChargingID gprsChargingID;
    private LocationInformationGPRS locationInformationGPRS;
    private EndUserAddress endUserAddress;
    private QualityOfService qualityOfService;
    private TimeAndTimezone timeAndTimezone;
    private GSNAddress gsnAddress;

    public PDPContextEstablishmentAcknowledgementSpecificInformationImpl() {
        super("PDPContextEstablishmentAcknowledgementSpecificInformation");
    }

    public PDPContextEstablishmentAcknowledgementSpecificInformationImpl(AccessPointName accessPointName,
            GPRSChargingID gprsChargingID, LocationInformationGPRS locationInformationGPRS, EndUserAddress endUserAddress,
            QualityOfService qualityOfService, TimeAndTimezone timeAndTimezone, GSNAddress gsnAddress) {
        super("PDPContextEstablishmentAcknowledgementSpecificInformation");
        this.accessPointName = accessPointName;
        this.gprsChargingID = gprsChargingID;
        this.locationInformationGPRS = locationInformationGPRS;
        this.endUserAddress = endUserAddress;
        this.qualityOfService = qualityOfService;
        this.timeAndTimezone = timeAndTimezone;
        this.gsnAddress = gsnAddress;
    }

    @Override
    public AccessPointName getAccessPointName() {
        return this.accessPointName;
    }

    @Override
    public GPRSChargingID getChargingID() {
        return this.gprsChargingID;
    }

    @Override
    public LocationInformationGPRS getLocationInformationGPRS() {
        return this.locationInformationGPRS;
    }

    @Override
    public EndUserAddress getEndUserAddress() {
        return this.endUserAddress;
    }

    @Override
    public QualityOfService getQualityOfService() {
        return this.qualityOfService;
    }

    @Override
    public TimeAndTimezone getTimeAndTimezone() {
        return this.timeAndTimezone;
    }

    @Override
    public GSNAddress getGSNAddress() {
        return this.gsnAddress;
    }

    public int getTag() throws CAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {
        this.accessPointName = null;
        this.gprsChargingID = null;
        this.locationInformationGPRS = null;
        this.endUserAddress = null;
        this.qualityOfService = null;
        this.timeAndTimezone = null;
        this.gsnAddress = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {

                    case _ID_accessPointName:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".accessPointName: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.accessPointName = new AccessPointNameImpl();
                        ((AccessPointNameImpl) this.accessPointName).decodeAll(ais);
                        break;
                    case _ID_gprsChargingID:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".gprsChargingID: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.gprsChargingID = new GPRSChargingIDImpl();
                        ((GPRSChargingIDImpl) this.gprsChargingID).decodeAll(ais);
                        break;
                    case _ID_locationInformationGPRS:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".locationInformationGPRS: Parameter is  primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.locationInformationGPRS = new LocationInformationGPRSImpl();
                        ((LocationInformationGPRSImpl) this.locationInformationGPRS).decodeAll(ais);
                        break;
                    case _ID_endUserAddress:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".endUserAddress: Parameter is  primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.endUserAddress = new EndUserAddressImpl();
                        ((EndUserAddressImpl) this.endUserAddress).decodeAll(ais);
                        break;
                    case _ID_qualityOfService:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".qualityOfService: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.qualityOfService = new QualityOfServiceImpl();
                        ((QualityOfServiceImpl) this.qualityOfService).decodeAll(ais);
                        break;
                    case _ID_timeAndTimezone:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".timeAndTimezone: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.timeAndTimezone = new TimeAndTimezoneImpl();
                        ((TimeAndTimezoneImpl) this.timeAndTimezone).decodeAll(ais);
                        break;
                    case _ID_gsnAddress:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".gsnAddress: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.gsnAddress = new GSNAddressImpl();
                        ((GSNAddressImpl) this.gsnAddress).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        try {
            if (this.accessPointName != null)
                ((AccessPointNameImpl) this.accessPointName).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_accessPointName);

            if (this.gprsChargingID != null)
                ((GPRSChargingIDImpl) this.gprsChargingID).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_gprsChargingID);

            if (this.locationInformationGPRS != null)
                ((LocationInformationGPRSImpl) this.locationInformationGPRS).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _ID_locationInformationGPRS);

            if (this.endUserAddress != null)
                ((EndUserAddressImpl) this.endUserAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_endUserAddress);

            if (this.qualityOfService != null)
                ((QualityOfServiceImpl) this.qualityOfService).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _ID_qualityOfService);

            if (this.timeAndTimezone != null)
                ((TimeAndTimezoneImpl) this.timeAndTimezone).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_timeAndTimezone);

            if (this.gsnAddress != null)
                ((GSNAddressImpl) this.gsnAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_gsnAddress);

        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.accessPointName != null) {
            sb.append("accessPointName=");
            sb.append(this.accessPointName.toString());
            sb.append(", ");
        }

        if (this.gprsChargingID != null) {
            sb.append("gprsChargingID=");
            sb.append(this.gprsChargingID.toString());
            sb.append(", ");
        }

        if (this.locationInformationGPRS != null) {
            sb.append("locationInformationGPRS=");
            sb.append(this.locationInformationGPRS.toString());
            sb.append(", ");
        }

        if (this.endUserAddress != null) {
            sb.append("endUserAddress=");
            sb.append(this.endUserAddress.toString());
            sb.append(", ");
        }

        if (this.qualityOfService != null) {
            sb.append("qualityOfService=");
            sb.append(this.qualityOfService.toString());
            sb.append(", ");
        }

        if (this.timeAndTimezone != null) {
            sb.append("timeAndTimezone=");
            sb.append(this.timeAndTimezone.toString());
            sb.append(", ");
        }

        if (this.gsnAddress != null) {
            sb.append("gsnAddress=");
            sb.append(this.gsnAddress.toString());
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }

}
