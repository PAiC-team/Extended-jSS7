
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.SGSNInterfaceListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class SGSNInterfaceListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 3, 5, (byte) 182, (byte) 160 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        SGSNInterfaceListImpl asc = new SGSNInterfaceListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getGb());
        assertFalse(asc.getIu());
        assertTrue(asc.getGn());
        assertTrue(asc.getMapGr());
        assertFalse(asc.getMapGd());
        assertTrue(asc.getMapGf());
        assertTrue(asc.getGs());
        assertFalse(asc.getGe());
        assertTrue(asc.getS3());
        assertFalse(asc.getS4());
        assertTrue(asc.getS6d());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        SGSNInterfaceListImpl asc = new SGSNInterfaceListImpl(true, false, true, true, false, true, true, false, true, false, true);
//        boolean gb, boolean iu, boolean gn, boolean mapGr, boolean mapGd,
//        boolean mapGf, boolean gs, boolean ge, boolean s3, boolean s4,
//        boolean s6d

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
