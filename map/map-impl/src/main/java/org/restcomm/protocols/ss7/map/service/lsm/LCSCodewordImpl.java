package org.restcomm.protocols.ss7.map.service.lsm;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;

/**
 * @author amit bhayani
 *
 */
public class LCSCodewordImpl implements LCSCodeword, MAPAsnPrimitive {

    private static final int _TAG_DATA_CODING_SCHEME = 0;
    private static final int _TAG_LCS_CODE_WORD_STRING = 1;

    public static final String _PrimitiveName = "LCSCodeword";

    private CBSDataCodingScheme dataCodingScheme;
    private USSDString lcsCodewordString;

    /**
     *
     */
    public LCSCodewordImpl() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param dataCodingScheme
     * @param lcsCodewordString
     */
    public LCSCodewordImpl(CBSDataCodingScheme dataCodingScheme, USSDString lcsCodewordString) {
        super();
        this.dataCodingScheme = dataCodingScheme;
        this.lcsCodewordString = lcsCodewordString;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword#getDataCodingScheme()
     */
    public CBSDataCodingScheme getDataCodingScheme() {
        return this.dataCodingScheme;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword#getLCSCodewordString()
     */
    public USSDString getLCSCodewordString() {
        return this.lcsCodewordString;
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

        this.dataCodingScheme = null;
        this.lcsCodewordString = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int tag = ais.readTag();

        // Decode mandatory dataCodingScheme [0] USSD-DataCodingScheme,
        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_DATA_CODING_SCHEME) {
            throw new MAPParsingComponentException(
                    "Error while decoding LCSCodeword: Parameter 0[dataCodingScheme [0] USSD-DataCodingScheme] bad tag class, tag or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        int length1 = ais.readLength();
        this.dataCodingScheme = new CBSDataCodingSchemeImpl(ais.readOctetStringData(length1)[0]);

        tag = ais.readTag();

        // Decode mandatory lcsCodewordString [1] LCSCodewordString,
        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_LCS_CODE_WORD_STRING) {
            throw new MAPParsingComponentException(
                    "Error while decoding LCSCodeword: Parameter 1[lcsCodewordString [1] LCSCodewordString] bad tag class, tag or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        this.lcsCodewordString = new USSDStringImpl(this.dataCodingScheme);
        ((USSDStringImpl) this.lcsCodewordString).decodeAll(ais);

        while (true) {
            if (ais.available() == 0)
                break;
            switch (ais.readTag()) {
                default:
                    ais.advanceElement();
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream, int, int)
     */
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
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
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeData
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.lcsCodewordString == null)
            throw new MAPException("lcsCodewordString must not be null");

        try {
            asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_DATA_CODING_SCHEME,
                    new byte[] { (byte) this.dataCodingScheme.getCode() });

            ((USSDStringImpl) this.lcsCodewordString).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LCS_CODE_WORD_STRING);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding LCSClientName", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding LCSClientName", e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dataCodingScheme.getCode();
        result = prime * result + ((lcsCodewordString == null) ? 0 : lcsCodewordString.hashCode());
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
        LCSCodewordImpl other = (LCSCodewordImpl) obj;
        if (dataCodingScheme.getCode() != other.dataCodingScheme.getCode())
            return false;
        if (lcsCodewordString == null) {
            if (other.lcsCodewordString != null)
                return false;
        } else if (!lcsCodewordString.equals(other.lcsCodewordString))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("dataCodingScheme=");
        sb.append(this.dataCodingScheme);

        if (this.lcsCodewordString != null) {
            sb.append(", lcsCodewordString=");
            sb.append(this.lcsCodewordString.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
