
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.VolumeIfTariffSwitchImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class VolumeIfTariffSwitchTest {

    public byte[] getData() {
        return new byte[] { -95, 6, -128, 1, 12, -127, 1, 24 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        VolumeIfTariffSwitchImpl prim = new VolumeIfTariffSwitchImpl();
        prim.decodeAll(asn);

        assertEquals(tag, VolumeIfTariffSwitchImpl._ID_VolumeIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(prim.getVolumeSinceLastTariffSwitch(), 12);
        assertEquals(prim.getVolumeTariffSwitchInterval().longValue(), 24);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        VolumeIfTariffSwitchImpl prim = new VolumeIfTariffSwitchImpl(12, new Long(24));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
