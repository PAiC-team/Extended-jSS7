
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BearerServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSForBSCodeImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class SSForBSCodeTest {

    private byte[] getEncodedData1() {
        return new byte[] { 48, 3, 4, 1, 18 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 8, 4, 1, 18, -126, 1, 35, -124, 0 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        SSForBSCodeImpl impl = new SSForBSCodeImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.clir);
        assertNull(impl.getBasicService());
        assertFalse(impl.getLongFtnSupported());


        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new SSForBSCodeImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.clir);
        assertEquals(impl.getBasicService().getBearerService().getBearerServiceCodeValue(), BearerServiceCodeValue.padAccessCA_1200_75bps);
        assertTrue(impl.getLongFtnSupported());

    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        SSCode ssCode = new SSCodeImpl(SupplementaryCodeValue.clir);

        SSForBSCodeImpl impl = new SSForBSCodeImpl(ssCode, null, false);
//        SSCode ssCode, BasicServiceCode basicService, boolean longFtnSupported

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));


        BearerServiceCode bearerService = new BearerServiceCodeImpl(BearerServiceCodeValue.padAccessCA_1200_75bps);
        BasicServiceCode basicService = new BasicServiceCodeImpl(bearerService);

        impl = new SSForBSCodeImpl(ssCode, basicService, true);
//        SSCode ssCode, BasicServiceCode basicService, boolean longFtnSupported

        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
