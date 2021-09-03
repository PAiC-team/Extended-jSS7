package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.CAI_GSM0224Impl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAI_GSM0224Test {

    public byte[] getData1() {
        return new byte[] { 48, 21, (byte) 128, 1, 1, (byte) 129, 1, 2, (byte) 130, 1, 3, (byte) 131, 1, 4, (byte) 132, 1, 5,
                (byte) 133, 1, 6, (byte) 134, 1, 7 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        CAI_GSM0224Impl elem = new CAI_GSM0224Impl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertEquals((int) elem.getE1(), 1);
        assertEquals((int) elem.getE2(), 2);
        assertEquals((int) elem.getE3(), 3);
        assertEquals((int) elem.getE4(), 4);
        assertEquals((int) elem.getE5(), 5);
        assertEquals((int) elem.getE6(), 6);
        assertEquals((int) elem.getE7(), 7);
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        CAI_GSM0224Impl elem = new CAI_GSM0224Impl(1, 2, 3, 4, 5, 6, 7);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }
}
