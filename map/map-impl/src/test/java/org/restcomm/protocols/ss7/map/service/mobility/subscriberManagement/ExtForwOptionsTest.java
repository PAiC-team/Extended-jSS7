
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwOptionsForwardingReason;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtForwOptionsImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ExtForwOptionsTest {

    public byte[] getData() {
        return new byte[] { 4, 1, -92 };
    };

    public byte[] getData2() {
        return new byte[] { (byte) 134, 1, 0 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ExtForwOptionsImpl prim = new ExtForwOptionsImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(prim.getNotificationToCallingParty());
        assertTrue(prim.getNotificationToForwardingParty());
        assertTrue(!prim.getRedirectingPresentation());
        assertEquals(prim.getExtForwOptionsForwardingReason(), ExtForwOptionsForwardingReason.msBusy);

        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new ExtForwOptionsImpl();
        prim.decodeAll(asn);

        assertEquals(tag, 6);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertFalse(prim.getNotificationToCallingParty());
        assertFalse(prim.getNotificationToForwardingParty());
        assertFalse(prim.getRedirectingPresentation());
        assertEquals(prim.getExtForwOptionsForwardingReason(), ExtForwOptionsForwardingReason.msNotReachable);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        ExtForwOptionsImpl prim = new ExtForwOptionsImpl(true, false, true, ExtForwOptionsForwardingReason.msBusy);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        prim = new ExtForwOptionsImpl(false, false, false, ExtForwOptionsForwardingReason.msNotReachable);
        asn = new AsnOutputStream();
        prim.encodeAll(asn, Tag.CLASS_CONTEXT_SPECIFIC, 6);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }
}
