
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TransferredVolumeImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.VolumeIfTariffSwitchImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TransferredVolumeTest {

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
        TransferredVolumeImpl prim = new TransferredVolumeImpl();
        prim.decodeAll(asn);

        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfNoTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertTrue(prim.getIsPrimitive());
        assertEquals(prim.getVolumeIfNoTariffSwitch().longValue(), 25);
        assertNull(prim.getVolumeIfTariffSwitch());

        // Option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new TransferredVolumeImpl();
        prim.decodeAll(asn);
        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());
        assertNull(prim.getVolumeIfNoTariffSwitch());
        assertEquals(prim.getVolumeIfTariffSwitch().getVolumeSinceLastTariffSwitch(), 12);
        assertEquals(prim.getVolumeIfTariffSwitch().getVolumeTariffSwitchInterval().longValue(), 24);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // Option 1
        TransferredVolumeImpl prim = new TransferredVolumeImpl(new Long(25));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // Option 2
        VolumeIfTariffSwitchImpl volumeIfTariffSwitch = new VolumeIfTariffSwitchImpl(12, new Long(24));
        prim = new TransferredVolumeImpl(volumeIfTariffSwitch);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

}
