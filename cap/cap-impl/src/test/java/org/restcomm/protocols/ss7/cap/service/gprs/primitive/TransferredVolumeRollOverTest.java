
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ROVolumeIfTariffSwitchImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TransferredVolumeImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TransferredVolumeRollOverImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TransferredVolumeRollOverTest {

    public byte[] getData() {
        return new byte[] { -128, 1, 25 };
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
        TransferredVolumeRollOverImpl prim = new TransferredVolumeRollOverImpl();
        prim.decodeAll(asn);

        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfNoTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertTrue(prim.getIsPrimitive());
        assertNull(prim.getROVolumeIfTariffSwitch());
        assertEquals(prim.getROVolumeIfNoTariffSwitch().intValue(), 25);

        // Option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new TransferredVolumeRollOverImpl();
        prim.decodeAll(asn);
        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());
        assertNull(prim.getROVolumeIfNoTariffSwitch());
        assertEquals(prim.getROVolumeIfTariffSwitch().getROVolumeSinceLastTariffSwitch().intValue(), 12);
        assertEquals(prim.getROVolumeIfTariffSwitch().getROVolumeTariffSwitchInterval().intValue(), 24);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // Option 1
        TransferredVolumeRollOverImpl prim = new TransferredVolumeRollOverImpl(new Integer(25));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // Option 2
        ROVolumeIfTariffSwitchImpl volumeIfTariffSwitch = new ROVolumeIfTariffSwitchImpl(new Integer(12), new Integer(24));
        prim = new TransferredVolumeRollOverImpl(volumeIfTariffSwitch);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

}
