
package org.restcomm.protocols.ss7.map.service.pdpContextActivation;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddressAddressType;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.pdpContextActivation.SendRoutingInfoForGprsResponseImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class SendRoutingInfoForGprsResponseTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 7, (byte) 128, 5, 4, (byte) 192, (byte) 168, 4, 11 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 58, (byte) 128, 5, 4, (byte) 192, (byte) 168, 4, 11, (byte) 129, 5, 4, (byte) 192, (byte) 168, 4, 22, (byte) 130, 1, 6,
                (byte) 163, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25,
                26, (byte) 161, 3, 31, 32, 33 };
    }

    private byte[] getAddressData() {
        return new byte[] { (byte) 192, (byte) 168, 4, 11 };
    }

    private byte[] getAddressData2() {
        return new byte[] { (byte) 192, (byte) 168, 4, 22 };
    }

    @Test(groups = { "functional.decode", "service.pdpContextActivation" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        SendRoutingInfoForGprsResponseImpl impl = new SendRoutingInfoForGprsResponseImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSgsnAddress().getGSNAddressAddressType(), GSNAddressAddressType.IPv4);
        assertEquals(impl.getSgsnAddress().getGSNAddressData(), getAddressData());
        assertNull(impl.getGgsnAddress());
        assertNull(impl.getMobileNotReachableReason());
        assertNull(impl.getExtensionContainer());


        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new SendRoutingInfoForGprsResponseImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSgsnAddress().getGSNAddressAddressType(), GSNAddressAddressType.IPv4);
        assertEquals(impl.getSgsnAddress().getGSNAddressData(), getAddressData());
        assertEquals(impl.getGgsnAddress().getGSNAddressAddressType(), GSNAddressAddressType.IPv4);
        assertEquals(impl.getGgsnAddress().getGSNAddressData(), getAddressData2());
        assertEquals((int) impl.getMobileNotReachableReason(), 6);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(impl.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode", "service.pdpContextActivation" })
    public void testEncode() throws Exception {

        GSNAddress sgsnAddress = new GSNAddressImpl(GSNAddressAddressType.IPv4, getAddressData());
        SendRoutingInfoForGprsResponseImpl impl = new SendRoutingInfoForGprsResponseImpl(sgsnAddress, null, null, null);
        // GSNAddress sgsnAddress, GSNAddress ggsnAddress, Integer mobileNotReachableReason, MAPExtensionContainer extensionContainer

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        GSNAddress ggsnAddress = new GSNAddressImpl(GSNAddressAddressType.IPv4, getAddressData2());
        impl = new SendRoutingInfoForGprsResponseImpl(sgsnAddress, ggsnAddress, 6, MAPExtensionContainerTest.GetTestExtensionContainer());

        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
