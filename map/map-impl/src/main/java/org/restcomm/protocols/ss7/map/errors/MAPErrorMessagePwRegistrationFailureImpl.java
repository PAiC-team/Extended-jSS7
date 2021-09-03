
package org.restcomm.protocols.ss7.map.errors;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessagePwRegistrationFailure;
import org.restcomm.protocols.ss7.map.api.errors.PWRegistrationFailureCause;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessagePwRegistrationFailureImpl extends MAPErrorMessageImpl implements MAPErrorMessagePwRegistrationFailure {

    private static final String PW_REGIS_FAILURE_CAUSE = "pwRegistrationFailureCause";

    private PWRegistrationFailureCause pwRegistrationFailureCause;

    protected String _PrimitiveName = "MAPErrorMessagePwRegistrationFailure";

    public MAPErrorMessagePwRegistrationFailureImpl(PWRegistrationFailureCause pwRegistrationFailureCause) {
        super((long) MAPErrorCode.pwRegistrationFailure);

        this.pwRegistrationFailureCause = pwRegistrationFailureCause;
    }

    public MAPErrorMessagePwRegistrationFailureImpl() {
        super((long) MAPErrorCode.pwRegistrationFailure);
    }

    public boolean isEmPwRegistrationFailure() {
        return true;
    }

    public MAPErrorMessagePwRegistrationFailure getEmPwRegistrationFailure() {
        return this;
    }

    @Override
    public PWRegistrationFailureCause getPWRegistrationFailureCause() {
        return pwRegistrationFailureCause;
    }

    @Override
    public void setPWRegistrationFailureCause(PWRegistrationFailureCause val) {
        pwRegistrationFailureCause = val;
    }

    @Override
    public int getTag() throws MAPException {
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

    @Override
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

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.ENUMERATED
            || !localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": bad tag class or tag or parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);

        int i1 = (int) localAsnInputStream.readIntegerData(length);
        this.pwRegistrationFailureCause = PWRegistrationFailureCause.getInstance(i1);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {

        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
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

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.pwRegistrationFailureCause == null)
            throw new MAPException("Parameter pwRegistrationFailureCause must not be null");

        try {
            asnOutputStream.writeIntegerData(this.pwRegistrationFailureCause.getCode());
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.pwRegistrationFailureCause != null)
            sb.append("pwRegistrationFailureCause=" + this.pwRegistrationFailureCause.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessagePwRegistrationFailureImpl> MAP_ERROR_MESSAGE_PW_REGIS_FAILURE_XML = new XMLFormat<MAPErrorMessagePwRegistrationFailureImpl>(
            MAPErrorMessagePwRegistrationFailureImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessagePwRegistrationFailureImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            String str = xml.get(PW_REGIS_FAILURE_CAUSE, String.class);
            if (str != null)
                errorMessage.pwRegistrationFailureCause = Enum.valueOf(PWRegistrationFailureCause.class, str);
        }

        @Override
        public void write(MAPErrorMessagePwRegistrationFailureImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            if (errorMessage.getPWRegistrationFailureCause() != null)
                xml.add((String) errorMessage.getPWRegistrationFailureCause().toString(), PW_REGIS_FAILURE_CAUSE, String.class);
        }
    };

}
