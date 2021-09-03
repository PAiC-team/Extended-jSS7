
package org.restcomm.protocols.ss7.cap.errors;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorCode;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageParameterless;

/**
 * The CAP ReturnError message without any parameters
 *
 * @author sergey vetyutnev
 *
 */
public class CAPErrorMessageParameterlessImpl extends CAPErrorMessageImpl implements CAPErrorMessageParameterless {

    public CAPErrorMessageParameterlessImpl(Long errorCode) {
        super(errorCode);
    }

    public CAPErrorMessageParameterlessImpl() {
        super(0L);
    }

    @Override
    public boolean isEmParameterless() {
        return true;
    }

    @Override
    public CAPErrorMessageParameterless getEmParameterless() {
        return this;
    }

    @Override
    public int getTag() throws CAPException {
        throw new CAPException("CAPErrorMessageParameterless does not support encoding");
    }

    @Override
    public int getTagClass() {
        return 0;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
    }

    @Override
    public String toString() {
        return "CAPErrorMessageParameterless [errorCode=" + errorCode + ":" + capErrorCodeName() + "]";
    }

    private String capErrorCodeName() {
        if (errorCode == null)
            return "N/A";
        switch (errorCode.intValue()) {
            case CAPErrorCode.canceled:
                return "canceled";
            case CAPErrorCode.cancelFailed:
                return "cancelFailed";
            case CAPErrorCode.eTCFailed:
                return "eTCFailed";
            case CAPErrorCode.improperCallerResponse:
                return "improperCallerResponse";
            case CAPErrorCode.missingCustomerRecord:
                return "missingCustomerRecord";
            case CAPErrorCode.missingParameter:
                return "missingParameter";
            case CAPErrorCode.parameterOutOfRange:
                return "parameterOutOfRange";
            case CAPErrorCode.requestedInfoError:
                return "requestedInfoError";
            case CAPErrorCode.systemFailure:
                return "systemFailure";
            case CAPErrorCode.taskRefused:
                return "taskRefused";
            case CAPErrorCode.unavailableResource:
                return "unavailableResource";
            case CAPErrorCode.unexpectedComponentSequence:
                return "unexpectedComponentSequence";
            case CAPErrorCode.unexpectedDataValue:
                return "unexpectedDataValue";
            case CAPErrorCode.unexpectedParameter:
                return "unexpectedParameter";
            case CAPErrorCode.unknownCSID:
                return "unknownCSID";
            case CAPErrorCode.unknownLegID:
                return "unknownLegID";
            case CAPErrorCode.unknownPDPID:
                return "unknownPDPID";
            default:
                return errorCode.toString();
        }
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CAPErrorMessageParameterlessImpl> CAP_ERROR_MESSAGE_PARAMETERLESS_XML = new XMLFormat<CAPErrorMessageParameterlessImpl>(
            CAPErrorMessageParameterlessImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CAPErrorMessageParameterlessImpl errorMessage)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
        }

        @Override
        public void write(CAPErrorMessageParameterlessImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
        }
    };

}
