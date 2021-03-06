
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

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
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CUGIndexImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class CUGIndexTest {

    private byte[] getEncodedData() {
        return new byte[] { 2, 1, 100 };
    }

    @Test(groups = { "functional.decode", "service.subscriberManagement" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        CUGIndexImpl asc = new CUGIndexImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.INTEGER);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getData(), 100);
    }

    @Test(groups = { "functional.encode", "service.subscriberManagement" })
    public void testEncode() throws Exception {

        CUGIndexImpl asc = new CUGIndexImpl(100);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

    @Test(groups = { "functional.xml.serialize", "service.subscriberManagement" })
    public void testXMLSerialize() throws Exception {

        CUGIndexImpl original = new CUGIndexImpl(100);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "cugIndex", CUGIndexImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CUGIndexImpl copy = reader.read("cugIndex", CUGIndexImpl.class);

        assertEquals(original.getData(), copy.getData());
    }

}
