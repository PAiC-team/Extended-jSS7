
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberStateChoice;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.ProvideSubscriberInfoResponseImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.SubscriberInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.SubscriberStateImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ProvideSubscriberInfoResponseTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 6, 48, 4, (byte) 161, 2, (byte) 129, 0 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 47, 48, 4, (byte) 161, 2, (byte) 129, 0, 48, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42,
                3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, (byte) 161, 3, 31, 32, 33 };
    }

    @Test(groups = { "functional.decode", "service.mobility.subscriberInformation" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ProvideSubscriberInfoResponseImpl asc = new ProvideSubscriberInfoResponseImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getSubscriberInfo().getSubscriberState().getSubscriberStateChoice(), SubscriberStateChoice.camelBusy);
        assertNull(asc.getExtensionContainer());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new ProvideSubscriberInfoResponseImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getSubscriberInfo().getSubscriberState().getSubscriberStateChoice(), SubscriberStateChoice.camelBusy);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(asc.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode", "service.mobility.subscriberInformation" })
    public void testEncode() throws Exception {

        SubscriberStateImpl subscriberState = new SubscriberStateImpl(SubscriberStateChoice.camelBusy, null);
        SubscriberInfoImpl subscriberInfo = new SubscriberInfoImpl(null, subscriberState, null, null, null, null, null, null, null);
        ProvideSubscriberInfoResponseImpl asc = new ProvideSubscriberInfoResponseImpl(subscriberInfo, null);
        // SubscriberInfo subscriberInfo, MAPExtensionContainer extensionContainer

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        asc = new ProvideSubscriberInfoResponseImpl(subscriberInfo, MAPExtensionContainerTest.GetTestExtensionContainer());

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
