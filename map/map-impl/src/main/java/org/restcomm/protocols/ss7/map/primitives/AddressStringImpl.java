package org.restcomm.protocols.ss7.map.primitives;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class AddressStringImpl implements AddressString, MAPAsnPrimitive {

    private static final String NAI = "nai";
    private static final String NPI = "npi";
    private static final String NUMBER = "number";

    private static final String DEFAULT_STRING_VALUE = null;

    protected int NO_EXTENSION_MASK = 0x80;
    protected int NATURE_OF_ADD_IND_MASK = 0x70;
    protected int NUMBERING_PLAN_IND_MASK = 0x0F;

    protected AddressNature addressNature;
    protected NumberingPlan numberingPlan;
    protected String address;

    private boolean isExtension;

    public AddressStringImpl() {
    }

    public AddressStringImpl(AddressNature addressNature, NumberingPlan numberingPlan, String address) {
        super();
        this.addressNature = addressNature;
        this.numberingPlan = numberingPlan;
        this.address = address;
    }

    public AddressStringImpl(boolean isExtension, AddressNature addressNature, NumberingPlan numberingPlan, String address) {
        super();
        this.isExtension = isExtension;
        this.addressNature = addressNature;
        this.numberingPlan = numberingPlan;
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public AddressNature getAddressNature() {
        return this.addressNature;
    }

    public NumberingPlan getNumberingPlan() {
        return this.numberingPlan;
    }

    public boolean isExtension() {
        return isExtension;
    }

    public int getTag() throws MAPException {
        return Tag.STRING_OCTET;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return true;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding AddressString: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding AddressString: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    protected void _testLengthDecode(int length) throws MAPParsingComponentException {
        if (length > 20)
            throw new MAPParsingComponentException("Error when decoding AddressString: mesage length must not exceed 20",
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException {

        this._testLengthDecode(length);

        // The first byte has extension, nature of address indicator and
        // numbering plan indicator
        int nature = asnInputStream.read();

        if ((nature & NO_EXTENSION_MASK) == 0x80) {
            this.isExtension = false;
        } else {
            this.isExtension = true;
        }

        int natureOfAddInd = ((nature & NATURE_OF_ADD_IND_MASK) >> 4);

        this.addressNature = AddressNature.getInstance(natureOfAddInd);

        int numbPlanInd = (nature & NUMBERING_PLAN_IND_MASK);

        this.numberingPlan = NumberingPlan.getInstance(numbPlanInd);

        this.address = TbcdString.decodeString(asnInputStream, length - 1);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.STRING_OCTET);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, true, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding AddressString: " + e.getMessage(), e);
        }
    }

    protected void _testLengthEncode() throws MAPException {
        if (this.address.length() > 38)
            throw new MAPException("Error when encoding AddressString: address length must not exceed 38 digits");
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.addressNature == null || this.numberingPlan == null || this.address == null)
            throw new MAPException("Error when encoding AddressString: addressNature, numberingPlan or address is empty");

        this._testLengthEncode();

        int nature = 1;

        if (this.isExtension) {
            nature = 0;
        }

        nature = nature << 7;

        nature = nature | (this.addressNature.getIndicator() << 4);

        nature = nature | (this.numberingPlan.getIndicator());

        asnOutputStream.write(nature);

        TbcdString.encodeString(asnOutputStream, this.address);
    }

    @Override
    public String toString() {
        return "AddressString[AddressNature=" + this.addressNature.toString() + ", NumberingPlan="
                + this.numberingPlan.toString() + ", Address=" + this.address + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((addressNature == null) ? 0 : addressNature.hashCode());
        result = prime * result + ((numberingPlan == null) ? 0 : numberingPlan.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressStringImpl other = (AddressStringImpl) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (addressNature != other.addressNature)
            return false;
        if (numberingPlan != other.numberingPlan)
            return false;
        return true;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<AddressStringImpl> ADDRESS_STRING_XML = new XMLFormat<AddressStringImpl>(
            AddressStringImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, AddressStringImpl addressStringImpl)
                throws XMLStreamException {
            addressStringImpl.address = xml.getAttribute(NUMBER, "");

            String nai = xml.getAttribute(NAI, DEFAULT_STRING_VALUE);
            String npi = xml.getAttribute(NPI, DEFAULT_STRING_VALUE);
            if (nai != null) {
                addressStringImpl.addressNature = Enum.valueOf(AddressNature.class, nai);
            }
            if (npi != null) {
                addressStringImpl.numberingPlan = Enum.valueOf(NumberingPlan.class, npi);
            }
        }

        @Override
        public void write(AddressStringImpl addressStringImpl, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {

            xml.setAttribute(NUMBER, addressStringImpl.address);
            xml.setAttribute(NAI, addressStringImpl.addressNature.toString());
            xml.setAttribute(NPI, addressStringImpl.numberingPlan.toString());
        }
    };
}
