
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FreeFormatDataSMS;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ByteArrayContainer;

/**
 *
 * @author Lasith Waruna Perera
 * @author alerant appngin
 *
 */
public class FreeFormatDataSMSImpl extends OctetStringBase implements FreeFormatDataSMS {

    private static final String DATA = "data";

    public FreeFormatDataSMSImpl() {
        super(1, 160, "FreeFormatDataSMS");
    }

    public FreeFormatDataSMSImpl(byte[] data) {
        super(1, 160, "FreeFormatDataSMS", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<FreeFormatDataSMSImpl> FREE_FORMAT_DATA_XML = new XMLFormat<FreeFormatDataSMSImpl>(
            FreeFormatDataSMSImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, FreeFormatDataSMSImpl freeFormatDataSMS)
                throws XMLStreamException {
            ByteArrayContainer bc = xml.get(DATA, ByteArrayContainer.class);
            if (bc != null) {
                freeFormatDataSMS.data = bc.getData();
            }
        }

        @Override
        public void write(FreeFormatDataSMSImpl freeFormatDataSMS, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (freeFormatDataSMS.data != null) {
                ByteArrayContainer bac = new ByteArrayContainer(freeFormatDataSMS.data);
                xml.add(bac, DATA, ByteArrayContainer.class);
            }
        }
    };
}
