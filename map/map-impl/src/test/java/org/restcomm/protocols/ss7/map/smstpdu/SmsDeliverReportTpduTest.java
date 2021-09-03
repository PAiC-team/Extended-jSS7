package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.restcomm.protocols.ss7.map.smstpdu.DataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.smstpdu.FailureCauseImpl;
import org.restcomm.protocols.ss7.map.smstpdu.ProtocolIdentifierImpl;
import org.restcomm.protocols.ss7.map.smstpdu.SmsDeliverReportTpduImpl;
import org.restcomm.protocols.ss7.map.smstpdu.UserDataImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SmsDeliverReportTpduTest {

    public byte[] getData1() {
        return new byte[] { 0, -56, 6, 0, 10, -56, 50, -101, -3, 6, -123, 66, -95, 16 };
    }

    public byte[] getData2() {
        return new byte[] { 0, 1, 44 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        SmsDeliverReportTpduImpl impl = new SmsDeliverReportTpduImpl(this.getData1(), null);
        assertFalse(impl.getUserDataHeaderIndicator());
        assertEquals(impl.getFailureCause().getCode(), 200);
        assertEquals(impl.getParameterIndicator().getCode(), 6);
        assertNull(impl.getProtocolIdentifier());
        impl.getUserData().decode();
        assertEquals(impl.getDataCodingScheme().getCode(), 0);
        assertTrue(impl.getUserData().getDecodedMessage().equals("Hello !!!!"));

        impl = new SmsDeliverReportTpduImpl(this.getData2(), null);
        assertFalse(impl.getUserDataHeaderIndicator());
        assertNull(impl.getFailureCause());
        assertEquals(impl.getParameterIndicator().getCode(), 1);
        assertEquals(impl.getProtocolIdentifier().getCode(), 44);
        assertNull(impl.getDataCodingScheme());
        assertNull(impl.getUserData());
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        UserDataImpl ud = new UserDataImpl("Hello !!!!", new DataCodingSchemeImpl(0), null, null);
        FailureCauseImpl failureCause = new FailureCauseImpl(200);
        SmsDeliverReportTpduImpl impl = new SmsDeliverReportTpduImpl(failureCause, null, ud);
        byte[] enc = impl.encodeData();
        assertTrue(Arrays.equals(enc, this.getData1()));

        ProtocolIdentifierImpl pi = new ProtocolIdentifierImpl(44);
        impl = new SmsDeliverReportTpduImpl(null, pi, null);
        enc = impl.encodeData();
        assertTrue(Arrays.equals(enc, this.getData2()));
    }
}
