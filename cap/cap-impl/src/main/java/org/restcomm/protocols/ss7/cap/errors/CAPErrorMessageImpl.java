package org.restcomm.protocols.ss7.cap.errors;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessage;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageCancelFailed;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageParameterless;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageRequestedInfoError;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageSystemFailure;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageTaskRefused;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 * Base class of CAP ReturnError messages
 *
 * @author sergey vetyutnev
 *
 */
public abstract class CAPErrorMessageImpl implements CAPErrorMessage, CAPAsnPrimitive {

    private static final String ERROR_CODE = "errorCode";

    protected Long errorCode;

    protected CAPErrorMessageImpl(Long errorCode) {
        this.errorCode = errorCode;
    }

    public CAPErrorMessageImpl() {
    }

    @Override
    public Long getErrorCode() {
        return errorCode;
    }

    @Override
    public boolean isEmParameterless() {
        return false;
    }

    @Override
    public boolean isEmCancelFailed() {
        return false;
    }

    @Override
    public boolean isEmRequestedInfoError() {
        return false;
    }

    @Override
    public boolean isEmSystemFailure() {
        return false;
    }

    @Override
    public boolean isEmTaskRefused() {
        return false;
    }

    @Override
    public CAPErrorMessageParameterless getEmParameterless() {
        return null;
    }

    @Override
    public CAPErrorMessageCancelFailed getEmCancelFailed() {
        return null;
    }

    @Override
    public CAPErrorMessageRequestedInfoError getEmRequestedInfoError() {
        return null;
    }

    @Override
    public CAPErrorMessageSystemFailure getEmSystemFailure() {
        return null;
    }

    @Override
    public CAPErrorMessageTaskRefused getEmTaskRefused() {
        return null;
    }

    /**
     * XML Serialization/Deserialization
     */
    public static final XMLFormat<CAPErrorMessageImpl> CAP_ERROR_MESSAGE_XML = new XMLFormat<CAPErrorMessageImpl>(
            CAPErrorMessageImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CAPErrorMessageImpl message) throws XMLStreamException {
            message.errorCode = xml.getAttribute(ERROR_CODE, -1L);
        }

        @Override
        public void write(CAPErrorMessageImpl message, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(ERROR_CODE, message.errorCode);
        }
    };
}
