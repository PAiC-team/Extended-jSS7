
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BearerServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.CallBarringFeatureImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSStatusImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class CallBarringFeatureTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 6, (byte) 130, 1, 38, (byte) 132, 1, 13 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        CallBarringFeatureImpl impl = new CallBarringFeatureImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getBasicService().getBearerService().getBearerServiceCodeValue(), BearerServiceCodeValue.padAccessCA_9600bps);
        SSStatus ssStatus = impl.getSsStatus();
        assertTrue(ssStatus.getQBit());
        assertTrue(ssStatus.getPBit());
        assertFalse(ssStatus.getRBit());
        assertTrue(ssStatus.getABit());
    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        BearerServiceCodeImpl bearerService = new BearerServiceCodeImpl(BearerServiceCodeValue.padAccessCA_9600bps);
        BasicServiceCodeImpl basicServiceCode = new BasicServiceCodeImpl(bearerService);
        SSStatusImpl ssStatus = new SSStatusImpl(true, true, false, true);

        CallBarringFeatureImpl impl = new CallBarringFeatureImpl(basicServiceCode, ssStatus);
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
