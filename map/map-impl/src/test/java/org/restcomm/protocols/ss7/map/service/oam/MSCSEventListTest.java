
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.MSCSEventListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class MSCSEventListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 3, 72 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        MSCSEventListImpl asc = new MSCSEventListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertFalse(asc.getMoMtCall());
        assertTrue(asc.getMoMtSms());
        assertFalse(asc.getLuImsiAttachImsiDetach());
        assertFalse(asc.getHandovers());
        assertTrue(asc.getSs());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        MSCSEventListImpl asc = new MSCSEventListImpl(false, true, false, false, true);
//        boolean moMtCall, boolean moMtSms, boolean luImsiAttachImsiDetach, boolean handovers, boolean ss

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
