package org.restcomm.protocols.ss7.map.service.lsm;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredLocationEventType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationEstimateType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationType;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 * @author amit bhayani
 *
 */
public class LocationTypeImpl implements LocationType, MAPAsnPrimitive {

    private static final int _TAG_LOCATION_ESTIMATE_TYPE = 0;
    private static final int _TAG_DEFERRED_LOCATION_EVENT_TYPE = 1;

    public static final String _PrimitiveName = "LocationType";

    private LocationEstimateType locationEstimateType;
    private DeferredLocationEventType deferredLocationEventType;

    public LocationTypeImpl() {

    }

    /**
     *
     */
    public LocationTypeImpl(final LocationEstimateType locationEstimateType, final DeferredLocationEventType deferredLocationEventType) {
        this.locationEstimateType = locationEstimateType;
        this.deferredLocationEventType = deferredLocationEventType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LocationType#getLocationEstimateType()
     */
    public LocationEstimateType getLocationEstimateType() {
        return this.locationEstimateType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LocationType#getDeferredLocationEventType()
     */
    public DeferredLocationEventType getDeferredLocationEventType() {
        return this.deferredLocationEventType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTagClass ()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeAll
     * (org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeData
     * (org.mobicents.protocols.asn.AsnInputStream, int)
     */
    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.locationEstimateType = null;
        this.deferredLocationEventType = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int tag = ais.readTag();

        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_LOCATION_ESTIMATE_TYPE) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter 0[locationEstimateType [0] LocationEstimateType] bad tag class, tag or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        int type = (int) ais.readInteger();
        this.locationEstimateType = LocationEstimateType.getLocationEstimateType(type);

        while (true) {
            if (ais.available() == 0)
                break;

            tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_DEFERRED_LOCATION_EVENT_TYPE:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter [deferredLocationEventType [1] DeferredLocationEventType] is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        }

                        this.deferredLocationEventType = new DeferredLocationEventTypeImpl();
                        ((DeferredLocationEventTypeImpl) this.deferredLocationEventType).decodeAll(ais);
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream _TAG_DEFERRED_LOCATION_EVENT_TYPE) throws MAPException {
        this.encodeAll(_TAG_DEFERRED_LOCATION_EVENT_TYPE, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream, int, int)
     */
    public void encodeAll(AsnOutputStream _TAG_DEFERRED_LOCATION_EVENT_TYPE, int tagClass, int tag) throws MAPException {
        try {
            _TAG_DEFERRED_LOCATION_EVENT_TYPE.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = _TAG_DEFERRED_LOCATION_EVENT_TYPE.StartContentDefiniteLength();
            this.encodeData(_TAG_DEFERRED_LOCATION_EVENT_TYPE);
            _TAG_DEFERRED_LOCATION_EVENT_TYPE.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeData
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.locationEstimateType == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter LocationEstimateType is not defined");
        }

        try {
            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LOCATION_ESTIMATE_TYPE, this.locationEstimateType.getType());
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter " + _PrimitiveName + ": ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter " + _PrimitiveName + ": ", e);
        }

        if (this.deferredLocationEventType != null) {
            ((DeferredLocationEventTypeImpl) this.deferredLocationEventType).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_DEFERRED_LOCATION_EVENT_TYPE);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deferredLocationEventType == null) ? 0 : deferredLocationEventType.hashCode());
        result = prime * result + ((locationEstimateType == null) ? 0 : locationEstimateType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocationTypeImpl other = (LocationTypeImpl) obj;
        if (deferredLocationEventType == null) {
            if (other.deferredLocationEventType != null)
                return false;
        } else if (!deferredLocationEventType.equals(other.deferredLocationEventType))
            return false;
        if (locationEstimateType != other.locationEstimateType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.locationEstimateType != null) {
            sb.append("locationEstimateType=");
            sb.append(this.locationEstimateType.toString());
        }
        if (this.deferredLocationEventType != null) {
            sb.append(", deferredLocationEventType=");
            sb.append(this.deferredLocationEventType.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
