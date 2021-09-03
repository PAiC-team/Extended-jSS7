
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.SGSNCapabilitiesImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SGSNCapabilitiesTest {

    public byte[] getData() {
        return new byte[] { 4, 1, 0 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 1, 1 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        SGSNCapabilitiesImpl prim = new SGSNCapabilitiesImpl();
        prim.decodeAll(asn);
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertFalse(prim.getAoCSupportedBySGSN());

        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new SGSNCapabilitiesImpl();
        prim.decodeAll(asn);
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertTrue(prim.getAoCSupportedBySGSN());
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        SGSNCapabilitiesImpl prim = new SGSNCapabilitiesImpl(0);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        prim = new SGSNCapabilitiesImpl(1);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));

        prim = new SGSNCapabilitiesImpl(true);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));

        prim = new SGSNCapabilitiesImpl(false);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

    }

}
