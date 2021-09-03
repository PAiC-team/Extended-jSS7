
package org.restcomm.protocols.ss7.map.service.callhandling;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.isup.impl.message.parameter.ByteArrayContainer;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallReferenceNumberImpl extends OctetStringBase implements CallReferenceNumber {

    private static final String DATA_XML = "data";

    public CallReferenceNumberImpl() {
        super(1, 8, "CallReferenceNumber");
    }

    public CallReferenceNumberImpl(byte[] data) {
        super(1, 8, "CallReferenceNumber", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CallReferenceNumberImpl> CALL_REFERENCE_NUMBER_XML = new XMLFormat<CallReferenceNumberImpl>(
            CallReferenceNumberImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CallReferenceNumberImpl crn) throws XMLStreamException {
            ByteArrayContainer bc = xml.get(DATA_XML, ByteArrayContainer.class);
            if (bc != null) {
                crn.data = bc.getData();
            }
        }

        @Override
        public void write(CallReferenceNumberImpl crn, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (crn.data != null && crn.data.length > 0) {
                ByteArrayContainer bac = new ByteArrayContainer(crn.data);
                xml.add(bac, DATA_XML, ByteArrayContainer.class);
            }
        }
    };
}
