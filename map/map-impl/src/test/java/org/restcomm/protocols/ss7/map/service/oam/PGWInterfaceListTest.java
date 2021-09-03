
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.PGWInterfaceListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class PGWInterfaceListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 0, (byte) 173 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        PGWInterfaceListImpl asc = new PGWInterfaceListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getS2a());
        assertFalse(asc.getS2b());
        assertTrue(asc.getS2c());
        assertFalse(asc.getS5());
        assertTrue(asc.getS6b());
        assertTrue(asc.getGx());
        assertFalse(asc.getS8b());
        assertTrue(asc.getSgi());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        PGWInterfaceListImpl asc = new PGWInterfaceListImpl(true, false, true, false, true, true, false, true);
//        boolean s2a, boolean s2b, boolean s2c, boolean s5, boolean s6b,
//        boolean gx, boolean s8b, boolean sgi

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
