
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ActivityTestRequest;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ActivityTestRequestImpl extends CircuitSwitchedCallMessageImpl implements ActivityTestRequest {

    public static final String _PrimitiveName = "ActivityTestRequest";

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.activityTest_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.activityTest;
    }

    @Override
    public int getTag() throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
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
        throw new CAPParsingComponentException("Parameter " + _PrimitiveName + ": does not support encoding",
                CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {
        throw new CAPParsingComponentException("Parameter " + _PrimitiveName + ": does not support encoding",
                CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ActivityTestRequestImpl> ACTIVITY_TEST_REQUEST_XML = new XMLFormat<ActivityTestRequestImpl>(
            ActivityTestRequestImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ActivityTestRequestImpl activityTestRequest)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, activityTestRequest);
        }

        @Override
        public void write(ActivityTestRequestImpl activityTestRequest, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(activityTestRequest, xml);
        }
    };

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        sb.append("]");

        return sb.toString();
    }
}
