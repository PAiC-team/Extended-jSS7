
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.MSCSInterfaceListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class MSCSInterfaceListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 3, 6, (byte) 219, 64 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        MSCSInterfaceListImpl asc = new MSCSInterfaceListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getA());
        assertTrue(asc.getIu());
        assertFalse(asc.getMc());
        assertTrue(asc.getMapG());
        assertTrue(asc.getMapB());
        assertFalse(asc.getMapE());
        assertTrue(asc.getMapF());
        assertTrue(asc.getCap());
        assertFalse(asc.getMapD());
        assertTrue(asc.getMapC());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        MSCSInterfaceListImpl asc = new MSCSInterfaceListImpl(true, true, false, true, true, false, true, true, false, true);
//        boolean a, boolean iu, boolean mc, boolean mapG, boolean mapB,
//        boolean mapE, boolean mapF, boolean cap, boolean mapD, boolean mapC

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
