
package org.restcomm.protocols.ss7.map.service.callhandling;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.service.callhandling.CallReferenceNumberImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallReferenceNumberTest {

    public byte[] getData() {
        return new byte[] { 4, 5, 19, -6, 61, 61, -22 };
    };

    public byte[] getDataVal() {
        return new byte[] { 19, -6, 61, 61, -22 };
    };

    @Test(groups = { "functional.decode", "service.callhandling" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        CallReferenceNumberImpl prim = new CallReferenceNumberImpl();
        prim.decodeAll(asn);

        assertNotNull(prim.getData());
        assertTrue(Arrays.equals(getDataVal(), prim.getData()));

    }

    @Test(groups = { "functional.decode", "service.callhandling" })
    public void testEncode() throws Exception {

        CallReferenceNumberImpl prim = new CallReferenceNumberImpl(getDataVal());

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

    @Test(groups = { "functional.xml.serialize", "service.callhandling" })
    public void testXMLSerialize() throws Exception {

        CallReferenceNumberImpl original = new CallReferenceNumberImpl(getDataVal());

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "callReferenceNumber", CallReferenceNumberImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CallReferenceNumberImpl copy = reader.read("callReferenceNumber", CallReferenceNumberImpl.class);

        assertEquals(copy.getData(), original.getData());

    }
}
