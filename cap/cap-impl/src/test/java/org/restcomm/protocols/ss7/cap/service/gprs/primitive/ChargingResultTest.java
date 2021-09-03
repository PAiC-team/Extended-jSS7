
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
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ChargingResultImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ElapsedTimeImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TimeGPRSIfTariffSwitchImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.TransferredVolumeImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.VolumeIfTariffSwitchImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ChargingResultTest {

    public byte[] getData() {
        return new byte[] { -95, 3, -128, 1, 24 };
    };

    public byte[] getData1() {
        return new byte[] { -95, 8, -95, 6, -128, 1, 12, -127, 1, 24 };
    };

    public byte[] getData2() {
        return new byte[] { -96, 3, -128, 1, 25 };
    };

    public byte[] getData3() {
        return new byte[] { -96, 8, -95, 6, -128, 1, 12, -127, 1, 24 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // Option 0
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ChargingResultImpl prim = new ChargingResultImpl();
        prim.decodeAll(asn);
        assertEquals(tag, ChargingResultImpl._ID_elapsedTime);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());
        assertEquals(prim.getElapsedTime().getTimeGPRSIfNoTariffSwitch().intValue(), 24);
        assertNull(prim.getTransferredVolume());

        // Option 1
        data = this.getData1();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ChargingResultImpl();
        prim.decodeAll(asn);
        assertEquals(tag, ChargingResultImpl._ID_elapsedTime);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());
        assertNull(prim.getElapsedTime().getTimeGPRSIfNoTariffSwitch());
        assertEquals(prim.getElapsedTime().getTimeGPRSIfTariffSwitch().getTimeGPRSSinceLastTariffSwitch(), 12);
        assertEquals(prim.getElapsedTime().getTimeGPRSIfTariffSwitch().getTimeGPRSTariffSwitchInterval().intValue(), 24);
        assertNull(prim.getTransferredVolume());

        // Option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ChargingResultImpl();
        prim.decodeAll(asn);
        assertEquals(tag, ChargingResultImpl._ID_transferredVolume);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());
        assertEquals(prim.getTransferredVolume().getVolumeIfNoTariffSwitch().longValue(), 25);
        assertNull(prim.getElapsedTime());

        // Option 3
        data = this.getData3();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ChargingResultImpl();
        prim.decodeAll(asn);
        assertEquals(tag, ChargingResultImpl._ID_transferredVolume);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        assertFalse(prim.getIsPrimitive());
        assertNull(prim.getTransferredVolume().getVolumeIfNoTariffSwitch());
        assertEquals(prim.getTransferredVolume().getVolumeIfTariffSwitch().getVolumeSinceLastTariffSwitch(), 12);
        assertEquals(prim.getTransferredVolume().getVolumeIfTariffSwitch().getVolumeTariffSwitchInterval().longValue(), 24);
        assertNull(prim.getElapsedTime());
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // Option 0
        ElapsedTimeImpl elapsedTime = new ElapsedTimeImpl(new Integer(24));
        ChargingResultImpl prim = new ChargingResultImpl(elapsedTime);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // Option 1
        TimeGPRSIfTariffSwitch timeGPRSIfTariffSwitch = new TimeGPRSIfTariffSwitchImpl(12, new Integer(24));
        elapsedTime = new ElapsedTimeImpl(timeGPRSIfTariffSwitch);
        prim = new ChargingResultImpl(elapsedTime);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));

        // Option 2
        TransferredVolumeImpl extQoSSubscribed = new TransferredVolumeImpl(new Long(25));
        prim = new ChargingResultImpl(extQoSSubscribed);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));

        // Option 3
        VolumeIfTariffSwitchImpl volumeIfTariffSwitch = new VolumeIfTariffSwitchImpl(12, new Long(24));
        extQoSSubscribed = new TransferredVolumeImpl(volumeIfTariffSwitch);
        prim = new ChargingResultImpl(extQoSSubscribed);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData3()));
    }

}
