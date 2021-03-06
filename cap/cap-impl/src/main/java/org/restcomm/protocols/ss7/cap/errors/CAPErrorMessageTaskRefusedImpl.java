
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
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageTaskRefused;
import org.restcomm.protocols.ss7.cap.api.errors.TaskRefusedParameter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPErrorMessageTaskRefusedImpl extends CAPErrorMessageImpl implements CAPErrorMessageTaskRefused {

    private static final String TASK_REFUSED_PARAMETER = "taskRefusedParameter";

    public static final String _PrimitiveName = "CAPErrorMessageTaskRefused";

    private TaskRefusedParameter taskRefusedParameter;

    protected CAPErrorMessageTaskRefusedImpl(TaskRefusedParameter taskRefusedParameter) {
        super((long) CAPErrorCode.taskRefused);

        this.taskRefusedParameter = taskRefusedParameter;
    }

    public CAPErrorMessageTaskRefusedImpl() {
        super((long) CAPErrorCode.taskRefused);
    }

    public boolean isEmTaskRefused() {
        return true;
    }

    public CAPErrorMessageTaskRefused getEmTaskRefused() {
        return this;
    }

    public TaskRefusedParameter getTaskRefusedParameter() {
        return taskRefusedParameter;
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

        this.taskRefusedParameter = null;

        if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL || localAis.getTag() != Tag.ENUMERATED || !localAis.isTagPrimitive())
            throw new CAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": bad tag class or tag or parameter is not primitive",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        int i1 = (int) localAis.readIntegerData(length);
        this.taskRefusedParameter = TaskRefusedParameter.getInstance(i1);
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

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        if (this.taskRefusedParameter == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": taskRefusedParameter field must not be null");

        try {
            asnOutputStream.writeIntegerData(this.taskRefusedParameter.getCode());

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.taskRefusedParameter != null) {
            sb.append("taskRefusedParameter=");
            sb.append(taskRefusedParameter);
            sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CAPErrorMessageTaskRefusedImpl> CAP_ERROR_MESSAGE_TASK_REFUSED_XML = new XMLFormat<CAPErrorMessageTaskRefusedImpl>(
            CAPErrorMessageTaskRefusedImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CAPErrorMessageTaskRefusedImpl errorMessage)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.read(xml, errorMessage);

            String str = xml.get(TASK_REFUSED_PARAMETER, String.class);
            if (str != null)
                errorMessage.taskRefusedParameter = Enum.valueOf(TaskRefusedParameter.class, str);
        }

        @Override
        public void write(CAPErrorMessageTaskRefusedImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CAP_ERROR_MESSAGE_XML.write(errorMessage, xml);

            if (errorMessage.taskRefusedParameter != null)
                xml.add((String) errorMessage.taskRefusedParameter.toString(), TASK_REFUSED_PARAMETER, String.class);
        }
    };

}
