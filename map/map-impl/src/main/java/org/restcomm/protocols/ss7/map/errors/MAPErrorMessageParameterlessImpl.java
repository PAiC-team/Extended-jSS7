package org.restcomm.protocols.ss7.map.errors;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageParameterless;

/**
 * The MAP ReturnError message without any parameters
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageParameterlessImpl extends MAPErrorMessageImpl implements MAPErrorMessageParameterless {

    public MAPErrorMessageParameterlessImpl() {
    }

    public MAPErrorMessageParameterlessImpl(Long errorCode) {
        super(errorCode);
    }

    public boolean isEmParameterless() {
        return true;
    }

    public MAPErrorMessageParameterless getEmParameterless() {
        return this;
    }

    public int getTag() throws MAPException {
        throw new MAPException("MAPErrorMessageParameterless does not support encoding");
    }

    public int getTagClass() {
        return 0;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
    }

    @Override
    public String toString() {
        return "MAPErrorMessageParameterless [errorCode=" + this.errorCode + "]";
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageParameterlessImpl> MAP_ERROR_MESSAGE_PARAMETERLESS_XML = new XMLFormat<MAPErrorMessageParameterlessImpl>(
            MAPErrorMessageParameterlessImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageParameterlessImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
        }

        @Override
        public void write(MAPErrorMessageParameterlessImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
        }
    };
}
