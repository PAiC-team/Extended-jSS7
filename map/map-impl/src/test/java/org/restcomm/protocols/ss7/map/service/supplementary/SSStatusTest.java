
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.supplementary.SSStatusImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SSStatusTest {

    private byte[] getEncodedData1() {
        return new byte[] { 4, 1, 10 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 4, 1, 5 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        SSStatusImpl impl = new SSStatusImpl();
        impl.decodeAll(asn);

        assertTrue(impl.getQBit());
        assertFalse(impl.getPBit());
        assertTrue(impl.getRBit());
        assertFalse(impl.getABit());

        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new SSStatusImpl();
        impl.decodeAll(asn);

        assertFalse(impl.getQBit());
        assertTrue(impl.getPBit());
        assertFalse(impl.getRBit());
        assertTrue(impl.getABit());
    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        SSStatusImpl impl = new SSStatusImpl(true, false, true, false);
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));

        impl = new SSStatusImpl(false, true, false, true);
        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }
}
