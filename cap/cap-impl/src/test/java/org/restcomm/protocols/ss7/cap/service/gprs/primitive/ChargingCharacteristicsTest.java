
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ChargingCharacteristicsImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ChargingCharacteristicsTest {

    public byte[] getData() {
        return new byte[] { -128, 2, 0, -56 };
    };

    public byte[] getData2() {
        return new byte[] { -127, 1, 20 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // Option 1
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ChargingCharacteristicsImpl prim = new ChargingCharacteristicsImpl();
        prim.decodeAll(asn);
        assertEquals(tag, ChargingCharacteristicsImpl._ID_maxTransferredVolume);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertEquals(prim.getMaxTransferredVolume(), 200L);
        assertEquals(prim.getMaxElapsedTime(), -1);

        // Option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ChargingCharacteristicsImpl();
        prim.decodeAll(asn);
        assertEquals(tag, ChargingCharacteristicsImpl._ID_maxElapsedTime);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertEquals(prim.getMaxTransferredVolume(), -1L);
        assertEquals(prim.getMaxElapsedTime(), 20);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // Option 1
        ChargingCharacteristicsImpl prim = new ChargingCharacteristicsImpl(200L);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // Option 2
        prim = new ChargingCharacteristicsImpl(20);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

}
