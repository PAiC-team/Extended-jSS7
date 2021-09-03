
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingCategory;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingLevel;
import org.restcomm.protocols.ss7.map.primitives.AlertingPatternImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author amit bhayani
 *
 */
public class AlertingPatternTest {
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeTest
    public void setUp() {
    }

    @AfterTest
    public void tearDown() {
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = new byte[] { (byte) 0x04, 0x01, 0x07 };

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        AlertingPatternImpl addNum = new AlertingPatternImpl();
        addNum.decodeAll(asn);
        assertNull(addNum.getAlertingLevel());
        assertNotNull(addNum.getAlertingCategory());

        assertEquals(addNum.getAlertingCategory(), AlertingCategory.Category4);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        byte[] data = new byte[] { (byte) 0x04, 0x01, 0x07 };

        AlertingPatternImpl addNum = new AlertingPatternImpl(AlertingCategory.Category4);

        AsnOutputStream asnOS = new AsnOutputStream();
        addNum.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));
    }

    @Test(groups = { "functional.serialize", "primitives" })
    public void testSerialization() throws Exception {
        AlertingPatternImpl original = new AlertingPatternImpl(AlertingCategory.Category4);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "alertingPattern", AlertingPatternImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        AlertingPatternImpl copy = reader.read("alertingPattern", AlertingPatternImpl.class);

        // test result
        assertEquals(copy.getAlertingCategory(), original.getAlertingCategory());
        assertEquals(copy, original);
        assertNull(copy.getAlertingLevel());

        original = new AlertingPatternImpl(AlertingLevel.Level1);

        // Writes the area to a file.
        baos = new ByteArrayOutputStream();
        writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "alertingPattern", AlertingPatternImpl.class);
        writer.close();

        rawData = baos.toByteArray();
        serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        bais = new ByteArrayInputStream(rawData);
        reader = XMLObjectReader.newInstance(bais);
        copy = reader.read("alertingPattern", AlertingPatternImpl.class);

        // test result
        assertEquals(copy.getAlertingLevel(), original.getAlertingLevel());
        assertEquals(copy, original);
        assertNull(copy.getAlertingCategory());
    }
}
