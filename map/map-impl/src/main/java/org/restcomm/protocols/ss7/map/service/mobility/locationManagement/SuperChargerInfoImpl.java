package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SuperChargerInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 * @author amit bhayani
 *
 */
public class SuperChargerInfoImpl implements SuperChargerInfo, MAPAsnPrimitive {

    public static final int _ID_sendSubscriberData = 0;
    public static final int _ID_subscriberDataStored = 1;

    public static final String _PrimitiveName = "SuperChargerInfo";

    private Boolean sendSubscriberData;
    private byte[] subscriberDataStored;

    /**
     *
     */
    public SuperChargerInfoImpl() {
    }

    /**
     * @param sendSubscriberData
     */
    public SuperChargerInfoImpl(Boolean sendSubscriberData) {
        super();
        this.sendSubscriberData = sendSubscriberData;
    }

    /**
     * @param subscriberDataStored
     */
    public SuperChargerInfoImpl(byte[] subscriberDataStored) {
        super();
        this.subscriberDataStored = subscriberDataStored;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SuperChargerInfo#getSendSubscriberData()
     */
    public Boolean getSendSubscriberData() {
        return this.sendSubscriberData;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SuperChargerInfo#getSubscriberDataStored()
     */
    public byte[] getSubscriberDataStored() {
        return this.subscriberDataStored;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        if (this.sendSubscriberData != null)
            return _ID_sendSubscriberData;
        else
            return _ID_subscriberDataStored;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll( org.mobicents.protocols.asn.AsnInputStream)
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
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData(org.mobicents.protocols.asn.AsnInputStream,
     * int)
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
        this.sendSubscriberData = null;
        this.subscriberDataStored = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is not primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _ID_sendSubscriberData:
                try {
                    asnInputStream.readNullData(length);
                    this.sendSubscriberData = Boolean.TRUE;
                } catch (AsnException e) {
                    throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": "
                            + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
                } catch (IOException e) {
                    throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": "
                            + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
                }
                break;

            case _ID_subscriberDataStored:
                this.subscriberDataStored = asnInputStream.readOctetStringData(length);
                break;

            default:
                throw new MAPParsingComponentException("Error while SuperChargerInfo: bad tag: " + asnInputStream.getTag(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream,
     * int, int)
     */
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, true, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.sendSubscriberData != null) {
            asnOutputStream.writeNullData();
        } else {
            asnOutputStream.writeOctetStringData(this.subscriberDataStored);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SuperChargerInfo [");

        if (sendSubscriberData != null)
            sb.append("sendSubscriberData, ");
        if (subscriberDataStored != null) {
            sb.append("subscriberDataStored=[");
            sb.append(printDataArr(subscriberDataStored));
            sb.append("], ");
        }

        sb.append("]");

        return sb.toString();
    }

    private String printDataArr(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int b : arr) {
            sb.append(b);
            sb.append(", ");
        }

        return sb.toString();
    }
}
