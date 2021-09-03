
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.OfferedCamel4CSIsImpl;
import org.testng.annotations.Test;

public class OfferedCamel4CSIsTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 1, (byte) 148 };
    }

//    private byte[] getEncodedData2() {
//        // short form - without the first bit string bit
//        return new byte[] { 3, 1, (byte) 148 };
//    }

    @Test(groups = { "functional.decode", "service.mobility.subscriberManagement" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        OfferedCamel4CSIsImpl imp = new OfferedCamel4CSIsImpl();
        imp.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(imp.getOCsi());
        assertFalse(imp.getDCsi());
        assertFalse(imp.getVtCsi());
        assertTrue(imp.getTCsi());
        assertFalse(imp.getMtSmsCsi());
        assertTrue(imp.getMgCsi());
        assertFalse(imp.getPsiEnhancements());


//        rawData = getEncodedData2();
//        asn = new AsnInputStream(rawData);
//
//        tag = asn.readTag();
//        imp = new OfferedCamel4CSIsImpl();
//        imp.decodeAll(asn);
//
//        assertEquals(tag, Tag.STRING_BIT);
//        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
//
//        assertTrue(imp.getOCsi());
//        assertFalse(imp.getDCsi());
//        assertFalse(imp.getVtCsi());
//        assertTrue(imp.getTCsi());
//        assertFalse(imp.getMtSmsCsi());
//        assertTrue(imp.getMgCsi());
//        assertFalse(imp.getPsiEnhancements());
    }

    private void assertTrue(boolean initiateCallAttempt) {
        // TODO Auto-generated method stub
        
    }

    @Test(groups = { "functional.encode", "service.mobility.subscriberManagement" })
    public void testEncode() throws Exception {

        OfferedCamel4CSIsImpl imp = new OfferedCamel4CSIsImpl(true, false, false, true, false, true, false);
//        boolean oCsi, boolean dCsi, boolean vtCsi, boolean tCsi, boolean mtSMSCsi, boolean mgCsi,
//        boolean psiEnhancements

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);
        assertTrue(Arrays.equals(getEncodedData(), asnOS.toByteArray()));
    }

}
