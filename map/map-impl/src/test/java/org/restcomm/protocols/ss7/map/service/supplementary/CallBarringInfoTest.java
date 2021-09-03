
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CallBarringFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.service.supplementary.CallBarringFeatureImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.CallBarringInfoImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSStatusImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class CallBarringInfoTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 7, 48, 5, 48, 3, (byte) 132, 1, 14 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 10, 4, 1, 33, 48, 5, 48, 3, (byte) 132, 1, 14 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        CallBarringInfoImpl impl = new CallBarringInfoImpl();
        impl.decodeAll(asn);

        assertNull(impl.getSsCode());

        assertEquals(impl.getCallBarringFeatureList().size(), 1);
        assertFalse(impl.getCallBarringFeatureList().get(0).getSsStatus().getABit());
        assertTrue(impl.getCallBarringFeatureList().get(0).getSsStatus().getPBit());

        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new CallBarringInfoImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.cfu);

        assertEquals(impl.getCallBarringFeatureList().size(), 1);
        assertFalse(impl.getCallBarringFeatureList().get(0).getSsStatus().getABit());
        assertTrue(impl.getCallBarringFeatureList().get(0).getSsStatus().getPBit());
    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        ArrayList<CallBarringFeature> forwardingFeatureList = new ArrayList<CallBarringFeature>();
        SSStatus ssStatus = new SSStatusImpl(true, true, true, false);
        CallBarringFeatureImpl callBarringFeature = new CallBarringFeatureImpl(null, ssStatus);
        forwardingFeatureList.add(callBarringFeature);
        CallBarringInfoImpl impl = new CallBarringInfoImpl(null, forwardingFeatureList);
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        SSCode ssCode = new SSCodeImpl(SupplementaryCodeValue.cfu);
        impl = new CallBarringInfoImpl(ssCode, forwardingFeatureList);
        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
