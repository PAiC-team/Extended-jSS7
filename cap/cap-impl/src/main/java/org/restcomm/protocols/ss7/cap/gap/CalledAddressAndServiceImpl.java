
package org.restcomm.protocols.ss7.cap.gap;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.gap.CalledAddressAndService;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sp. z o.o.)</a>
 */
public class CalledAddressAndServiceImpl extends SequenceBase implements CalledAddressAndService {

    public static final int _ID_calledAddressValue = 0;
    public static final int _ID_serviceKey = 1;

    private static final String CALLED_ADDRESS_VALUE = "calledAddressValue";
    private static final String SERVICE_KEY = "serviceKey";

    private Digits calledAddressValue;
    private int serviceKey;

    public CalledAddressAndServiceImpl() {
        super("CalledAddressAndService");
    }

    public CalledAddressAndServiceImpl(Digits calledAddressValue, int serviceKey) {
        super("CalledAddressAndService");
        this.calledAddressValue = calledAddressValue;
        this.serviceKey = serviceKey;
    }

    public Digits getCalledAddressValue() {
        return calledAddressValue;
    }

    public int getServiceKey() {
        return serviceKey;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws IOException, AsnException, CAPParsingComponentException {

        this.calledAddressValue = null;
        this.serviceKey = 0;
        boolean serviceKeyFound = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0) {
                break;
            }

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_calledAddressValue: {
                        this.calledAddressValue = new DigitsImpl();
                        ((DigitsImpl) calledAddressValue).decodeAll(ais);
                        this.calledAddressValue.setIsGenericNumber();
                        break;
                    }
                    case _ID_serviceKey: {
                        if (!ais.isTagPrimitive()) {
                            throw new CAPParsingComponentException(
                                    "Error while decoding InitialDPRequest: Parameter " + _ID_serviceKey + " not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        this.serviceKey = (int) ais.readInteger();
                        serviceKeyFound = true;
                        break;
                    }
                    default: {
                        ais.advanceElement();
                        break;
                    }
                }
            } else {
                ais.advanceElement();
            }
        }

        if (!serviceKeyFound) {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": serviceKey is mandatory",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.calledAddressValue == null) {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": calledAddressValue is mandatory",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.calledAddressValue == null) {
            throw new CAPException("Error while encoding " + _PrimitiveName + ": calledAddressValue must not be null");
        }

        try {
            ((DigitsImpl) calledAddressValue).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_calledAddressValue);
            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_serviceKey, this.serviceKey);
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CalledAddressAndServiceImpl> CALLED_ADDRESS_AND_SERVICE_XML = new XMLFormat<CalledAddressAndServiceImpl>(CalledAddressAndServiceImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CalledAddressAndServiceImpl calledAddressAndServiceImpl) throws XMLStreamException {

            calledAddressAndServiceImpl.calledAddressValue = xml.get(CALLED_ADDRESS_VALUE, DigitsImpl.class);
            calledAddressAndServiceImpl.serviceKey = xml.get(SERVICE_KEY, Integer.class);
        }

        @Override
        public void write(CalledAddressAndServiceImpl calledAddressAndServiceImpl, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            xml.add((DigitsImpl) calledAddressAndServiceImpl.getCalledAddressValue(), CALLED_ADDRESS_VALUE, DigitsImpl.class);
            xml.add((Integer) calledAddressAndServiceImpl.getServiceKey(), SERVICE_KEY, Integer.class);
        }
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (calledAddressValue != null) {
            sb.append("calledAddressValue=");
            sb.append(calledAddressValue.toString());
        }

        sb.append(", serviceKey=");
        sb.append(serviceKey);

        sb.append("]");

        return sb.toString();
    }

}
