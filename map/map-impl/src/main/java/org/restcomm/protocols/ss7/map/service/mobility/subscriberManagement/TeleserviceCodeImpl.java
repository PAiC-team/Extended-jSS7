
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class TeleserviceCodeImpl extends OctetStringLength1Base implements TeleserviceCode {
    private static final String TELE_SERVICE_CODE_VALUE = "teleserviceCodeValue";
    private static final String DATA = "data";

    public TeleserviceCodeImpl() {
        super("TeleserviceCode");
    }

    public TeleserviceCodeImpl(int data) {
        super("TeleserviceCode", data);
    }

    public TeleserviceCodeImpl(TeleserviceCodeValue value) {
        super("TeleserviceCode", value != null ? value.getCode() : 0);
    }

    public int getData() {
        return data;
    }

    public TeleserviceCodeValue getTeleserviceCodeValue() {
        return TeleserviceCodeValue.getInstance(this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        sb.append(this.getTeleserviceCodeValue());

        sb.append(", Data=");
        sb.append(this.data);

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<TeleserviceCodeImpl> TELE_SERVICE_CODE_XML = new XMLFormat<TeleserviceCodeImpl>(
            TeleserviceCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, TeleserviceCodeImpl ssCode) throws XMLStreamException {
            ssCode.data = xml.get(DATA, Integer.class);

            String str = xml.get(TELE_SERVICE_CODE_VALUE, String.class);
        }

        @Override
        public void write(TeleserviceCodeImpl ssCode, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add(ssCode.getData(), DATA, Integer.class);

            if (ssCode.getTeleserviceCodeValue() != null)
                xml.add((String) ssCode.getTeleserviceCodeValue().toString(), TELE_SERVICE_CODE_VALUE, String.class);
        }
    };

}
