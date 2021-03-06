
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import java.io.IOException;
import java.util.ArrayList;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DestinationRoutingAddress;
import org.restcomm.protocols.ss7.cap.isup.CalledPartyNumberCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.primitives.ArrayListSerializingBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class DestinationRoutingAddressImpl extends SequenceBase implements DestinationRoutingAddress {

    private static final String CALLED_PARTY_NUMBER = "calledPartyNumber";
    private static final String CALLED_PARTY_NUMBER_LIST = "calledPartyNumberList";

    public ArrayList<CalledPartyNumberCap> calledPartyNumber;

    public DestinationRoutingAddressImpl() {
        super("DestinationRoutingAddress");
    }

    public DestinationRoutingAddressImpl(ArrayList<CalledPartyNumberCap> calledPartyNumber) {
        super("DestinationRoutingAddress");

        this.calledPartyNumber = calledPartyNumber;
    }

    @Override
    public ArrayList<CalledPartyNumberCap> getCalledPartyNumber() {
        return calledPartyNumber;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.calledPartyNumber = new ArrayList<CalledPartyNumberCap>();

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL)
                throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": bad tag or tagClass when decoding CalledPartyNumber",
                        CAPParsingComponentExceptionReason.MistypedParameter);

            CalledPartyNumberCap cpn = new CalledPartyNumberCapImpl();
            ((CalledPartyNumberCapImpl) cpn).decodeAll(ais);
            this.calledPartyNumber.add(cpn);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.calledPartyNumber == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": calledPartyNumber must not be null");
        if (this.calledPartyNumber.size() != 1)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": calledPartyNumber count must be equal 1");

        for (CalledPartyNumberCap cpn : this.calledPartyNumber) {
            ((CalledPartyNumberCapImpl) cpn).encodeAll(asnOutputStream);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.calledPartyNumber != null) {
            sb.append("calledPartyNumber=[");
            for (CalledPartyNumberCap cpn : this.calledPartyNumber) {
                sb.append(cpn.toString());
                sb.append(", ");
            }
            sb.append("]");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<DestinationRoutingAddressImpl> DESTINATION_ROUTING_ADDRESS_XML = new XMLFormat<DestinationRoutingAddressImpl>(
            DestinationRoutingAddressImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, DestinationRoutingAddressImpl destinationRoutingAddress)
                throws XMLStreamException {
            DestinationRoutingAddress_CalledPartyNumbers al = xml.get(CALLED_PARTY_NUMBER_LIST,
                    DestinationRoutingAddress_CalledPartyNumbers.class);
            if (al != null) {
                destinationRoutingAddress.calledPartyNumber = al.getData();
            }
        }

        @Override
        public void write(DestinationRoutingAddressImpl destinationRoutingAddress, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (destinationRoutingAddress.calledPartyNumber != null) {
                DestinationRoutingAddress_CalledPartyNumbers al = new DestinationRoutingAddress_CalledPartyNumbers(
                        destinationRoutingAddress.calledPartyNumber);
                xml.add(al, CALLED_PARTY_NUMBER_LIST, DestinationRoutingAddress_CalledPartyNumbers.class);
            }
        }
    };

    public static class DestinationRoutingAddress_CalledPartyNumbers extends ArrayListSerializingBase<CalledPartyNumberCap> {

        public DestinationRoutingAddress_CalledPartyNumbers() {
            super(CALLED_PARTY_NUMBER, CalledPartyNumberCapImpl.class);
        }

        public DestinationRoutingAddress_CalledPartyNumbers(ArrayList<CalledPartyNumberCap> data) {
            super(CALLED_PARTY_NUMBER, CalledPartyNumberCapImpl.class, data);
        }

    }
}
