
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;


import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDPType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDPTypeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PDPTypeImpl extends OctetStringBase implements PDPType {

    private static final String PDP_TYPE_VALUE = "pdpTypeValue";

    private static final String DEFAULT_VALUE = null;

    public static final int _VALUE_ETSI = 0xF0 + 0; // PPP
    public static final int _VALUE_IETF = 0xF0 + 1; // IPv4, IPv6

    public static final int _VALUE_PPP = 1;
    public static final int _VALUE_IPv4 = 33;
    public static final int _VALUE_IPv6 = 87;

    public PDPTypeImpl() {
        super(2, 2, "PDPType");
    }

    public PDPTypeImpl(byte[] data) {
        super(2, 2, "PDPType", data);
    }

    public PDPTypeImpl(PDPTypeValue value) {
        super(2, 2, "PDPType");

        this.setPDPTypeValue(value);
    }

    protected void setPDPTypeValue(PDPTypeValue value) {
        this.data = new byte[2];

        switch (value) {
        case PPP:
            this.data[0] = (byte) _VALUE_ETSI;
            this.data[1] = (byte) _VALUE_PPP;
            break;
        case IPv4:
            this.data[0] = (byte) _VALUE_IETF;
            this.data[1] = (byte) _VALUE_IPv4;
            break;
        case IPv6:
            this.data[0] = (byte) _VALUE_IETF;
            this.data[1] = (byte) _VALUE_IPv6;
            break;
        }
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public PDPTypeValue getPDPTypeValue() {
        if (this.data != null && this.data.length == 2) {
            if ((this.data[0] & 0xFF) == _VALUE_ETSI) {
                if (this.data[1] == _VALUE_PPP)
                    return PDPTypeValue.PPP;
            } else if ((this.data[0] & 0xFF) == _VALUE_IETF) {
                if (this.data[1] == _VALUE_IPv4)
                    return PDPTypeValue.IPv4;
                if (this.data[1] == _VALUE_IPv6)
                    return PDPTypeValue.IPv6;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        PDPTypeValue value = this.getPDPTypeValue();
        if (value != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(_PrimitiveName);
            sb.append(" [PDPTypeValue=");
            sb.append(value);
            sb.append("]");

            return sb.toString();
        } else {
            return super.toString();
        }
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<PDPTypeImpl> PDP_TYPE_XML = new XMLFormat<PDPTypeImpl>(PDPTypeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, PDPTypeImpl pdpType) throws XMLStreamException {
            String s = xml.getAttribute(PDP_TYPE_VALUE, DEFAULT_VALUE);
            if (s != null) {
                pdpType.setPDPTypeValue(Enum.valueOf(PDPTypeValue.class, s));
            }
        }

        @Override
        public void write(PDPTypeImpl pdpType, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (pdpType.getPDPTypeValue() != null) {
                xml.setAttribute(PDP_TYPE_VALUE, pdpType.getPDPTypeValue().toString());
            }
        }
    };
}
