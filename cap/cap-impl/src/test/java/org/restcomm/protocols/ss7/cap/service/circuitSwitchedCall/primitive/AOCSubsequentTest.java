package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.AOCSubsequentImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.CAI_GSM0224Impl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AOCSubsequentTest {

    public byte[] getData1() {
        return new byte[] { 48, 12, (byte) 160, 6, (byte) 131, 1, 4, (byte) 132, 1, 5, (byte) 129, 2, 0, (byte) 222 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        AOCSubsequentImpl elem = new AOCSubsequentImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertNull(elem.getCAI_GSM0224().getE1());
        assertNull(elem.getCAI_GSM0224().getE2());
        assertNull(elem.getCAI_GSM0224().getE3());
        assertEquals((int) elem.getCAI_GSM0224().getE4(), 4);
        assertEquals((int) elem.getCAI_GSM0224().getE5(), 5);
        assertNull(elem.getCAI_GSM0224().getE6());
        assertNull(elem.getCAI_GSM0224().getE7());
        assertEquals((int) elem.getTariffSwitchInterval(), 222);
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        CAI_GSM0224Impl cai_GSM0224 = new CAI_GSM0224Impl(null, null, null, 4, 5, null, null);
        AOCSubsequentImpl elem = new AOCSubsequentImpl(cai_GSM0224, 222);
        // CAI_GSM0224 cai_GSM0224, Integer tariffSwitchInterval
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }
}
