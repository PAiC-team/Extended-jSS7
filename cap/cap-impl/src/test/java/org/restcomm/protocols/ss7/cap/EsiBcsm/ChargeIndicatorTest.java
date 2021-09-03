
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiBcsm.ChargeIndicatorImpl;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.ChargeIndicatorValue;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ChargeIndicatorTest {

    public byte[] getData1() {
        return new byte[] { 4, 1, 3 };
    }

    @Test(groups = { "functional.decode", "EsiBcsm" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        ChargeIndicatorImpl elem = new ChargeIndicatorImpl();
        int tag = ais.readTag();
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(ais.getTagClass(), Tag.CLASS_UNIVERSAL);

        elem.decodeAll(ais);
        assertEquals(elem.getChargeIndicatorValue(), ChargeIndicatorValue.spare);
    }

    @Test(groups = { "functional.encode", "EsiBcsm" })
    public void testEncode() throws Exception {

        ChargeIndicatorImpl elem = new ChargeIndicatorImpl(ChargeIndicatorValue.spare);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertEquals(aos.toByteArray(), this.getData1());
    }

    @Test(groups = { "functional.xml.serialize", "EsiBcsm" })
    public void testXMLSerializaion() throws Exception {
        ChargeIndicatorImpl original = new ChargeIndicatorImpl(ChargeIndicatorValue.spare);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "chargeIndicator", ChargeIndicatorImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        ChargeIndicatorImpl copy = reader.read("chargeIndicator", ChargeIndicatorImpl.class);

        assertEquals(copy.getChargeIndicatorValue(), original.getChargeIndicatorValue());
    }

}
