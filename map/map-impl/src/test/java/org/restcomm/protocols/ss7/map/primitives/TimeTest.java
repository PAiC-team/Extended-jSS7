
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.primitives.TimeImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TimeTest {

    public byte[] getData() {
        return new byte[] { 4, 4, -95, 17, 53, -98 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 4, 127, -2, -14, 30 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // option 1
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        TimeImpl prim = new TimeImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getYear(), 1985);
        assertEquals(prim.getMonth(), 8);
        assertEquals(prim.getDay(), 19);
        assertEquals(prim.getHour(), 3);
        assertEquals(prim.getMinute(), 40);
        assertEquals(prim.getSecond(), 14);

        // option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new TimeImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getYear(), 2104);
        assertEquals(prim.getMonth(), 2);
        assertEquals(prim.getDay(), 25);
        assertEquals(prim.getHour(), 14);
        assertEquals(prim.getMinute(), 30);
        assertEquals(prim.getSecond(), 54);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        TimeImpl prim = new TimeImpl(1985, 8, 19, 3, 40, 14);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        prim = new TimeImpl(2104, 2, 25, 14, 30, 54);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }
}
