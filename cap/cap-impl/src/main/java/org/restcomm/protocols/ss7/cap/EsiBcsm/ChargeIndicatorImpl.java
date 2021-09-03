
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.EsiBcsm.ChargeIndicator;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.ChargeIndicatorValue;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
*
* @author sergey vetyutnev
*
*/
public class ChargeIndicatorImpl extends OctetStringLength1Base implements ChargeIndicator {

    private static final String VALUE = "value";

    private static final String DEFAULT_VALUE = "";

    public ChargeIndicatorImpl() {
        super("ChargeIndicator");
    }

    public ChargeIndicatorImpl(int data) {
        super("ChargeIndicator", data);
    }

    public ChargeIndicatorImpl(ChargeIndicatorValue value) {
        super("ChargeIndicator");

        if (value != null)
            this.data = value.getCode();
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public ChargeIndicatorValue getChargeIndicatorValue() {
        return ChargeIndicatorValue.getInstance(data);
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ChargeIndicatorImpl> CHARGE_INDICATOR_XML = new XMLFormat<ChargeIndicatorImpl>(ChargeIndicatorImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ChargeIndicatorImpl chargeIndicator) throws XMLStreamException {
            String val = xml.getAttribute(VALUE, DEFAULT_VALUE);
            if (val != null) {
                ChargeIndicatorValue value = Enum.valueOf(ChargeIndicatorValue.class, val);
                if (value != null) {
                    chargeIndicator.data = value.getCode();
                }
            }
        }

        @Override
        public void write(ChargeIndicatorImpl chargeIndicator, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            ChargeIndicatorValue value = chargeIndicator.getChargeIndicatorValue();
            if (value != null)
                xml.setAttribute(VALUE, value.toString());
        }
    };

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        ChargeIndicatorValue value = this.getChargeIndicatorValue();
        if (value != null) {
            sb.append("chargeIndicatorValue=");
            sb.append(value);
        }

        sb.append("]");

        return sb.toString();
    }

}
