
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
import org.restcomm.protocols.ss7.cap.api.gap.CallingAddressAndService;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sp. z o.o.)</a>
 */
public class CallingAddressAndServiceImpl extends SequenceBase implements CallingAddressAndService {

    public static final int _ID_callingAddressValue = 0;
    public static final int _ID_serviceKey = 1;

    private static final String CALLING_ADDRESS_VALUE = "callingAddressValue";
    private static final String SERVICE_KEY = "serviceKey";

    private Digits callingAddressValue;
    private int serviceKey;

    public CallingAddressAndServiceImpl() {
        super("CallingAddressAndService");
    }

    public CallingAddressAndServiceImpl(Digits callingAddressValue, int serviceKey) {
        super("CallingAddressAndService");
        this.callingAddressValue = callingAddressValue;
        this.serviceKey = serviceKey;
    }

    public Digits getCallingAddressValue() {
        return callingAddressValue;
    }

    public int getServiceKey() {
        return serviceKey;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws IOException, AsnException, CAPParsingComponentException {

        this.callingAddressValue = null;
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
                    case _ID_callingAddressValue: {
                        this.callingAddressValue = new DigitsImpl();
                        ((DigitsImpl) callingAddressValue).decodeAll(ais);
                        this.callingAddressValue.setIsGenericNumber();
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
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": serviceKey is mandatory",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
        if (this.callingAddressValue == null) {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": callingAddressValue is mandatory",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.callingAddressValue == null) {
            throw new CAPException("Error while encoding " + _PrimitiveName + ": callingAddressValue must not be null");
        }

        try {
            ((DigitsImpl) callingAddressValue).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_callingAddressValue);
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
    protected static final XMLFormat<CallingAddressAndServiceImpl> CALLING_ADDRESS_AND_SERVICE_XML = new XMLFormat<CallingAddressAndServiceImpl>(CallingAddressAndServiceImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CallingAddressAndServiceImpl callingAddressAndServiceImpl) throws XMLStreamException {

            callingAddressAndServiceImpl.callingAddressValue = xml.get(CALLING_ADDRESS_VALUE, DigitsImpl.class);
            callingAddressAndServiceImpl.serviceKey = xml.get(SERVICE_KEY, Integer.class);
        }

        @Override
        public void write(CallingAddressAndServiceImpl callingAddressAndServiceImpl, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            xml.add((DigitsImpl) callingAddressAndServiceImpl.getCallingAddressValue(), CALLING_ADDRESS_VALUE, DigitsImpl.class);
            xml.add((Integer) callingAddressAndServiceImpl.getServiceKey(), SERVICE_KEY, Integer.class);
        }
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (callingAddressValue != null) {
            sb.append("callingAddressValue=");
            sb.append(callingAddressValue.toString());
        }

        sb.append(", serviceKey=");
        sb.append(serviceKey);

        sb.append("]");

        return sb.toString();
    }

}
