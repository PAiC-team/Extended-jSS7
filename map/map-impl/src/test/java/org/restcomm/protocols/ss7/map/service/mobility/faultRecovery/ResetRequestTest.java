
package org.restcomm.protocols.ss7.map.service.mobility.faultRecovery;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.service.mobility.faultRecovery.ResetRequestImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ResetRequestTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 9, 10, 1, 1, 4, 4, (byte) 145, 33, 67, (byte) 245 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 14, 4, 4, (byte) 145, 33, 67, (byte) 245, 48, 6, 4, 4, 33, 67, 0, (byte) 241 };
    }

    @Test(groups = { "functional.decode", "service.mobility.faultRecovery" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ResetRequestImpl prim = new ResetRequestImpl(1);
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getMapProtocolVersion(), 1);
        assertEquals(prim.getNetworkResource(), NetworkResource.hlr);
        assertEquals(prim.getHlrNumber().getAddress(), "12345");
        assertNull(prim.getHlrList());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        prim = new ResetRequestImpl(2);
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getMapProtocolVersion(), 2);
        assertNull(prim.getNetworkResource());
        assertEquals(prim.getHlrNumber().getAddress(), "12345");
        assertEquals(prim.getHlrList().size(), 1);
        assertEquals(prim.getHlrList().get(0).getData(), "1234001");

    }

    @Test(groups = { "functional.encode", "service.mobility.faultRecovery" })
    public void testEncode() throws Exception {

        ISDNAddressStringImpl hlrNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "12345");
        ResetRequestImpl prim = new ResetRequestImpl(NetworkResource.hlr, hlrNumber, null, 1);

        AsnOutputStream asnOS = new AsnOutputStream();
        prim.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        ArrayList<IMSI> hlrList = new ArrayList<IMSI>();
        IMSIImpl imsi = new IMSIImpl("1234001");
        hlrList.add(imsi);
        prim = new ResetRequestImpl(null, hlrNumber, hlrList, 2);

        asnOS = new AsnOutputStream();
        prim.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
