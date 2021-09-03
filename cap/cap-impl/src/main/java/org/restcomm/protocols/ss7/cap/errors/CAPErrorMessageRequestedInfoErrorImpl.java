
package org.restcomm.protocols.ss7.cap.errors;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorCode;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageRequestedInfoError;
import org.restcomm.protocols.ss7.cap.api.errors.RequestedInfoErrorParameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPErrorMessageRequestedInfoErrorImpl extends CAPErrorMessageImpl implements CAPErrorMessageRequestedInfoError {

    private static final String REQUESTED_INFO_ERROR_PARAMETER = "requestedInfoErrorParameter";

    public static final String _PrimitiveName = "CAPErrorMessageRequestedInfoError";

    private RequestedInfoErrorParameter requestedInfoErrorParameter;

    protected CAPErrorMessageRequestedInfoErrorImpl(RequestedInfoErrorParameter requestedInfoErrorParameter) {
        super((long) CAPErrorCode.requestedInfoError);

        this.requestedInfoErrorParameter = requestedInfoErrorParameter;
    }

    public CAPErrorMessageRequestedInfoErrorImpl() {
        super((long) CAPErrorCode.requestedInfoError);
    }

    public boolean isEmRequestedInfoError() {
        return true;
    }

    public CAPErrorMessageRequestedInfoError getEmRequestedInfoError() {
        return this;
    }

    public RequestedInfoErrorParameter getRequestedInfoErrorParameter() {
        return requestedInfoErrorParameter;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.ENUMERATED;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return true;
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
        }
    }


    private void _decode(AsnInputStream localAis, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.requestedInfoErrorParameter = null;

        if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL || localAis.getTag() != Tag.ENUMERATED || !localAis.isTagPrimitive())
            throw new CAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": bad tag class or tag or parameter is not primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        int i1 = (int) localAis.readIntegerData(length);
        this.requestedInfoErrorParameter = RequestedInfoErrorParameter.getInstance(i1);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream aos) throws CAPException {

        if (this.requestedInfoErrorParameter == null)
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": requestedInfoErrorParameter field must not be null");

        try {
            aos.writeIntegerData(this.requestedInfoErrorParameter.getCode());

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.requestedInfoErrorParameter != null) {
            sb.append("requestedInfoErrorParameter=");
            sb.append(requestedInfoErrorParameter);
            sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CAPErrorMessageRequestedInfoErrorImpl> CAP_ERROR_MESSAGE_REQUESTED_INFO_ERROR_XML = new XMLFormat<CAPErrorMessageRequestedInfoErrorImpl>(
            CAPErrorMessageRequestedInfoErrorImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CAPErrorMessageRequestedInfoErrorImpl errorMessage)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.read(xml, errorMessage);

            String str = xml.get(REQUESTED_INFO_ERROR_PARAMETER, String.class);
            if (str != null)
                errorMessage.requestedInfoErrorParameter = Enum.valueOf(RequestedInfoErrorParameter.class, str);
        }

        @Override
        public void write(CAPErrorMessageRequestedInfoErrorImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.write(errorMessage, xml);

            if (errorMessage.requestedInfoErrorParameter != null)
                xml.add((String) errorMessage.requestedInfoErrorParameter.toString(), REQUESTED_INFO_ERROR_PARAMETER,
                        String.class);
        }
    };
}
