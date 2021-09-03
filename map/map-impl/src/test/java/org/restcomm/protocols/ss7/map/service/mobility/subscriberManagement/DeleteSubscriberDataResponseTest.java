
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.RegionalSubscriptionResponse;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.DeleteSubscriberDataResponseImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class DeleteSubscriberDataResponseTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 3, (byte) 128, 1, 2 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 44, (byte) 128, 1, 2, 48, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3,
                42, 3, 5, 21, 22, 23, 24, 25, 26, (byte) 161, 3, 31, 32, 33 };
    }

    @Test(groups = { "functional.decode", "service.mobility.subscriberManagement" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        DeleteSubscriberDataResponseImpl asc = new DeleteSubscriberDataResponseImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getRegionalSubscriptionResponse(), RegionalSubscriptionResponse.zoneCodesConflict);
        assertNull(asc.getExtensionContainer());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new DeleteSubscriberDataResponseImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getRegionalSubscriptionResponse(), RegionalSubscriptionResponse.zoneCodesConflict);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(asc.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode", "service.mobility.subscriberManagement" })
    public void testEncode() throws Exception {

        DeleteSubscriberDataResponseImpl asc = new DeleteSubscriberDataResponseImpl(RegionalSubscriptionResponse.zoneCodesConflict, null);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        asc = new DeleteSubscriberDataResponseImpl(RegionalSubscriptionResponse.zoneCodesConflict, MAPExtensionContainerTest.GetTestExtensionContainer());

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
