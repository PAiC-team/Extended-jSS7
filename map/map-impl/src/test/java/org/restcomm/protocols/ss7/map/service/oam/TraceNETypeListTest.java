
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.TraceNETypeListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceNETypeListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 3, 6, (byte) 219, 64 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        TraceNETypeListImpl asc = new TraceNETypeListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getMscS());
        assertTrue(asc.getMgw());
        assertFalse(asc.getSgsn());
        assertTrue(asc.getGgsn());
        assertTrue(asc.getRnc());
        assertFalse(asc.getBmSc());
        assertTrue(asc.getMme());
        assertTrue(asc.getSgw());
        assertFalse(asc.getPgw());
        assertTrue(asc.getEnb());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        TraceNETypeListImpl asc = new TraceNETypeListImpl(true, true, false, true, true, false, true, true, false, true);
//        boolean mscS, boolean mgw, boolean sgsn, boolean ggsn, boolean rnc, 
//        boolean bmSc, boolean mme, boolean sgw, boolean pgw, boolean enb

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
