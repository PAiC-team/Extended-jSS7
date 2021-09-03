package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.restcomm.protocols.ss7.map.api.smstpdu.NumberingPlanIdentification;
import org.restcomm.protocols.ss7.map.api.smstpdu.TypeOfNumber;
import org.restcomm.protocols.ss7.map.smstpdu.AbsoluteTimeStampImpl;
import org.restcomm.protocols.ss7.map.smstpdu.AddressFieldImpl;
import org.restcomm.protocols.ss7.map.smstpdu.DataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.smstpdu.ProtocolIdentifierImpl;
import org.restcomm.protocols.ss7.map.smstpdu.SmsDeliverTpduImpl;
import org.restcomm.protocols.ss7.map.smstpdu.UserDataHeaderImpl;
import org.restcomm.protocols.ss7.map.smstpdu.UserDataImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SmsDeliverTpduTest {

    public byte[] getData1() {
        return new byte[] { -28, 10, -111, 33, 67, 101, -121, 9, 0, 0, 112, 80, 81, 81, 16, 17, 33, 23, 5, 0, 3, -21, 2, 1,
                -112, 101, 54, -5, -51, 2, -35, -33, 114, 54, 25, 20, 10, -123, 0 };
    }

    public byte[] getData1A() {
        return new byte[] { -21, 2, 1 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        SmsDeliverTpduImpl impl = new SmsDeliverTpduImpl(this.getData1(), null);
        ;

        impl.getUserData().decode();
        assertFalse(impl.getMoreMessagesToSend());
        assertFalse(impl.getForwardedOrSpawned());
        assertTrue(impl.getReplyPathExists());
        assertTrue(impl.getUserDataHeaderIndicator());
        assertTrue(impl.getStatusReportIndication());
        assertEquals(impl.getOriginatingAddress().getTypeOfNumber(), TypeOfNumber.InternationalNumber);
        assertEquals(impl.getOriginatingAddress().getNumberingPlanIdentification(),
                NumberingPlanIdentification.ISDNTelephoneNumberingPlan);
        assertTrue(impl.getOriginatingAddress().getAddressValue().equals("1234567890"));
        assertEquals(impl.getProtocolIdentifier().getCode(), 0);
        assertEquals(impl.getDataCodingScheme().getCode(), 0);
        assertEquals(impl.getServiceCentreTimeStamp().getYear(), 7);
        assertEquals(impl.getServiceCentreTimeStamp().getMonth(), 5);
        assertEquals(impl.getServiceCentreTimeStamp().getDay(), 15);
        assertEquals(impl.getServiceCentreTimeStamp().getHour(), 15);
        assertEquals(impl.getServiceCentreTimeStamp().getMinute(), 1);
        assertEquals(impl.getServiceCentreTimeStamp().getSecond(), 11);
        assertEquals(impl.getServiceCentreTimeStamp().getTimeZone(), 12);
        assertEquals(impl.getUserDataLength(), 23);
        assertTrue(impl.getUserData().getDecodedMessage().equals("Hello, world !!!"));
        assertTrue(Arrays.equals(impl.getUserData().getDecodedUserDataHeader().getInformationElementData(0), this.getData1A()));
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        UserDataHeaderImpl udh = new UserDataHeaderImpl();
        udh.addInformationElement(0, this.getData1A());
        UserDataImpl ud = new UserDataImpl("Hello, world !!!", new DataCodingSchemeImpl(0), udh, null);

        AddressFieldImpl originatingAddress = new AddressFieldImpl(TypeOfNumber.InternationalNumber,
                NumberingPlanIdentification.ISDNTelephoneNumberingPlan, "1234567890");
        ProtocolIdentifierImpl pi = new ProtocolIdentifierImpl(0);
        AbsoluteTimeStampImpl serviceCentreTimeStamp = new AbsoluteTimeStampImpl(7, 5, 15, 15, 1, 11, 12);
        SmsDeliverTpduImpl impl = new SmsDeliverTpduImpl(false, false, true, true, originatingAddress, pi,
                serviceCentreTimeStamp, ud);
        byte[] enc = impl.encodeData();
        assertTrue(Arrays.equals(enc, this.getData1()));

        // udh = new UserDataHeaderImpl();
        // ud = new UserDataImpl(
        // "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890",
        // new DataCodingSchemeImpl(0), udh, null);
        //
        // originatingAddress = new AddressFieldImpl(TypeOfNumber.InternationalNumber,
        // NumberingPlanIdentification.ISDNTelephoneNumberingPlan,
        // "1234567890");
        // pi = new ProtocolIdentifierImpl(0);
        // serviceCentreTimeStamp = new AbsoluteTimeStampImpl(7, 5, 15, 15, 1, 11, 12);
        // impl = new SmsDeliverTpduImpl(false, false, true, true, originatingAddress, pi, serviceCentreTimeStamp, ud);
        // enc = impl.encodeData();
        // assertTrue(Arrays.equals(enc, this.getData1()));
    }
}
