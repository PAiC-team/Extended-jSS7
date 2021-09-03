
package org.restcomm.protocols.ss7.map.service.callhandling;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.callhandling.UUIndicator;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
*
* @author sergey vetyutnev
*
*/
public class UUIndicatorImpl extends OctetStringLength1Base implements UUIndicator {

    private static final String DATA = "data";

    private static final int DEFAULT_VALUE = 0;

    public UUIndicatorImpl() {
        super("UUIndicator");
    }

    public UUIndicatorImpl(int data) {
        super("UUIndicator", data);
    }

    public int getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<UUIndicatorImpl> UU_INDICATOR_XML = new XMLFormat<UUIndicatorImpl>(UUIndicatorImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, UUIndicatorImpl uuIndicator) throws XMLStreamException {
            Integer i1 = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (i1 != null) {
                uuIndicator.data = i1;
            }
        }

        @Override
        public void write(UUIndicatorImpl uuIndicator, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(DATA, uuIndicator.data);
        }
    };

}
