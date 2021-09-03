
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.SGSNEventListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class SGSNEventListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 4, (byte) 144 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        SGSNEventListImpl asc = new SGSNEventListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getPdpContext());
        assertFalse(asc.getMoMtSms());
        assertFalse(asc.getRauGprsAttachGprsDetach());
        assertTrue(asc.getMbmsContext());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        SGSNEventListImpl asc = new SGSNEventListImpl(true, false, false, true);
//        boolean pdpContext, boolean moMtSms, boolean rauGprsAttachGprsDetach, boolean mbmsContext

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
