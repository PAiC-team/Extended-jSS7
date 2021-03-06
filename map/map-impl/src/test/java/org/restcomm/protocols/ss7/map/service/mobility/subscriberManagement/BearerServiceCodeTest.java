
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BearerServiceCodeImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class BearerServiceCodeTest {

    private byte[] getEncodedData1() {
        return new byte[] { (byte) 130, 1, 38 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();
        AsnInputStream asn = new AsnInputStream(rawData);
        int tag = asn.readTag();
        BearerServiceCodeImpl impl = new BearerServiceCodeImpl();
        impl.decodeAll(asn);
        assertEquals(impl.getData(), 38);
        assertEquals(impl.getBearerServiceCodeValue(), BearerServiceCodeValue.padAccessCA_9600bps);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        BearerServiceCodeImpl impl = new BearerServiceCodeImpl(38);
        AsnOutputStream asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, 2);
        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));

        impl = new BearerServiceCodeImpl(BearerServiceCodeValue.padAccessCA_9600bps);
        asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, 2);
        encodedData = asnOS.toByteArray();
        rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));
    }
}
