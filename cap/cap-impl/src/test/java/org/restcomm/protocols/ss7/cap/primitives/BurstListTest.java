
package org.restcomm.protocols.ss7.cap.primitives;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.primitives.BurstImpl;
import org.restcomm.protocols.ss7.cap.primitives.BurstListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class BurstListTest {

    public byte[] getData1() {
        return new byte[] { 48, 8, (byte) 128, 1, 101, (byte) 161, 3, (byte) 129, 1, 10 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        BurstListImpl elem = new BurstListImpl();
        int tag = ais.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(ais.getTagClass(), Tag.CLASS_UNIVERSAL);
        elem.decodeAll(ais);
        assertEquals((int) elem.getWarningPeriod(), 101);
        assertEquals((int) elem.getBursts().getBurstInterval(), 10);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        BurstImpl burst = new BurstImpl(null, 10, null, null, null);
        BurstListImpl elem = new BurstListImpl(101, burst);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }

    @Test(groups = { "functional.xml.serialize", "primitives" })
    public void testXMLSerialize() throws Exception {

        BurstImpl burst = new BurstImpl(null, 10, null, null, null);
        BurstListImpl original = new BurstListImpl(101, burst);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "burstList", BurstListImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        BurstListImpl copy = reader.read("burstList", BurstListImpl.class);

        assertEquals((int) copy.getWarningPeriod(), (int) original.getWarningPeriod());
        assertEquals((int) copy.getBursts().getBurstInterval(), (int) original.getBursts().getBurstInterval());
    }

}
