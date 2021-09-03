
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAInformationWithdrawImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class LSAInformationWithdrawTest {

    private byte[] getEncodedData() {
        return new byte[] { 5, 0 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 5, 4, 3, 1, 2, 3 };
    }

    private byte[] getLsaIdData() {
        return new byte[] { 1, 2, 3 };
    }

    @Test
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        LSAInformationWithdrawImpl asc = new LSAInformationWithdrawImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.NULL);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertTrue(asn.isTagPrimitive());

        assertTrue(asc.getAllLSAData());
        assertNull(asc.getLSAIdentityList());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new LSAInformationWithdrawImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertFalse(asn.isTagPrimitive());

        assertFalse(asc.getAllLSAData());
        assertEquals(asc.getLSAIdentityList().size(), 1);
        assertEquals(asc.getLSAIdentityList().get(0).getData(), getLsaIdData());
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        LSAInformationWithdrawImpl asc = new LSAInformationWithdrawImpl(true);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        ArrayList<LSAIdentity> arr = new ArrayList<LSAIdentity>();
        LSAIdentity lsaId = new LSAIdentityImpl(getLsaIdData());
        arr.add(lsaId);
        asc = new LSAInformationWithdrawImpl(arr);

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
