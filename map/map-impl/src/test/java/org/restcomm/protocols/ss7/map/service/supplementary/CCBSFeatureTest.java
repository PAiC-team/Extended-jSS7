
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeValue;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.TeleserviceCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.CCBSFeatureImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class CCBSFeatureTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 3, (byte) 128, 1, 1 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 20, (byte) 128, 1, 1, (byte) 129, 4, (byte) 145, 51, 51, 16, (byte) 130, 4, (byte) 145, 51, 51, 32, (byte) 163, 3, (byte) 131,
                1, 0 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        CCBSFeatureImpl impl = new CCBSFeatureImpl();
        impl.decodeAll(asn);

        assertEquals((int)impl.getCcbsIndex(), 1);
        assertNull(impl.getBSubscriberNumber());
        assertNull(impl.getBSubscriberSubaddress());
        assertNull(impl.getBasicServiceCode());


        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new CCBSFeatureImpl();
        impl.decodeAll(asn);

        assertEquals((int) impl.getCcbsIndex(), 1);
        assertEquals(impl.getBSubscriberNumber().getAddress(), "333301");
        assertEquals(impl.getBSubscriberSubaddress().getAddress(), "333302");
        assertEquals(impl.getBasicServiceCode().getTeleservice().getTeleserviceCodeValue(), TeleserviceCodeValue.allTeleservices);

    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        CCBSFeatureImpl impl = new CCBSFeatureImpl(1, null, null, null);
        // Integer ccbsIndex, ISDNAddressString bSubscriberNumber, ISDNAddressString bSubscriberSubaddress, BasicServiceCode basicServiceCode
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        ISDNAddressString bSubscriberNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "333301");
        ISDNAddressString bSubscriberSubaddress = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "333302");
        TeleserviceCode teleservice = new TeleserviceCodeImpl(TeleserviceCodeValue.allTeleservices);
        BasicServiceCode basicServiceCode = new BasicServiceCodeImpl(teleservice);
        impl = new CCBSFeatureImpl(1, bSubscriberNumber, bSubscriberSubaddress, basicServiceCode);
        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
