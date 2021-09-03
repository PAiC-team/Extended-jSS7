
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ROVolumeIfTariffSwitchImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ROVolumeIfTariffSwitchTest {

    public byte[] getData() {
        return new byte[] { -95, 6, -128, 1, 12, -127, 1, 24 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ROVolumeIfTariffSwitchImpl prim = new ROVolumeIfTariffSwitchImpl();
        prim.decodeAll(asn);

        assertEquals(tag, ROVolumeIfTariffSwitchImpl._ID_ROVolumeIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(prim.getROVolumeSinceLastTariffSwitch().intValue(), 12);
        assertEquals(prim.getROVolumeTariffSwitchInterval().intValue(), 24);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        ROVolumeIfTariffSwitchImpl prim = new ROVolumeIfTariffSwitchImpl(new Integer(12), new Integer(24));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
