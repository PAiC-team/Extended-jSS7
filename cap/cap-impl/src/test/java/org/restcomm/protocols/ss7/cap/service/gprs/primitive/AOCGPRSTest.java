
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AOCSubsequent;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAI_GSM0224;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.AOCSubsequentImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.CAI_GSM0224Impl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.AOCGPRSImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class AOCGPRSTest {

    public byte[] getData() {
        return new byte[] { 48, 37, -96, 21, -128, 1, 1, -127, 1, 2, -126, 1, 3, -125, 1, 4, -124, 1, 5, -123, 1, 6, -122, 1,
                7, -95, 12, -96, 6, -125, 1, 4, -124, 1, 5, -127, 2, 0, -34 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        AOCGPRSImpl prim = new AOCGPRSImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals((int) prim.getAOCInitial().getE1(), 1);
        assertEquals((int) prim.getAOCInitial().getE2(), 2);
        assertEquals((int) prim.getAOCInitial().getE3(), 3);
        assertEquals((int) prim.getAOCInitial().getE4(), 4);
        assertEquals((int) prim.getAOCInitial().getE5(), 5);
        assertEquals((int) prim.getAOCInitial().getE6(), 6);
        assertEquals((int) prim.getAOCInitial().getE7(), 7);

        assertNull(prim.getAOCSubsequent().getCAI_GSM0224().getE1());
        assertNull(prim.getAOCSubsequent().getCAI_GSM0224().getE2());
        assertNull(prim.getAOCSubsequent().getCAI_GSM0224().getE3());
        assertEquals((int) prim.getAOCSubsequent().getCAI_GSM0224().getE4(), 4);
        assertEquals((int) prim.getAOCSubsequent().getCAI_GSM0224().getE5(), 5);
        assertNull(prim.getAOCSubsequent().getCAI_GSM0224().getE6());
        assertNull(prim.getAOCSubsequent().getCAI_GSM0224().getE7());
        assertEquals((int) prim.getAOCSubsequent().getTariffSwitchInterval(), 222);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        CAI_GSM0224 aocInitial = new CAI_GSM0224Impl(1, 2, 3, 4, 5, 6, 7);
        CAI_GSM0224Impl cai_GSM0224 = new CAI_GSM0224Impl(null, null, null, 4, 5, null, null);
        AOCSubsequent aocSubsequent = new AOCSubsequentImpl(cai_GSM0224, 222);

        AOCGPRSImpl prim = new AOCGPRSImpl(aocInitial, aocSubsequent);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
