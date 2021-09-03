
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeValue;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.TeleserviceCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.ForwardingFeatureImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.ForwardingInfoImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ForwardingInfoTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 7, 48, 5, 48, 3, (byte) 131, 1, (byte) 144 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 10, 4, 1, 33, 48, 5, 48, 3, (byte) 131, 1, (byte) 144 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        ForwardingInfoImpl impl = new ForwardingInfoImpl();
        impl.decodeAll(asn);

        assertNull(impl.getSsCode());

        assertEquals(impl.getForwardingFeatureList().size(), 1);
        assertEquals(impl.getForwardingFeatureList().get(0).getBasicService().getTeleservice().getTeleserviceCodeValue(),
                TeleserviceCodeValue.allVoiceGroupCallServices);


        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new ForwardingInfoImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.cfu);

        assertEquals(impl.getForwardingFeatureList().size(), 1);
        assertEquals(impl.getForwardingFeatureList().get(0).getBasicService().getTeleservice().getTeleserviceCodeValue(),
                TeleserviceCodeValue.allVoiceGroupCallServices);
    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        TeleserviceCodeImpl teleservice = new TeleserviceCodeImpl(TeleserviceCodeValue.allVoiceGroupCallServices);
        BasicServiceCodeImpl basicService = new BasicServiceCodeImpl(teleservice);
        ArrayList<ForwardingFeature> forwardingFeatureList = new ArrayList<ForwardingFeature>();
        ForwardingFeatureImpl forwardingFeature = new ForwardingFeatureImpl(basicService, null, null, null, null, null, null);
        forwardingFeatureList.add(forwardingFeature);
        ForwardingInfoImpl impl = new ForwardingInfoImpl(null, forwardingFeatureList);
        //        (SSCode ssCode, ArrayList<ForwardingFeature> forwardingFeatureList
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        SSCode ssCode = new SSCodeImpl(SupplementaryCodeValue.cfu);
        impl = new ForwardingInfoImpl(ssCode, forwardingFeatureList);
        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
