
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InterCUGRestrictionsValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.InterCUGRestrictionsImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class InterCUGRestrictionsTest {

    public byte[] getData() {
        return new byte[] { 4, 1, 2 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        InterCUGRestrictionsImpl prim = new InterCUGRestrictionsImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getInterCUGRestrictionsValue(), InterCUGRestrictionsValue.CUGWithIncomingAccess);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        InterCUGRestrictionsImpl prim = new InterCUGRestrictionsImpl(InterCUGRestrictionsValue.CUGWithIncomingAccess);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }
}
