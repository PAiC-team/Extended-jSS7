
package org.restcomm.protocols.ss7.cap.isup;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.OriginalCalledNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.OriginalCalledNumber;

/**
 *
 *
 * @author sergey vetyutnev
 *
 */
public class OriginalCalledNumberCapImpl extends OctetStringBase implements OriginalCalledNumberCap {

    private static final String ISUP_ORIGINAL_CALLED_NUMBER_XML = "isupOriginalCalledNumber";

    public OriginalCalledNumberCapImpl() {
        super(2, 12, "OriginalCalledNumberCap");
    }

    public OriginalCalledNumberCapImpl(byte[] data) {
        super(2, 12, "OriginalCalledNumberCap", data);
    }

    public OriginalCalledNumberCapImpl(OriginalCalledNumber originalCalledNumber) throws CAPException {
        super(2, 12, "OriginalCalledNumberCap");
        setOriginalCalledNumber(originalCalledNumber);
    }

    public void setOriginalCalledNumber(OriginalCalledNumber originalCalledNumber) throws CAPException {
        if (originalCalledNumber == null)
            throw new CAPException("The originalCalledNumber parameter must not be null");
        try {
            this.data = ((OriginalCalledNumberImpl) originalCalledNumber).encode();
        } catch (ParameterException e) {
            throw new CAPException("ParameterException when encoding originalCalledNumber: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public OriginalCalledNumber getOriginalCalledNumber() throws CAPException {
        if (this.data == null)
            throw new CAPException("The data has not been filled");

        try {
            OriginalCalledNumberImpl ocn = new OriginalCalledNumberImpl();
            ocn.decode(this.data);
            return ocn;
        } catch (ParameterException e) {
            throw new CAPException("ParameterException when decoding OriginalCalledNumber: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.data != null) {
            sb.append("data=[");
            sb.append(printDataArr());
            sb.append("]");
            try {
                OriginalCalledNumber ocn = this.getOriginalCalledNumber();
                sb.append(", ");
                sb.append(ocn.toString());
            } catch (CAPException e) {
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<OriginalCalledNumberCapImpl> ORIGINAL_CALLED_NUMBER_CAP_XML = new XMLFormat<OriginalCalledNumberCapImpl>(
            OriginalCalledNumberCapImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, OriginalCalledNumberCapImpl originalCalledNumber)
                throws XMLStreamException {
            try {
                originalCalledNumber.setOriginalCalledNumber(xml.get(ISUP_ORIGINAL_CALLED_NUMBER_XML,
                        OriginalCalledNumberImpl.class));
            } catch (CAPException e) {
                throw new XMLStreamException(e);
            }
        }

        @Override
        public void write(OriginalCalledNumberCapImpl originalCalledNumber, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            try {
                xml.add(((OriginalCalledNumberImpl) originalCalledNumber.getOriginalCalledNumber()),
                        ISUP_ORIGINAL_CALLED_NUMBER_XML, OriginalCalledNumberImpl.class);
            } catch (CAPException e) {
                throw new XMLStreamException(e);
            }
        }
    };
}
