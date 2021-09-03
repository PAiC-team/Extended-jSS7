package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.LAC;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.LACImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.LocationAreaImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class LocationAreaTest {

    public byte[] getData1() {
        return new byte[] { (byte) 128, 5, 66, (byte) 249, 16, 54, (byte) 186 };
    };

    public byte[] getData2() {
        return new byte[] { (byte) 129, 2, 54, (byte) 186 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        LocationAreaImpl prim = new LocationAreaImpl();
        prim.decodeAll(asn);

        assertEquals(tag, LocationAreaImpl._TAG_laiFixedLength);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        LAIFixedLength lai = prim.getLAIFixedLength();
        assertEquals(lai.getMCC(), 249);
        assertEquals(lai.getMNC(), 1);
        assertEquals(lai.getLac(), 14010);
        assertNull(prim.getLAC());

        data = this.getData2();

        asn = new AsnInputStream(data);
        tag = asn.readTag();

        prim = new LocationAreaImpl();
        prim.decodeAll(asn);

        assertEquals(tag, LocationAreaImpl._TAG_lac);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertNull(prim.getLAIFixedLength());
        LAC lac = prim.getLAC();
        assertEquals(lac.getLac(), 14010);
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {

        LAIFixedLengthImpl lai = new LAIFixedLengthImpl(249, 1, 14010);
        LocationAreaImpl prim = new LocationAreaImpl(lai);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));

        LAC lac = new LACImpl(14010);
        prim = new LocationAreaImpl(lac);

        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }
}
