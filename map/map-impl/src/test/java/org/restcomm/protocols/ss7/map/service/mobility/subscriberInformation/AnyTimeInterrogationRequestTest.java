package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.AnyTimeInterrogationRequestImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RequestedInfoImpl;
import org.testng.annotations.Test;

/**
 * @author abhayani
 *
 */
public class AnyTimeInterrogationRequestTest {
    // Real Trace
    byte[] data = new byte[] { 0x30, 0x1a, (byte) 0xa0, 0x09, (byte) 0x81, 0x07, (byte) 0x91, 0x55, 0x43, (byte) 0x99, 0x77,
            0x15, 0x09, (byte) 0xa1, 0x04, (byte) 0x80, 0x00, (byte) 0x81, 0x00, (byte) 0x83, 0x07, (byte) 0x91, 0x55, 0x43,
            0x69, 0x26, (byte) 0x99, 0x34 };

    @Test(groups = { "functional.decode", "subscriberInformation" })
    public void testDecode() throws Exception {

        AsnInputStream ansIS = new AsnInputStream(data);
        int tag = ansIS.readTag();
        assertEquals(tag, Tag.SEQUENCE);

        AnyTimeInterrogationRequestImpl anyTimeInt = new AnyTimeInterrogationRequestImpl();
        anyTimeInt.decodeAll(ansIS);

        SubscriberIdentity subsId = anyTimeInt.getSubscriberIdentity();
        ISDNAddressString isdnAddress = subsId.getMSISDN();
        assertEquals(isdnAddress.getAddress(), "553499775190");

        ISDNAddressString gscmSCFAddress = anyTimeInt.getGsmSCFAddress();
        assertEquals(gscmSCFAddress.getAddress(), "553496629943");

        RequestedInfo requestedInfo = anyTimeInt.getRequestedInfo();
        assertTrue(requestedInfo.getLocationInformation());
        assertTrue(requestedInfo.getSubscriberState());
        DomainType domainType = requestedInfo.getRequestedDomain();
        assertNull(domainType);

    }

    @Test(groups = { "functional.decode", "subscriberInformation" })
    public void testEncode() throws Exception {

        ISDNAddressString isdnAdd = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN,
                "553499775190");
        SubscriberIdentity subsId = new SubscriberIdentityImpl(isdnAdd);
        RequestedInfo requestedInfo = new RequestedInfoImpl(true, true, null, false, null, false, false, false, false);
        ISDNAddressString gscmSCFAddress = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN,
                "553496629943");

        AnyTimeInterrogationRequestImpl anyTimeInt = new AnyTimeInterrogationRequestImpl(subsId, requestedInfo, gscmSCFAddress,
                null);

        AsnOutputStream asnOS = new AsnOutputStream();
        anyTimeInt.encodeAll(asnOS);
        byte[] encodedData = asnOS.toByteArray();
        assertTrue(Arrays.equals(data, encodedData));
    }

}
