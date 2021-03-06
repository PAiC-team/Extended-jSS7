
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
import org.restcomm.protocols.ss7.cap.api.gap.GapOnService;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sp. z o.o.)</a>
 */
public class GapOnServiceImpl extends SequenceBase implements GapOnService {

    public static final int _ID_serviceKey = 0;

    private static final String SERVICE_KEY = "serviceKey";

    private int serviceKey;

    public GapOnServiceImpl() {
        super("GapOnService");
    }

    public GapOnServiceImpl(int serviceKey) {
        super("GapOnService");

        this.serviceKey = serviceKey;
    }

    public int getServiceKey() {
        return serviceKey;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws IOException, AsnException, CAPParsingComponentException {

        this.serviceKey = 0;
        boolean foundServiceKey = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0) {
                break;
            }

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_serviceKey: {
                        this.serviceKey = (int) ais.readInteger();
                        foundServiceKey = true;
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

        if (!foundServiceKey) {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": serviceKey is mandatory",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        try {
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
    protected static final XMLFormat<GapOnServiceImpl> GAP_ON_SERVICE_XML = new XMLFormat<GapOnServiceImpl>(GapOnServiceImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, GapOnServiceImpl gapOnServiceImpl) throws XMLStreamException {

            gapOnServiceImpl.serviceKey = xml.get(SERVICE_KEY, Integer.class);
        }

        @Override
        public void write(GapOnServiceImpl gapOnServiceImpl, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            xml.add((Integer) gapOnServiceImpl.getServiceKey(), SERVICE_KEY, Integer.class);
        }
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("serviceKey=");
        sb.append(serviceKey);

        sb.append("]");

        return sb.toString();
    }

}
