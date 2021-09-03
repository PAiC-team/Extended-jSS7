
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBearerServiceCode;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ExtBearerServiceCodeImpl extends OctetStringBase implements ExtBearerServiceCode {

    private static final String BEARER_SERVICE_CODE_VALUE = "bearerServiceCodeValue";
    private static final String DEFAULT_STRING_VALUE = null;

    public ExtBearerServiceCodeImpl() {
        super(1, 5, "ExtBearerServiceCode");
    }

    public ExtBearerServiceCodeImpl(byte[] data) {
        super(1, 5, "ExtBearerServiceCode", data);
    }

    public ExtBearerServiceCodeImpl(BearerServiceCodeValue value) {
        super(1, 5, "ExtBearerServiceCode");
        setBearerServiceCode(value);
    }

    public void setBearerServiceCode(BearerServiceCodeValue value) {
//        if (value != null)
//            this.data = new byte[] { (byte) (value.getBearerServiceCode()) };

        if (value != null)
            this.data = new byte[] { (byte) (value.getCode()) };
    }

    public byte[] getData() {
        return data;
    }

    public BearerServiceCodeValue getBearerServiceCodeValue() {
        if (data == null || data.length < 1)
            return null;
        else
            return BearerServiceCodeValue.getInstance(this.data[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        sb.append(this.getBearerServiceCodeValue());

        sb.append(", Data=[");
        if (data != null) {
            for (int i1 : data) {
                sb.append(i1);
                sb.append(", ");
            }
        }
        sb.append("]");

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ExtBearerServiceCodeImpl> EXT_BEARER_SERVICE_CODE_XML = new XMLFormat<ExtBearerServiceCodeImpl>(
            ExtBearerServiceCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ExtBearerServiceCodeImpl extBearerServiceCode)
                throws XMLStreamException {
            String val = xml.getAttribute(BEARER_SERVICE_CODE_VALUE, DEFAULT_STRING_VALUE);
            if (val != null) {
                extBearerServiceCode.setBearerServiceCode(Enum.valueOf(BearerServiceCodeValue.class, val));
            }

            // Byte integ = xml.get(BEARER_SERVICE_CODE_VALUE, Byte.class);
            // extBearerServiceCode.data = new byte[]{integ};

            // if (integ != null) {
            // BearerServiceCodeValue bearerServiceCodeValue = BearerServiceCodeValue.getInstance(integ);
            // extBearerServiceCode.data = new byte[]{(byte)bearerServiceCodeValue.getCode()};
            //
            //
            // }
        }

        @Override
        public void write(ExtBearerServiceCodeImpl extBearerServiceCode, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            BearerServiceCodeValue val = extBearerServiceCode.getBearerServiceCodeValue();
            if (val != null)
                xml.setAttribute(BEARER_SERVICE_CODE_VALUE, val.toString());

            // BearerServiceCodeValue bearerServiceCodeValue = extBearerServiceCode.getBearerServiceCodeValue();
            // if (bearerServiceCodeValue != null) {
            // xml.add((byte) bearerServiceCodeValue.getBearerServiceCode(), BEARER_SERVICE_CODE_VALUE, Byte.class);
            // }
        }
    };
}
