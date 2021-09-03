
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.VariablePartDate;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class VariablePartDateImpl extends OctetStringBase implements VariablePartDate {

    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";

    public VariablePartDateImpl() {
        super(4, 4, "VariablePartDate");
    }

    public VariablePartDateImpl(byte[] data) {
        super(4, 4, "VariablePartDate");

        this.data = data;
    }

    public VariablePartDateImpl(int year, int month, int day) {
        super(4, 4, "VariablePartDate");
        this.data = new byte[4];

        setYear(year);
        setMonth(month);
        setDay(day);
    }

    protected void setYear(int year) {
        if (this.data == null || this.data.length != 4)
            return;

        this.data[0] = (byte) this.encodeByte(year / 100);
        this.data[1] = (byte) this.encodeByte(year % 100);
    }

    protected void setMonth(int month) {
        if (this.data == null || this.data.length != 4)
            return;

        this.data[2] = (byte) this.encodeByte(month);
    }

    protected void setDay(int day) {
        if (this.data == null || this.data.length != 4)
            return;

        this.data[3] = (byte) this.encodeByte(day);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public int getYear() {

        if (this.data == null || this.data.length != 4)
            return 0;

        return this.decodeByte(data[0]) * 100 + this.decodeByte(data[1]);
    }

    @Override
    public int getMonth() {

        if (this.data == null || this.data.length != 4)
            return 0;

        return this.decodeByte(data[2]);
    }

    @Override
    public int getDay() {

        if (this.data == null || this.data.length != 4)
            return 0;

        return this.decodeByte(data[3]);
    }

    private int decodeByte(int bt) {
        return (bt & 0x0F) * 10 + ((bt & 0xF0) >> 4);
    }

    private int encodeByte(int val) {
        return (val / 10) | (val % 10) << 4;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.data != null && this.data.length == 4) {
            sb.append("year=");
            sb.append(this.getYear());
            sb.append(", month=");
            sb.append(this.getMonth());
            sb.append(", day=");
            sb.append(this.getDay());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<VariablePartDateImpl> VARIABLE_PART_DATE_XML = new XMLFormat<VariablePartDateImpl>(
            VariablePartDateImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, VariablePartDateImpl variablePartDate)
                throws XMLStreamException {
            variablePartDate.data = new byte[4];

            variablePartDate.setYear(xml.getAttribute(YEAR, 0));
            variablePartDate.setMonth(xml.getAttribute(MONTH, 0));
            variablePartDate.setDay(xml.getAttribute(DAY, 0));
        }

        @Override
        public void write(VariablePartDateImpl variablePartDate, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            xml.setAttribute(YEAR, variablePartDate.getYear());
            xml.setAttribute(MONTH, variablePartDate.getMonth());
            xml.setAttribute(DAY, variablePartDate.getDay());
        }
    };
}
