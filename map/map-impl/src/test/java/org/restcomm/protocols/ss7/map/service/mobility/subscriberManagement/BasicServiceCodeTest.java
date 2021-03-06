
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BearerServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.TeleserviceCodeImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class BasicServiceCodeTest {

    private byte[] getEncodedData1() {
        return new byte[] { -126, 1, 38 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { -125, 1, 33 };
    }

    @Test(groups = { "functional.decode", "service.subscriberManagement" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, BasicServiceCodeImpl._TAG_bearerService);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        BasicServiceCodeImpl impl = new BasicServiceCodeImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getBearerService().getBearerServiceCodeValue(), BearerServiceCodeValue.padAccessCA_9600bps);
        assertNull(impl.getTeleservice());

        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, BasicServiceCodeImpl._TAG_teleservice);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        impl = new BasicServiceCodeImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getTeleservice().getTeleserviceCodeValue(), TeleserviceCodeValue.shortMessageMT_PP);
        assertNull(impl.getBearerService());
    }

    @Test(groups = { "functional.encode", "service.subscriberManagement" })
    public void testEncode() throws Exception {

        BearerServiceCode bearerService = new BearerServiceCodeImpl(BearerServiceCodeValue.padAccessCA_9600bps);
        BasicServiceCodeImpl impl = new BasicServiceCodeImpl(bearerService);
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));

        TeleserviceCode teleservice = new TeleserviceCodeImpl(TeleserviceCodeValue.shortMessageMT_PP);
        impl = new BasicServiceCodeImpl(teleservice);
        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
