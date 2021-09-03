
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.GPRSSubscriptionDataWithdrawImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class GPRSSubscriptionDataWithdrawTest {

    private byte[] getEncodedData() {
        return new byte[] { 5, 0 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 6, 2, 1, 1, 2, 1, 12 };
    }

    @Test
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        GPRSSubscriptionDataWithdrawImpl asc = new GPRSSubscriptionDataWithdrawImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.NULL);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertTrue(asn.isTagPrimitive());

        assertTrue(asc.getAllGPRSData());
        assertNull(asc.getContextIdList());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new GPRSSubscriptionDataWithdrawImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertFalse(asn.isTagPrimitive());

        assertFalse(asc.getAllGPRSData());
        assertEquals(asc.getContextIdList().size(), 2);
        assertEquals((int) asc.getContextIdList().get(0), 1);
        assertEquals((int) asc.getContextIdList().get(1), 12);
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        GPRSSubscriptionDataWithdrawImpl asc = new GPRSSubscriptionDataWithdrawImpl(true);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(12);
        asc = new GPRSSubscriptionDataWithdrawImpl(arr);

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
