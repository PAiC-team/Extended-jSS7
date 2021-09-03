package org.restcomm.protocols.ss7.inap.isup;

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
import org.restcomm.protocols.ss7.inap.isup.CallingPartysCategoryInapImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CallingPartyCategoryImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyCategory;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallingPartysCategoryInapTest {

    public byte[] getData() {
        return new byte[] { (byte) 133, 1, 10 };
    }

    public byte[] getIntData() {
        return new byte[] { 10 };
    }

    @Test(groups = { "functional.decode", "isup" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        CallingPartysCategoryInapImpl elem = new CallingPartysCategoryInapImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        CallingPartyCategory cpc = elem.getCallingPartyCategory();
        assertTrue(Arrays.equals(elem.getData(), this.getIntData()));
        assertEquals(cpc.getCallingPartyCategory(), 10);
    }

    @Test(groups = { "functional.encode", "isup" })
    public void testEncode() throws Exception {

        CallingPartysCategoryInapImpl elem = new CallingPartysCategoryInapImpl(this.getIntData());
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 5);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));

        CallingPartyCategory cpc = new CallingPartyCategoryImpl((byte) 10);
        elem = new CallingPartysCategoryInapImpl(cpc);
        aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 5);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));

        // byte callingPartyCategory
    }

    @Test(groups = { "functional.xml.serialize", "isup" })
    public void testXMLSerialize() throws Exception {

        CallingPartysCategoryInapImpl original = new CallingPartysCategoryInapImpl(new CallingPartyCategoryImpl((byte) 10));

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "callingPartysCategoryInap", CallingPartysCategoryInapImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CallingPartysCategoryInapImpl copy = reader.read("callingPartysCategoryInap", CallingPartysCategoryInapImpl.class);

        assertEquals(copy.getCallingPartyCategory().getCallingPartyCategory(), original.getCallingPartyCategory()
                .getCallingPartyCategory());

    }
}
