
package org.restcomm.protocols.ss7.map.primitives;

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
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddressAddressType;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class GSNAddressTest {

    private byte[] getEncodedData() {
        return new byte[] { 4, 5, 4, (byte) 192, (byte) 168, 4, 22 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 4, 17, 80, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4 };
    }

    private byte[] getData() {
        return new byte[] { (byte) 192, (byte) 168, 4, 22 };
    }

    private byte[] getData2() {
        return new byte[] { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        GSNAddressImpl pi = new GSNAddressImpl();
        
        pi.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(pi.getGSNAddressAddressType(), GSNAddressAddressType.IPv4);
        assertEquals(pi.getGSNAddressData(), getData());


        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        pi = new GSNAddressImpl();
        pi.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(pi.getGSNAddressAddressType(), GSNAddressAddressType.IPv6);
        assertEquals(pi.getGSNAddressData(), getData2());
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        GSNAddressImpl pi = new GSNAddressImpl(GSNAddressAddressType.IPv4, getData());
        AsnOutputStream asnOS = new AsnOutputStream();

        pi.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        pi = new GSNAddressImpl(GSNAddressAddressType.IPv6, getData2());
        asnOS = new AsnOutputStream();

        pi.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

    @Test(groups = { "functional.xml.serialize", "primitives" })
    public void testXMLSerialize() throws Exception {

        GSNAddressImpl original = new GSNAddressImpl(GSNAddressAddressType.IPv4, getData());

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "gsnAddress", GSNAddressImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        GSNAddressImpl copy = reader.read("gsnAddress", GSNAddressImpl.class);

        assertEquals(copy.getGSNAddressData(), original.getGSNAddressData());
        assertEquals(copy.getGSNAddressAddressType(), original.getGSNAddressAddressType());

    }

}
