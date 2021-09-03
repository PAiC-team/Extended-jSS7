
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeValue;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CliRestrictionOption;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSSubscriptionOption;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.TeleserviceCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSDataImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSStatusImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSSubscriptionOptionImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class SSDataTest {

    private byte[] getEncodedData1() {
        return new byte[] { 48, 20, 4, 1, 18, (byte) 132, 1, 10, (byte) 130, 1, 2, 48, 3, (byte) 131, 1, 99, 2, 1, 3, (byte) 133, 1, 6 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        SSDataImpl impl = new SSDataImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.clir);

        assertTrue(impl.getSsStatus().getQBit());
        assertFalse(impl.getSsStatus().getPBit());
        assertTrue(impl.getSsStatus().getRBit());
        assertFalse(impl.getSsStatus().getABit());
        assertEquals(impl.getSsSubscriptionOption().getCliRestrictionOption(), CliRestrictionOption.temporaryDefaultAllowed);

        assertEquals(impl.getBasicServiceGroupList().size(), 1);
        assertEquals(impl.getBasicServiceGroupList().get(0).getTeleservice().getTeleserviceCodeValue(), TeleserviceCodeValue.facsimileGroup4);

        assertEquals(impl.getDefaultPriority(), EMLPPPriority.priorityLevel3);
        assertEquals((int) impl.getNbrUser(), 6);

    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        SSCode ssCode = new SSCodeImpl(SupplementaryCodeValue.clir);
        SSStatus ssStatus = new SSStatusImpl(true, false, true, false);
        SSSubscriptionOption ssSubscriptionOption = new SSSubscriptionOptionImpl(CliRestrictionOption.temporaryDefaultAllowed);

        ArrayList<BasicServiceCode> basicServiceGroupList = new ArrayList<BasicServiceCode>();
        TeleserviceCodeImpl teleserviceCode = new TeleserviceCodeImpl(TeleserviceCodeValue.facsimileGroup4);
        BasicServiceCode bsc = new BasicServiceCodeImpl(teleserviceCode);
        basicServiceGroupList.add(bsc);

        SSDataImpl impl = new SSDataImpl(ssCode, ssStatus, ssSubscriptionOption, basicServiceGroupList, EMLPPPriority.priorityLevel3, 6);
//        SSCode ssCode, SSStatus ssStatus, SSSubscriptionOption ssSubscriptionOption, ArrayList<BasicServiceCode> basicServiceGroupList,
//        EMLPPPriority defaultPriority, Integer nbrUser

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
