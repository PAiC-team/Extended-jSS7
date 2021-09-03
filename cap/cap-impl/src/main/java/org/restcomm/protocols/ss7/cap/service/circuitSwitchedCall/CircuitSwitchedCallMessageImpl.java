
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.MessageImpl;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPDialogCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CircuitSwitchedCallMessage;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public abstract class CircuitSwitchedCallMessageImpl extends MessageImpl implements CircuitSwitchedCallMessage, CAPAsnPrimitive {

    public CAPDialogCircuitSwitchedCall getCAPDialog() {
        return (CAPDialogCircuitSwitchedCall) super.getCAPDialog();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CircuitSwitchedCallMessageImpl> CIRCUIT_SWITCHED_CALL_MESSAGE_XML = new XMLFormat<CircuitSwitchedCallMessageImpl>(
            CircuitSwitchedCallMessageImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CircuitSwitchedCallMessageImpl message)
                throws XMLStreamException {
            CAP_MESSAGE_XML.read(xml, message);
        }

        @Override
        public void write(CircuitSwitchedCallMessageImpl message, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CAP_MESSAGE_XML.write(message, xml);
        }
    };

}
