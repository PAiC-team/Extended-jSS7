
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
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.VariablePartPriceImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class VariablePartPriceTest {

    public byte[] getData1() {
        return new byte[] { (byte) 132, 4, 0, 0, 32, 69 };
    }

    public byte[] getData2() {
        return new byte[] { (byte) 132, 4, (byte) 135, (byte) 152, (byte) 137, (byte) 151 };
    }

    public byte[] getData3() {
        return new byte[] {};
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        VariablePartPriceImpl elem = new VariablePartPriceImpl();
        int tag = ais.readTag();
        assertEquals(tag, 4);
        elem.decodeAll(ais);
        assertEquals(elem.getPriceIntegerPart(), 2);
        assertEquals(elem.getPriceHundredthPart(), 54);

        data = this.getData2();
        ais = new AsnInputStream(data);
        elem = new VariablePartPriceImpl();
        tag = ais.readTag();
        assertEquals(tag, 4);
        elem.decodeAll(ais);
        assertEquals(elem.getPriceIntegerPart(), 788998);
        assertEquals(elem.getPriceHundredthPart(), 79);
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        VariablePartPriceImpl elem = new VariablePartPriceImpl(2, 54);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 4);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));

        elem = new VariablePartPriceImpl(99788998, 79);
        aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 4);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData2()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall" })
    public void testXMLSerialize() throws Exception {

        VariablePartPriceImpl original = new VariablePartPriceImpl(345.2);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "variablePartPrice", VariablePartPriceImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        VariablePartPriceImpl copy = reader.read("variablePartPrice", VariablePartPriceImpl.class);

        assertEquals(copy.getPriceIntegerPart(), original.getPriceIntegerPart());
        assertEquals(copy.getPriceHundredthPart(), original.getPriceHundredthPart());
    }
}
