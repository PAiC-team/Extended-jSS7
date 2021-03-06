
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiGprs.DetachSpecificInformationImpl;
import org.restcomm.protocols.ss7.cap.EsiGprs.DisconnectSpecificInformationImpl;
import org.restcomm.protocols.ss7.cap.EsiGprs.PDPContextEstablishmentAcknowledgementSpecificInformationImpl;
import org.restcomm.protocols.ss7.cap.EsiGprs.PDPContextEstablishmentSpecificInformationImpl;
import org.restcomm.protocols.ss7.cap.EsiGprs.PdpContextChangeOfPositionSpecificInformationImpl;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.DetachSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.DisconnectSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PDPContextEstablishmentAcknowledgementSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PDPContextEstablishmentSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.PdpContextChangeOfPositionSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventSpecificInformation;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationGPRSImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GPRSEventSpecificInformationImpl implements GPRSEventSpecificInformation, CAPAsnPrimitive {

    public static final String _PrimitiveName = "GPRSEventSpecificInformation";

    public static final int _ID_locationInformationGPRS = 0;
    public static final int _ID_pdpContextChangeOfPositionSpecificInformation = 1;
    public static final int _ID_detachSpecificInformation = 2;
    public static final int _ID_disconnectSpecificInformation = 3;
    public static final int _ID_pdpContextEstablishmentSpecificInformation = 4;
    public static final int _ID_pdpContextEstablishmentAcknowledgementSpecificInformation = 5;

    private LocationInformationGPRS locationInformationGPRS;
    private PdpContextChangeOfPositionSpecificInformation pdpContextChangeOfPositionSpecificInformation;
    private DetachSpecificInformation detachSpecificInformation;
    private DisconnectSpecificInformation disconnectSpecificInformation;
    private PDPContextEstablishmentSpecificInformation pdpContextEstablishmentSpecificInformation;
    private PDPContextEstablishmentAcknowledgementSpecificInformation pdpContextEstablishmentAcknowledgementSpecificInformation;

    public GPRSEventSpecificInformationImpl() {
        super();
    }

    public GPRSEventSpecificInformationImpl(LocationInformationGPRS locationInformationGPRS) {
        super();
        this.locationInformationGPRS = locationInformationGPRS;
    }

    public GPRSEventSpecificInformationImpl(PdpContextChangeOfPositionSpecificInformation pdpContextChangeOfPositionSpecificInformation) {
        super();
        this.pdpContextChangeOfPositionSpecificInformation = pdpContextChangeOfPositionSpecificInformation;
    }

    public GPRSEventSpecificInformationImpl(DetachSpecificInformation detachSpecificInformation) {
        super();
        this.detachSpecificInformation = detachSpecificInformation;
    }

    public GPRSEventSpecificInformationImpl(DisconnectSpecificInformation disconnectSpecificInformation) {
        super();
        this.disconnectSpecificInformation = disconnectSpecificInformation;
    }

    public GPRSEventSpecificInformationImpl(
            PDPContextEstablishmentSpecificInformation pdpContextEstablishmentSpecificInformation) {
        super();
        this.pdpContextEstablishmentSpecificInformation = pdpContextEstablishmentSpecificInformation;
    }

    public GPRSEventSpecificInformationImpl(
            PDPContextEstablishmentAcknowledgementSpecificInformation pdpContextEstablishmentAcknowledgementSpecificInformation) {
        super();
        this.pdpContextEstablishmentAcknowledgementSpecificInformation = pdpContextEstablishmentAcknowledgementSpecificInformation;
    }

    @Override
    public LocationInformationGPRS getLocationInformationGPRS() {
        return this.locationInformationGPRS;
    }

    @Override
    public PdpContextChangeOfPositionSpecificInformation getPdpContextChangeOfPositionSpecificInformation() {
        return this.pdpContextChangeOfPositionSpecificInformation;
    }

    @Override
    public DetachSpecificInformation getDetachSpecificInformation() {
        return this.detachSpecificInformation;
    }

    @Override
    public DisconnectSpecificInformation getDisconnectSpecificInformation() {
        return this.disconnectSpecificInformation;
    }

    @Override
    public PDPContextEstablishmentSpecificInformation getPDPContextEstablishmentSpecificInformation() {
        return this.pdpContextEstablishmentSpecificInformation;
    }

    @Override
    public PDPContextEstablishmentAcknowledgementSpecificInformation getPDPContextEstablishmentAcknowledgementSpecificInformation() {
        return this.pdpContextEstablishmentAcknowledgementSpecificInformation;
    }

    @Override
    public int getTag() throws CAPException {

        if (locationInformationGPRS != null) {
            return _ID_locationInformationGPRS;
        } else if (pdpContextChangeOfPositionSpecificInformation != null) {
            return _ID_pdpContextChangeOfPositionSpecificInformation;
        } else if (detachSpecificInformation != null) {
            return _ID_detachSpecificInformation;
        } else if (disconnectSpecificInformation != null) {
            return _ID_disconnectSpecificInformation;
        } else if (pdpContextEstablishmentSpecificInformation != null) {
            return _ID_pdpContextEstablishmentSpecificInformation;
        } else {
            return _ID_pdpContextEstablishmentAcknowledgementSpecificInformation;
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.locationInformationGPRS = null;
        this.pdpContextChangeOfPositionSpecificInformation = null;
        this.detachSpecificInformation = null;
        this.disconnectSpecificInformation = null;
        this.pdpContextEstablishmentSpecificInformation = null;
        this.pdpContextEstablishmentAcknowledgementSpecificInformation = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_locationInformationGPRS:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".locationInformationGPRS: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.locationInformationGPRS = new LocationInformationGPRSImpl();
                    ((LocationInformationGPRSImpl) this.locationInformationGPRS).decodeData(asnInputStream, length);
                    break;
                case _ID_pdpContextChangeOfPositionSpecificInformation:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".pdpContextChangeOfPositionSpecificInformation: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.pdpContextChangeOfPositionSpecificInformation = new PdpContextChangeOfPositionSpecificInformationImpl();
                    ((PdpContextChangeOfPositionSpecificInformationImpl) this.pdpContextChangeOfPositionSpecificInformation)
                            .decodeData(asnInputStream, length);
                    break;
                case _ID_detachSpecificInformation:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".detachSpecificInformation: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.detachSpecificInformation = new DetachSpecificInformationImpl();
                    ((DetachSpecificInformationImpl) this.detachSpecificInformation).decodeData(asnInputStream, length);
                    break;
                case _ID_disconnectSpecificInformation:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".disconnectSpecificInformation: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.disconnectSpecificInformation = new DisconnectSpecificInformationImpl();
                    ((DisconnectSpecificInformationImpl) this.disconnectSpecificInformation).decodeData(asnInputStream, length);
                    break;
                case _ID_pdpContextEstablishmentSpecificInformation:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".pdpContextEstablishmentSpecificInformation: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.pdpContextEstablishmentSpecificInformation = new PDPContextEstablishmentSpecificInformationImpl();
                    ((PDPContextEstablishmentSpecificInformationImpl) this.pdpContextEstablishmentSpecificInformation)
                            .decodeData(asnInputStream, length);
                    break;
                case _ID_pdpContextEstablishmentAcknowledgementSpecificInformation:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".pdpContextEstablishmentAcknowledgementSpecificInformation: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.pdpContextEstablishmentAcknowledgementSpecificInformation = new PDPContextEstablishmentAcknowledgementSpecificInformationImpl();
                    ((PDPContextEstablishmentAcknowledgementSpecificInformationImpl) this.pdpContextEstablishmentAcknowledgementSpecificInformation)
                            .decodeData(asnInputStream, length);
                    break;

                default:
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            CAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }

    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        int cnt = 0;
        if (this.locationInformationGPRS != null)
            cnt++;
        if (this.pdpContextChangeOfPositionSpecificInformation != null)
            cnt++;
        if (this.detachSpecificInformation != null)
            cnt++;
        if (this.disconnectSpecificInformation != null)
            cnt++;
        if (this.pdpContextEstablishmentSpecificInformation != null)
            cnt++;
        if (this.pdpContextEstablishmentAcknowledgementSpecificInformation != null)
            cnt++;

        if (cnt != 1)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": one and only one choice is required.");

        try {
            if (this.locationInformationGPRS != null) {
                ((LocationInformationGPRSImpl) this.locationInformationGPRS).encodeData(asnOutputStream);
                return;
            }

            if (this.pdpContextChangeOfPositionSpecificInformation != null) {
                ((PdpContextChangeOfPositionSpecificInformationImpl) this.pdpContextChangeOfPositionSpecificInformation)
                        .encodeData(asnOutputStream);
                return;
            }

            if (this.detachSpecificInformation != null) {
                ((DetachSpecificInformationImpl) this.detachSpecificInformation).encodeData(asnOutputStream);
                return;
            }

            if (this.disconnectSpecificInformation != null) {
                ((DisconnectSpecificInformationImpl) this.disconnectSpecificInformation).encodeData(asnOutputStream);
                return;
            }

            if (this.pdpContextEstablishmentSpecificInformation != null) {
                ((PDPContextEstablishmentSpecificInformationImpl) this.pdpContextEstablishmentSpecificInformation)
                        .encodeData(asnOutputStream);
                return;
            }

            if (this.pdpContextEstablishmentAcknowledgementSpecificInformation != null) {
                ((PDPContextEstablishmentAcknowledgementSpecificInformationImpl) this.pdpContextEstablishmentAcknowledgementSpecificInformation)
                        .encodeData(asnOutputStream);
                return;
            }

        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.locationInformationGPRS != null) {
            sb.append("locationInformationGPRS=");
            sb.append(this.locationInformationGPRS.toString());
            sb.append(" ");
        } else if (this.pdpContextChangeOfPositionSpecificInformation != null) {
            sb.append("pdpContextChangeOfPositionSpecificInformation=");
            sb.append(this.pdpContextChangeOfPositionSpecificInformation.toString());
            sb.append(" ");
        } else if (this.detachSpecificInformation != null) {
            sb.append("detachSpecificInformation=");
            sb.append(this.detachSpecificInformation.toString());
            sb.append(" ");
        } else if (this.disconnectSpecificInformation != null) {
            sb.append("disconnectSpecificInformation=");
            sb.append(this.disconnectSpecificInformation.toString());
            sb.append(" ");
        } else if (this.pdpContextEstablishmentSpecificInformation != null) {
            sb.append("pdpContextEstablishmentSpecificInformation=");
            sb.append(this.pdpContextEstablishmentSpecificInformation.toString());
            sb.append(" ");
        } else if (this.pdpContextEstablishmentAcknowledgementSpecificInformation != null) {
            sb.append("pdpContextEstablishmentAcknowledgementSpecificInformation=");
            sb.append(this.pdpContextEstablishmentAcknowledgementSpecificInformation.toString());
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }

}
