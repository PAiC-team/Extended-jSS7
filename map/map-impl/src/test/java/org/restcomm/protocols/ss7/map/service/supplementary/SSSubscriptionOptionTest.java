
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CliRestrictionOption;
import org.restcomm.protocols.ss7.map.api.service.supplementary.OverrideCategory;
import org.restcomm.protocols.ss7.map.service.supplementary.SSSubscriptionOptionImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SSSubscriptionOptionTest {

    private byte[] getData1() {
        return new byte[] { -126, 1, 0 };
    }

    private byte[] getData2() {
        return new byte[] { -127, 1, 1 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        SSSubscriptionOptionImpl prim = new SSSubscriptionOptionImpl();
        prim.decodeAll(asn);

        assertEquals(tag, SSSubscriptionOptionImpl._TAG_cliRestrictionOption);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertNotNull(prim.getCliRestrictionOption());
        assertNull(prim.getOverrideCategory());
        assertTrue(prim.getCliRestrictionOption().getCode() == CliRestrictionOption.permanent.getCode());

        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new SSSubscriptionOptionImpl();
        prim.decodeAll(asn);

        assertEquals(tag, SSSubscriptionOptionImpl._TAG_overrideCategory);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertNull(prim.getCliRestrictionOption());
        assertNotNull(prim.getOverrideCategory());
        assertTrue(prim.getOverrideCategory().getCode() == OverrideCategory.overrideDisabled.getCode());

    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        SSSubscriptionOptionImpl impl = new SSSubscriptionOptionImpl(CliRestrictionOption.permanent);
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);
        assertEquals(asnOS.toByteArray(), this.getData1());

        impl = new SSSubscriptionOptionImpl(OverrideCategory.overrideDisabled);
        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);
        assertEquals(asnOS.toByteArray(), this.getData2());

    }
}
