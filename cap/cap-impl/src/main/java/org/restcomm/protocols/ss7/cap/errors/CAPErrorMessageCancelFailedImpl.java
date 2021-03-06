
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
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageCancelFailed;
import org.restcomm.protocols.ss7.cap.api.errors.CancelProblem;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPErrorMessageCancelFailedImpl extends CAPErrorMessageImpl implements CAPErrorMessageCancelFailed {

    private static final String CANCEL_PROBLEM = "cancelProblem";

    public static final String _PrimitiveName = "CAPErrorMessageCancelFailed";

    private CancelProblem cancelProblem;

    protected CAPErrorMessageCancelFailedImpl(CancelProblem cancelProblem) {
        super((long) CAPErrorCode.cancelFailed);

        this.cancelProblem = cancelProblem;
    }

    public CAPErrorMessageCancelFailedImpl() {
        super((long) CAPErrorCode.cancelFailed);
    }

    @Override
    public boolean isEmCancelFailed() {
        return true;
    }

    @Override
    public CAPErrorMessageCancelFailed getEmCancelFailed() {
        return this;
    }

    @Override
    public CancelProblem getCancelProblem() {
        return cancelProblem;
    }

    public void setCancelProblem(CancelProblem cancelProblem) {
        this.cancelProblem = cancelProblem;
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

        this.cancelProblem = null;

        if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL || localAis.getTag() != Tag.ENUMERATED || !localAis.isTagPrimitive())
            throw new CAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": bad tag class or tag or parameter is not primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        int i1 = (int) localAis.readIntegerData(length);
        this.cancelProblem = CancelProblem.getInstance(i1);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws CAPException {
        try {
            asnOs.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        if (this.cancelProblem == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": cancelProblem field must not be null");

        try {
            asnOutputStream.writeIntegerData(this.cancelProblem.getCode());

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.cancelProblem != null) {
            sb.append("cancelProblem=");
            sb.append(cancelProblem);
            sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CAPErrorMessageCancelFailedImpl> CAP_ERROR_MESSAGE_CANCEL_FAILED_XML = new XMLFormat<CAPErrorMessageCancelFailedImpl>(
            CAPErrorMessageCancelFailedImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CAPErrorMessageCancelFailedImpl errorMessage)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.read(xml, errorMessage);

            String str = xml.get(CANCEL_PROBLEM, String.class);
            if (str != null)
                errorMessage.cancelProblem = Enum.valueOf(CancelProblem.class, str);
        }

        @Override
        public void write(CAPErrorMessageCancelFailedImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.write(errorMessage, xml);

            if (errorMessage.cancelProblem != null)
                xml.add(errorMessage.cancelProblem.toString(), CANCEL_PROBLEM, String.class);
        }
    };
}
