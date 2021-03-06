
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentificationPriorityValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAAttributesImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class LSAAttributesTest {

    public byte[] getData() {
        return new byte[] { 4, 1, 57 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 1, 15 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // option 1
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        LSAAttributesImpl prim = new LSAAttributesImpl();
        prim.decodeAll(asn);
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(prim.isActiveModeSupportAvailable());
        assertTrue(prim.isPreferentialAccessAvailable());
        assertEquals(prim.getLSAIdentificationPriority(), LSAIdentificationPriorityValue.Priority_10);

        // option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new LSAAttributesImpl();
        prim.decodeAll(asn);
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertFalse(prim.isActiveModeSupportAvailable());
        assertFalse(prim.isPreferentialAccessAvailable());
        assertEquals(prim.getLSAIdentificationPriority(), LSAIdentificationPriorityValue.Priority_16);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        // option 1
        LSAAttributesImpl prim = new LSAAttributesImpl(LSAIdentificationPriorityValue.Priority_10, true, true);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        // option 2
        prim = new LSAAttributesImpl(LSAIdentificationPriorityValue.Priority_16, false, false);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));

    }

}
