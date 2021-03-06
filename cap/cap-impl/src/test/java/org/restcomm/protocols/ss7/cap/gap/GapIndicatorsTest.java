
package org.restcomm.protocols.ss7.cap.gap;

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
import org.restcomm.protocols.ss7.cap.gap.GapIndicatorsImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class GapIndicatorsTest {

    public byte[] getData() {
        return new byte[] { 48, 6, (byte) 128, 1, 100, (byte) 129, 1, (byte) 255 };
    }

    @Test(groups = { "functional.decode", "gap" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        GapIndicatorsImpl elem = new GapIndicatorsImpl();
        int tag = ais.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(ais.getTagClass(), Tag.CLASS_UNIVERSAL);
        elem.decodeAll(ais);

        assertEquals(elem.getDuration(), 100);
        assertEquals(elem.getGapInterval(), -1);
    }

    @Test(groups = { "functional.encode", "gap" })
    public void testEncode() throws Exception {
        GapIndicatorsImpl elem = new GapIndicatorsImpl(100, -1);

        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);

        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall" })
    public void testXMLSerialize() throws Exception {

        GapIndicatorsImpl original = new GapIndicatorsImpl(100, -1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "gapIndicators", GapIndicatorsImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);

        GapIndicatorsImpl copy = reader.read("gapIndicators", GapIndicatorsImpl.class);

        assertTrue(isEqual(original, copy));
    }

    private boolean isEqual(GapIndicatorsImpl o1, GapIndicatorsImpl o2) {
        if (o1 == o2)
            return true;
        if (o1 == null && o2 != null || o1 != null && o2 == null)
            return false;
        if (o1 == null && o2 == null)
            return true;
        if (!o1.toString().equals(o2.toString()))
            return false;
        return true;
    }

}
