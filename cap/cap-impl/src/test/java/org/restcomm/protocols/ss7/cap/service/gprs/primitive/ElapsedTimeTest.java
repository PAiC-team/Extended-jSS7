
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.TimeGPRSIfTariffSwitch;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ElapsedTimeImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TimeGPRSIfTariffSwitchImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TransferredVolumeImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ElapsedTimeTest {

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
        ElapsedTimeImpl prim = new ElapsedTimeImpl();
        prim.decodeAll(asn);

        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfNoTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertTrue(prim.getIsPrimitive());
        assertEquals(prim.getTimeGPRSIfNoTariffSwitch().intValue(), 24);
        assertNull(prim.getTimeGPRSIfTariffSwitch());

        // Option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ElapsedTimeImpl();
        prim.decodeAll(asn);
        assertEquals(tag, TransferredVolumeImpl._ID_volumeIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());

        assertNull(prim.getTimeGPRSIfNoTariffSwitch());
        assertEquals(prim.getTimeGPRSIfTariffSwitch().getTimeGPRSSinceLastTariffSwitch(), 12);
        assertEquals(prim.getTimeGPRSIfTariffSwitch().getTimeGPRSTariffSwitchInterval().intValue(), 24);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // Option 1
        ElapsedTimeImpl prim = new ElapsedTimeImpl(new Integer(24));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // Option 2
        TimeGPRSIfTariffSwitch timeGPRSIfTariffSwitch = new TimeGPRSIfTariffSwitchImpl(12, new Integer(24));
        prim = new ElapsedTimeImpl(timeGPRSIfTariffSwitch);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

}
