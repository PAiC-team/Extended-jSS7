
package org.restcomm.protocols.ss7.map.service.sms;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.sms.IpSmGwGuidanceImpl;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author eva ogallar
 * @author sergey vetyutnev
 *
 */
public class IpSmGwGuidanceTest {

    private byte[] getEncodedData() {
        return new byte[] {32,6,2,1,30,2,1,40};
    }

    private byte[] getEncodedDataFull() {
        return new byte[] {32,47,2,1,30,2,1,40,48,39,-96,32,48,10,6,3,42,3,4,11,12,13,14,15,
                48,5,6,3,42,3,6,48,11,6,3,42,3,5,21,22,23,24,25,26,-95,3,31,32,33};
    }

    @Test(groups = { "functional.decode", "service.sms" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        IpSmGwGuidanceImpl ipSmGwGuidance = new IpSmGwGuidanceImpl();
        ipSmGwGuidance.decodeAll(asn);

        assertEquals(tag, 0);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(ipSmGwGuidance.getMinimumDeliveryTimeValue(), 30);
        assertEquals(ipSmGwGuidance.getRecommendedDeliveryTimeValue(), 40);

        rawData = getEncodedDataFull();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        ipSmGwGuidance = new IpSmGwGuidanceImpl();
        ipSmGwGuidance.decodeAll(asn);

        assertEquals(tag, 0);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(ipSmGwGuidance.getMinimumDeliveryTimeValue(), 30);
        assertEquals(ipSmGwGuidance.getRecommendedDeliveryTimeValue(), 40);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(ipSmGwGuidance.getExtensionContainer()));
    }

    @Test(groups = { "functional.encode", "service.sms" })
    public void testEncode() throws Exception {

        IpSmGwGuidanceImpl liw = new IpSmGwGuidanceImpl(30, 40, null);

        AsnOutputStream asnOS = new AsnOutputStream();
        liw.encodeAll(asnOS, Tag.CLASS_UNIVERSAL, 0);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

        liw = new IpSmGwGuidanceImpl(30, 40, MAPExtensionContainerTest.GetTestExtensionContainer());

        asnOS.reset();
        liw.encodeAll(asnOS, Tag.CLASS_UNIVERSAL, 0);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedDataFull();
        assertTrue(Arrays.equals(rawData, encodedData));

    }
}
