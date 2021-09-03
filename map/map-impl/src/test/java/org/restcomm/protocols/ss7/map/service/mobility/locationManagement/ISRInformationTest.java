
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.ISRInformationImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ISRInformationTest {

    public byte[] getData1() {
        return new byte[] { 3, 2, 5, -32 };
    };

    public byte[] getData2() {
        return new byte[] { 3, 2, 5, 0 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // option 1
        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        ISRInformationImpl prim = new ISRInformationImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(prim.getCancelSGSN());
        assertTrue(prim.getInitialAttachIndicator());
        assertTrue(prim.getUpdateMME());

        // option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();

        prim = new ISRInformationImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertFalse(prim.getCancelSGSN());
        assertFalse(prim.getInitialAttachIndicator());
        assertFalse(prim.getUpdateMME());

    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {
        // option 1
        ISRInformationImpl prim = new ISRInformationImpl(true, true, true);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));

        // option 2
        prim = new ISRInformationImpl(false, false, false);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));

    }

}
