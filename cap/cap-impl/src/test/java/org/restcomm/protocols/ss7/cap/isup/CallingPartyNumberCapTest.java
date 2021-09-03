
package org.restcomm.protocols.ss7.cap.isup;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.isup.CallingPartyNumberCapImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CallingPartyNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyNumber;
import org.restcomm.protocols.ss7.isup.message.parameter.NAINumber;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallingPartyNumberCapTest {

    public byte[] getData() {
        return new byte[] { (byte) 131, 8, (byte) 132, 17, 20, (byte) 135, 9, 80, 64, (byte) 7 }; // 247
    }

    public byte[] getIntData() {
        return new byte[] { (byte) 132, 17, 20, (byte) 135, 9, 80, 64, (byte) 7 }; // 247
    }

    @Test(groups = { "functional.decode", "isup" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        CallingPartyNumberCapImpl elem = new CallingPartyNumberCapImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        CallingPartyNumber cpn = elem.getCallingPartyNumber();
        assertTrue(Arrays.equals(elem.getData(), this.getIntData()));
        assertTrue(cpn.isOddFlag());
        assertEquals(cpn.getNumberingPlanIndicator(), 1);
        assertEquals(cpn.getScreeningIndicator(), 1);
        assertEquals(cpn.getAddressRepresentationRestrictedIndicator(), 0);
        assertEquals(cpn.getNumberIncompleteIndicator(), 0);
        assertEquals(cpn.getNatureOfAddressIndicator(), 4);
        assertTrue(cpn.getAddress().equals("41789005047"));
    }

    @Test(groups = { "functional.encode", "isup" })
    public void testEncode() throws Exception {

        CallingPartyNumberCapImpl elem = new CallingPartyNumberCapImpl(this.getIntData());
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 3);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));

        CallingPartyNumber cpn = new CallingPartyNumberImpl(4, "41789005047", 1, 0, 0, 1);
        elem = new CallingPartyNumberCapImpl(cpn);
        aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 3);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));

        // int natureOfAddresIndicator, String address, int numberingPlanIndicator, int numberIncompleteIndicator, int
        // addressRepresentationREstrictedIndicator,
        // int screeningIndicator
    }

    @Test(groups = { "functional.xml.serialize", "isup" })
    public void testXMLSerialize() throws Exception {

        CallingPartyNumberCapImpl original = new CallingPartyNumberCapImpl(new CallingPartyNumberImpl(
                NAINumber._NAI_NATIONAL_SN, "12345", CallingPartyNumber._NPI_TELEX, CallingPartyNumber._NI_INCOMPLETE,
                CallingPartyNumber._APRI_ALLOWED, CallingPartyNumber._SI_USER_PROVIDED_FAILED));

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "callingPartyNumberCap", CallingPartyNumberCapImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CallingPartyNumberCapImpl copy = reader.read("callingPartyNumberCap", CallingPartyNumberCapImpl.class);

        assertEquals(copy.getCallingPartyNumber().getNatureOfAddressIndicator(), original.getCallingPartyNumber()
                .getNatureOfAddressIndicator());
        assertEquals(copy.getCallingPartyNumber().getAddress(), original.getCallingPartyNumber().getAddress());
        assertEquals(copy.getCallingPartyNumber().getNumberingPlanIndicator(), original.getCallingPartyNumber()
                .getNumberingPlanIndicator());
        assertEquals(copy.getCallingPartyNumber().getNumberIncompleteIndicator(), original.getCallingPartyNumber()
                .getNumberIncompleteIndicator());
        assertEquals(copy.getCallingPartyNumber().getAddressRepresentationRestrictedIndicator(), original
                .getCallingPartyNumber().getAddressRepresentationRestrictedIndicator());
        assertEquals(copy.getCallingPartyNumber().getScreeningIndicator(), original.getCallingPartyNumber()
                .getScreeningIndicator());

    }
}
