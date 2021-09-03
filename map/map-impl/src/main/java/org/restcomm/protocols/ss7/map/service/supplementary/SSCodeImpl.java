
package org.restcomm.protocols.ss7.map.service.supplementary;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class SSCodeImpl extends OctetStringLength1Base implements SSCode {

    private static final String SUPPLEMENTARY_CODE_VALUE = "supplementaryCodeValue";
    private static final String DATA = "data";

    public SSCodeImpl() {
        super("SSCode");
    }

    public SSCodeImpl(int data) {
        super("SSCode", data);
    }

    public SSCodeImpl(SupplementaryCodeValue value) {
        super("SSCode", value != null ? value.getCode() : 0);
    }

    @Override
    public int getData() {
        return this.data;
    }

    @Override
    public SupplementaryCodeValue getSupplementaryCodeValue() {
        return SupplementaryCodeValue.getInstance(this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        SupplementaryCodeValue scv = this.getSupplementaryCodeValue();
        if (scv != null) {
            sb.append("SupplementaryCodeValue=" + scv);
            sb.append(", ");
        }
        sb.append("Data=" + this.data);
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<SSCodeImpl> SS_CODE = new XMLFormat<SSCodeImpl>(SSCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, SSCodeImpl ssCode) throws XMLStreamException {
            ssCode.data = xml.get(DATA, Integer.class);

            String str = xml.get(SUPPLEMENTARY_CODE_VALUE, String.class);
        }

        @Override
        public void write(SSCodeImpl ssCode, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add(ssCode.getData(), DATA, Integer.class);

            if (ssCode.getSupplementaryCodeValue() != null)
                xml.add((String) ssCode.getSupplementaryCodeValue().toString(), SUPPLEMENTARY_CODE_VALUE, String.class);
        }
    };

}
