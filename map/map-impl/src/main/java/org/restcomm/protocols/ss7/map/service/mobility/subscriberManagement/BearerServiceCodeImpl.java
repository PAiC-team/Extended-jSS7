
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class BearerServiceCodeImpl extends OctetStringLength1Base implements BearerServiceCode {

    private static final String BEARER_SERVICE_CODE_VALUE = "bearerServiceCodeValue";
    private static final String DATA = "data";

    public BearerServiceCodeImpl() {
        super("BearerServiceCode");
    }

    public BearerServiceCodeImpl(int data) {
        super("BearerServiceCode", data);
    }

    public BearerServiceCodeImpl(BearerServiceCodeValue value) {
        // super("BearerServiceCode", value != null ? value.getBearerServiceCode() : 0);
        super("BearerServiceCode", value != null ? value.getCode() : 0);
    }

    public int getData() {
        return data;
    }

    public BearerServiceCodeValue getBearerServiceCodeValue() {
        return BearerServiceCodeValue.getInstance(this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        sb.append(this.getBearerServiceCodeValue());

        sb.append(", Data=");
        sb.append(this.data);

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<BearerServiceCodeImpl> BEARER_SERVICE_CODE_XML = new XMLFormat<BearerServiceCodeImpl>(
            BearerServiceCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BearerServiceCodeImpl ssCode) throws XMLStreamException {
            ssCode.data = xml.get(DATA, Integer.class);

            String str = xml.get(BEARER_SERVICE_CODE_VALUE, String.class);
        }

        @Override
        public void write(BearerServiceCodeImpl ssCode, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add(ssCode.getData(), DATA, Integer.class);

            if (ssCode.getBearerServiceCodeValue() != null)
                xml.add((String) ssCode.getBearerServiceCodeValue().toString(), BEARER_SERVICE_CODE_VALUE, String.class);
        }
    };
}
