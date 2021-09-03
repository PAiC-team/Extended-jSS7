package org.restcomm.protocols.ss7.cap.primitives;

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
import org.restcomm.protocols.ss7.cap.primitives.ReceivingSideIDImpl;
import org.restcomm.protocols.ss7.inap.api.primitives.LegType;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ReceivingSideIDTest {

    public byte[] getData1() {
        return new byte[] { (byte) 129, 1, 2 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        ReceivingSideIDImpl elem = new ReceivingSideIDImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertEquals(elem.getReceivingSideID(), LegType.leg2);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        ReceivingSideIDImpl elem = new ReceivingSideIDImpl(LegType.leg2);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 1);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }

    @Test(groups = { "functional.xml.serialize", "primitives" })
    public void testXMLSerializaion() throws Exception {
        ReceivingSideIDImpl original = new ReceivingSideIDImpl(LegType.leg2);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "receivingSideID", ReceivingSideIDImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        ReceivingSideIDImpl copy = reader.read("receivingSideID", ReceivingSideIDImpl.class);

        assertEquals(copy.getReceivingSideID(), original.getReceivingSideID());
    }
}
