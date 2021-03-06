
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.AlertingPatternCapImpl;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingCategory;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingLevel;
import org.restcomm.protocols.ss7.map.primitives.AlertingPatternImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AlertingPatternCapTest {

    public byte[] getData1() {
        return new byte[] { 4, 3, 0, 0, 1 };
    }

    public byte[] getData2() {
        return new byte[] { 4, 3, 0, 0, 5 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        AlertingPatternCapImpl elem = new AlertingPatternCapImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertEquals(elem.getAlertingPattern().getAlertingLevel(), AlertingLevel.Level1);

        data = this.getData2();
        ais = new AsnInputStream(data);
        elem = new AlertingPatternCapImpl();
        tag = ais.readTag();
        elem.decodeAll(ais);
        assertEquals(elem.getAlertingPattern().getAlertingCategory(), AlertingCategory.Category2);
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        AlertingPatternImpl alertingPattern = new AlertingPatternImpl(AlertingLevel.Level1);
        AlertingPatternCapImpl elem = new AlertingPatternCapImpl(alertingPattern);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));

        alertingPattern = new AlertingPatternImpl(AlertingCategory.Category2);
        elem = new AlertingPatternCapImpl(alertingPattern);
        aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData2()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall.primitive" })
    public void testXMLSerialize() throws Exception {

        AlertingPatternImpl alertingPattern = new AlertingPatternImpl(AlertingLevel.Level1);
        AlertingPatternCapImpl original = new AlertingPatternCapImpl(alertingPattern);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "alertingPatternCap", AlertingPatternCapImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        AlertingPatternCapImpl copy = reader.read("alertingPatternCap", AlertingPatternCapImpl.class);

        assertEquals(copy.getData(), original.getData());

    }
}
