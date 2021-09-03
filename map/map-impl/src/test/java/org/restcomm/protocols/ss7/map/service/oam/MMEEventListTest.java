
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.oam.MMEEventListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class MMEEventListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 2, (byte) 132 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        MMEEventListImpl asc = new MMEEventListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getUeInitiatedPDNconectivityRequest());
        assertFalse(asc.getServiceRequests());
        assertFalse(asc.getInitialAttachTrackingAreaUpdateDetach());
        assertFalse(asc.getUeInitiatedPDNdisconnection());
        assertFalse(asc.getBearerActivationModificationDeletion());
        assertTrue(asc.getHandover());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        MMEEventListImpl asc = new MMEEventListImpl(true, false, false, false, false, true);
//        boolean ueInitiatedPDNconectivityRequest, boolean serviceRequests, boolean initialAttachTrackingAreaUpdateDetach,
//        boolean ueInitiatedPDNdisconnection, boolean bearerActivationModificationDeletion, boolean handover

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
