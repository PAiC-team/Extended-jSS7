
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ElapsedTimeRollOverImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ROTimeGPRSIfTariffSwitchImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TransferredVolumeImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ElapsedTimeRollOverTest {

    public byte[] getData() {
        return new byte[] { -128, 1, 24 };
    };

    public byte[] getData2() {
        return new byte[] { -95, 6, -128, 1, 12, -127, 1, 24 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // Option 1
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ElapsedTimeRollOverImpl prim = new ElapsedTimeRollOverImpl();
        prim.decodeAll(asn);

        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfNoTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertTrue(prim.getIsPrimitive());
        assertEquals(prim.getROTimeGPRSIfNoTariffSwitch().intValue(), 24);
        assertNull(prim.getROTimeGPRSIfTariffSwitch());

        // Option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ElapsedTimeRollOverImpl();
        prim.decodeAll(asn);
        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());

        assertNull(prim.getROTimeGPRSIfNoTariffSwitch());
        assertEquals(prim.getROTimeGPRSIfTariffSwitch().getROTimeGPRSSinceLastTariffSwitch().intValue(), 12);
        assertEquals(prim.getROTimeGPRSIfTariffSwitch().getROTimeGPRSTariffSwitchInterval().intValue(), 24);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // Option 1
        ElapsedTimeRollOverImpl prim = new ElapsedTimeRollOverImpl(new Integer(24));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // Option 2
        ROTimeGPRSIfTariffSwitchImpl roTimeGPRSIfTariffSwitch = new ROTimeGPRSIfTariffSwitchImpl(new Integer(12), new Integer(
                24));
        prim = new ElapsedTimeRollOverImpl(roTimeGPRSIfTariffSwitch);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

}
