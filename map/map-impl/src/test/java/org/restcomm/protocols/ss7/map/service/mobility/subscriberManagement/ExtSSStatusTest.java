
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtSSStatusImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ExtSSStatusTest {
    public byte[] getData() {
        return new byte[] { 4, 1, 5 };
    };

    public byte[] getData2() {
        return new byte[] { (byte) 132, 1, 15 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ExtSSStatusImpl prim = new ExtSSStatusImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(!prim.getBitQ());
        assertTrue(prim.getBitP());
        assertTrue(!prim.getBitR());
        assertTrue(prim.getBitA());

        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ExtSSStatusImpl();
        prim.decodeAll(asn);

        assertEquals(tag, 4);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertTrue(prim.getBitQ());
        assertTrue(prim.getBitP());
        assertTrue(prim.getBitR());
        assertTrue(prim.getBitA());
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        ExtSSStatusImpl prim = new ExtSSStatusImpl(false, true, false, true);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        prim = new ExtSSStatusImpl(true, true, true, true);
        asn = new AsnOutputStream();
        prim.encodeAll(asn, Tag.CLASS_CONTEXT_SPECIFIC, 4);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }
}
