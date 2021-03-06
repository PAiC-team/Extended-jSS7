package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.restcomm.protocols.ss7.map.smstpdu.AbsoluteTimeStampImpl;
import org.restcomm.protocols.ss7.map.smstpdu.DataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.smstpdu.FailureCauseImpl;
import org.restcomm.protocols.ss7.map.smstpdu.ProtocolIdentifierImpl;
import org.restcomm.protocols.ss7.map.smstpdu.SmsSubmitReportTpduImpl;
import org.restcomm.protocols.ss7.map.smstpdu.UserDataImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SmsSubmitReportTpduTest {

    public byte[] getData1() {
        return new byte[] { 1, -56, 6, 112, 80, 81, 81, 16, 17, 33, 0, 10, -56, 50, -101, -3, 6, -123, 66, -95, 16 };
    }

    public byte[] getData2() {
        return new byte[] { 1, 1, 112, 80, 81, 81, 16, 17, 33, 44 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        SmsSubmitReportTpduImpl impl = new SmsSubmitReportTpduImpl(this.getData1(), null);
        assertFalse(impl.getUserDataHeaderIndicator());
        assertEquals(impl.getFailureCause().getCode(), 200);
        assertEquals(impl.getServiceCentreTimeStamp().getYear(), 7);
        assertEquals(impl.getServiceCentreTimeStamp().getMonth(), 5);
        assertEquals(impl.getServiceCentreTimeStamp().getDay(), 15);
        assertEquals(impl.getServiceCentreTimeStamp().getHour(), 15);
        assertEquals(impl.getServiceCentreTimeStamp().getMinute(), 1);
        assertEquals(impl.getServiceCentreTimeStamp().getSecond(), 11);
        assertEquals(impl.getServiceCentreTimeStamp().getTimeZone(), 12);
        assertEquals(impl.getParameterIndicator().getCode(), 6);
        assertNull(impl.getProtocolIdentifier());
        impl.getUserData().decode();
        assertEquals(impl.getDataCodingScheme().getCode(), 0);
        assertTrue(impl.getUserData().getDecodedMessage().equals("Hello !!!!"));

        impl = new SmsSubmitReportTpduImpl(this.getData2(), null);
        assertFalse(impl.getUserDataHeaderIndicator());
        assertNull(impl.getFailureCause());
        assertEquals(impl.getServiceCentreTimeStamp().getYear(), 7);
        assertEquals(impl.getServiceCentreTimeStamp().getMonth(), 5);
        assertEquals(impl.getServiceCentreTimeStamp().getDay(), 15);
        assertEquals(impl.getServiceCentreTimeStamp().getHour(), 15);
        assertEquals(impl.getServiceCentreTimeStamp().getMinute(), 1);
        assertEquals(impl.getServiceCentreTimeStamp().getSecond(), 11);
        assertEquals(impl.getServiceCentreTimeStamp().getTimeZone(), 12);
        assertEquals(impl.getParameterIndicator().getCode(), 1);
        assertEquals(impl.getProtocolIdentifier().getCode(), 44);
        assertNull(impl.getDataCodingScheme());
        assertNull(impl.getUserData());
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        UserDataImpl ud = new UserDataImpl("Hello !!!!", new DataCodingSchemeImpl(0), null, null);
        FailureCauseImpl failureCause = new FailureCauseImpl(200);
        AbsoluteTimeStampImpl serviceCentreTimeStamp = new AbsoluteTimeStampImpl(7, 5, 15, 15, 1, 11, 12);
        SmsSubmitReportTpduImpl impl = new SmsSubmitReportTpduImpl(failureCause, serviceCentreTimeStamp, null, ud);
        byte[] enc = impl.encodeData();
        assertTrue(Arrays.equals(enc, this.getData1()));

        ProtocolIdentifierImpl pi = new ProtocolIdentifierImpl(44);
        impl = new SmsSubmitReportTpduImpl(null, serviceCentreTimeStamp, pi, null);
        enc = impl.encodeData();
        assertTrue(Arrays.equals(enc, this.getData2()));
    }
}
