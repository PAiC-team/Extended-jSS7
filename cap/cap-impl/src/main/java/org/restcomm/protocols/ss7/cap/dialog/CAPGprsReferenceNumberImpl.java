package org.restcomm.protocols.ss7.cap.dialog;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPGprsReferenceNumber;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPGprsReferenceNumberImpl implements CAPGprsReferenceNumber, CAPAsnPrimitive {

    protected static final int DESTINATION_REF_TAG = 0;
    protected static final int ORIGINATION_REF_TAG = 1;

    public static final String _PrimitiveName = "CAPGprsReferenceNumber";

    public static final long[] CAP_Dialogue_OId = new long[] { 0, 4, 0, 0, 1, 1, 5, 2 };

    private Integer destinationReference;
    private Integer originationReference;

    public CAPGprsReferenceNumberImpl() {
    }

    public CAPGprsReferenceNumberImpl(Integer destinationReference, Integer originationReference) {
        this.destinationReference = destinationReference;
        this.originationReference = originationReference;
    }

    @Override
    public Integer getDestinationReference() {
        return this.destinationReference;
    }

    @Override
    public Integer getOriginationReference() {
        return this.originationReference;
    }

    @Override
    public void setDestinationReference(Integer destinationReference) {
        this.destinationReference = destinationReference;
    }

    @Override
    public void setOriginationReference(Integer originationReference) {
        this.originationReference = originationReference;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
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
            throw new CAPParsingComponentException("IOException when decoding CAPGprsReferenceNumber: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding CAPGprsReferenceNumber: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding CAPGprsReferenceNumber: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding CAPGprsReferenceNumber: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.destinationReference = null;
        this.originationReference = null;

        AsnInputStream localAis = asnInputStream.readSequenceStreamData(length);

        while (localAis.available() > 0) {
            int tag = localAis.readTag();

            switch (localAis.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case DESTINATION_REF_TAG:
                            if (localAis.isTagPrimitive()) {
                                this.destinationReference = (int) localAis.readInteger();
                            } else {
                                AsnInputStream ais2 = localAis.readSequenceStream();
                                int tag2 = ais2.readTag();
                                if (tag2 != Tag.INTEGER || ais2.getTagClass() != Tag.CLASS_UNIVERSAL) {
                                    throw new CAPParsingComponentException(
                                            "Error while decoding CAPGprsReferenceNumber: Bad tag or tagClass when decoding EXPLICIT destinationReference",
                                            CAPParsingComponentExceptionReason.MistypedParameter);
                                }
                                this.destinationReference = (int) ais2.readInteger();
                            }
                            break;

                        case ORIGINATION_REF_TAG:
                            if (localAis.isTagPrimitive()) {
                                this.originationReference = (int) localAis.readInteger();
                            } else {
                                AsnInputStream ais2 = localAis.readSequenceStream();
                                int tag2 = ais2.readTag();
                                if (tag2 != Tag.INTEGER || ais2.getTagClass() != Tag.CLASS_UNIVERSAL) {
                                    throw new CAPParsingComponentException(
                                            "Error while decoding CAPGprsReferenceNumber: Bad tag or tagClass when decoding EXPLICIT destinationReference",
                                            CAPParsingComponentExceptionReason.MistypedParameter);
                                }
                                this.originationReference = (int) ais2.readInteger();
                            }
                            break;

                        default:
                            localAis.advanceElement();
                            break;
                    }
                    break;

                default:
                    localAis.advanceElement();
                    break;
            }
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOs) throws CAPException {

        this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws CAPException {

        try {
            asnOs.writeTag(tagClass, false, tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream aos) throws CAPException {

        try {
            if (this.destinationReference != null) {
                aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, DESTINATION_REF_TAG);
                int i1 = aos.StartContentDefiniteLength();
                aos.writeInteger(Tag.CLASS_UNIVERSAL, Tag.INTEGER, this.destinationReference);
                aos.FinalizeContent(i1);
            }
            if (this.originationReference != null) {
                aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, ORIGINATION_REF_TAG);
                int i1 = aos.StartContentDefiniteLength();
                aos.writeInteger(Tag.CLASS_UNIVERSAL, Tag.INTEGER, this.originationReference);
                aos.FinalizeContent(i1);
            }
        } catch (IOException e) {
            throw new CAPException("IOException when encoding CAPGprsReferenceNumber: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding CAPGprsReferenceNumber: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.destinationReference != null) {
            sb.append("destinationReference=");
            sb.append(destinationReference);
        }
        if (this.originationReference != null) {
            sb.append(", originationReference=");
            sb.append(originationReference);
        }
        sb.append("]");

        return sb.toString();
    }
}
