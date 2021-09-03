
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.MGWInterfaceListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class MGWInterfaceListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 5, (byte) 160 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        MGWInterfaceListImpl asc = new MGWInterfaceListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getMc());
        assertFalse(asc.getNbUp());
        assertTrue(asc.getIuUp());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        MGWInterfaceListImpl asc = new MGWInterfaceListImpl(true, false, true);
//        boolean mc, boolean nbUp, boolean iuUp

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
