
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.FreeFormatData;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ByteArrayContainer;

/**
*
* @author Lasith Waruna Perera
* @author sergey vetyutnev
*
*/
public class FreeFormatDataImpl extends OctetStringBase implements FreeFormatData {

    private static final String DATA = "data";

    public FreeFormatDataImpl() {
        super(1, 160, "FreeFormatData");
    }

    public FreeFormatDataImpl(byte[] data) {
        super(1, 160, "FreeFormatData", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<FreeFormatDataImpl> FREE_FORMAT_DATA_XML = new XMLFormat<FreeFormatDataImpl>(
            FreeFormatDataImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, FreeFormatDataImpl freeFormatData)
                throws XMLStreamException {
            ByteArrayContainer bc = xml.get(DATA, ByteArrayContainer.class);
            if (bc != null) {
                freeFormatData.data = bc.getData();
            }
        }

        @Override
        public void write(FreeFormatDataImpl freeFormatData, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (freeFormatData.data != null) {
                ByteArrayContainer bac = new ByteArrayContainer(freeFormatData.data);
                xml.add(bac, DATA, ByteArrayContainer.class);
            }
        }
    };

}
