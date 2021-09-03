
package org.restcomm.protocols.ss7.map.service.mobility.imei;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.imei.UESBIIuAImpl;
import org.restcomm.protocols.ss7.map.service.mobility.imei.UESBIIuBImpl;
import org.restcomm.protocols.ss7.map.service.mobility.imei.UESBIIuImpl;
import org.testng.annotations.Test;

/**
 *
 * @author normandes
 *
 */
public class UESBIIuTest {

    public byte[] getEncodedData() {
        // TODO this is self generated trace. We need trace from operator
        return new byte[] { 48, 8, -128, 2, 7, -128, -127, 2, 7, 0 };
    }

    @Test(groups = { "functional.decode", "imei" })
    public void testDecode() throws Exception {
        byte[] data = getEncodedData();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);

        UESBIIuImpl imp = new UESBIIuImpl();
        imp.decodeAll(asn);

        BitSetStrictLength bsUESBIIuA = imp.getUESBI_IuA().getData();
        BitSetStrictLength bsUESBIIuB = imp.getUESBI_IuB().getData();

        assertTrue(bsUESBIIuA.get(0));
        assertFalse(bsUESBIIuB.get(0));
    }

    @Test(groups = { "functional.encode", "imei" })
    public void testEncode() throws Exception {
        BitSetStrictLength bsUESBIIuA = new BitSetStrictLength(1);
        bsUESBIIuA.set(0);
        UESBIIuAImpl impUESBIIuA = new UESBIIuAImpl(bsUESBIIuA);

        BitSetStrictLength bsUESBIIuB = new BitSetStrictLength(1);
        UESBIIuBImpl impUESBIIuB = new UESBIIuBImpl(bsUESBIIuB);

        UESBIIuImpl imp = new UESBIIuImpl(impUESBIIuA, impUESBIIuB);

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
