package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.LAC;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.LocationArea;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.LACImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.LocationAreaImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.PagingAreaImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PagingAreaTest {

    public byte[] getData1() {
        return new byte[] { 48, 11, (byte) 128, 5, 66, (byte) 249, 16, 54, (byte) 186, (byte) 129, 2, 54, (byte) 186 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        PagingAreaImpl prim = new PagingAreaImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        ArrayList<LocationArea> lst = prim.getLocationAreas();

        LAIFixedLength lai = lst.get(0).getLAIFixedLength();
        assertEquals(lai.getMCC(), 249);
        assertEquals(lai.getMNC(), 1);
        assertEquals(lai.getLac(), 14010);
        assertNull(lst.get(0).getLAC());

        LAC lac = lst.get(1).getLAC();
        assertEquals(lac.getLac(), 14010);
        assertNull(lst.get(1).getLAIFixedLength());
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {

        ArrayList<LocationArea> lst = new ArrayList<LocationArea>();
        LAIFixedLengthImpl lai = new LAIFixedLengthImpl(249, 1, 14010);
        LAC lac = new LACImpl(14010);
        LocationAreaImpl l1 = new LocationAreaImpl(lai);
        LocationAreaImpl l2 = new LocationAreaImpl(lac);
        lst.add(l1);
        lst.add(l2);
        PagingAreaImpl prim = new PagingAreaImpl(lst);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));
    }
}
