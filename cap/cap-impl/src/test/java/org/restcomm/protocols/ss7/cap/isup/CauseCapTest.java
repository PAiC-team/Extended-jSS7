
package org.restcomm.protocols.ss7.cap.isup;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CauseIndicatorsImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public class CauseCapTest {

    public byte[] getData() {
        return new byte[] { (byte) 128, 2, (byte) 132, (byte) 144 };
    }

    public byte[] getIntData() {
        return new byte[] { (byte) 132, (byte) 144 };
    }

    @Test(groups = { "functional.decode", "isup" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        CauseCapImpl elem = new CauseCapImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertTrue(Arrays.equals(elem.getData(), this.getIntData()));
        CauseIndicators ci = elem.getCauseIndicators();
        assertEquals(ci.getCodingStandard(), 0);
        assertEquals(ci.getLocation(), 4);
        assertEquals(ci.getCauseValue(), 16);
        assertNull(ci.getDiagnostics());
    }

    @Test(groups = { "functional.xml.serialize", "isup" })
    public void testXMLSerializaion() throws Exception {

        CauseCapImpl original = new CauseCapImpl(this.getIntData());

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.

        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "causeCap", CauseCapImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CauseCapImpl copy = reader.read("causeCap", CauseCapImpl.class);

        assertEquals(copy.getCauseIndicators().getCauseValue(), original.getCauseIndicators().getCauseValue());
    }

    @Test(groups = { "functional.encode", "isup" })
    public void testEncode() throws Exception {

    }

    @Test(groups = { "functional.xml.serialize", "isup" })
    public void testXMLSerialize() throws Exception {

        CauseIndicatorsImpl original0 = new CauseIndicatorsImpl(CauseIndicators._CODING_STANDARD_NATIONAL,
                CauseIndicators._LOCATION_PRIVATE_NSRU, 1, CauseIndicators._CV_CALL_REJECTED, null);

        CauseCapImpl original = new CauseCapImpl(original0);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "causeCap", CauseCapImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CauseCapImpl copy = reader.read("causeCap", CauseCapImpl.class);

        assertEquals(copy.getCauseIndicators().getCodingStandard(), original.getCauseIndicators().getCodingStandard());
        assertEquals(copy.getCauseIndicators().getLocation(), original.getCauseIndicators().getLocation());
        assertEquals(copy.getCauseIndicators().getRecommendation(), original.getCauseIndicators().getRecommendation());
        assertEquals(copy.getCauseIndicators().getCauseValue(), original.getCauseIndicators().getCauseValue());
    }
}
