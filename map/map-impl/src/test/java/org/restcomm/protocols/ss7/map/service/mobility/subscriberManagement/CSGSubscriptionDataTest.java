
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.Time;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APN;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGId;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.primitives.TimeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.APNImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CSGIdImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CSGSubscriptionDataImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CSGSubscriptionDataTest {

    public byte[] getData() {
        return new byte[] { 48, 60, 3, 5, 5, -128, 0, 0, 32, 4, 4, 10, 22, 41, 34, 48, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11,
                12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33, -96,
                4, 4, 2, 6, 7 };
    };

    public byte[] getTimeData() {
        return new byte[] { 10, 22, 41, 34 };
    };

    public byte[] getAPNData() {
        return new byte[] { 6, 7 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        CSGSubscriptionDataImpl prim = new CSGSubscriptionDataImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        BitSetStrictLength bs = prim.getCsgId().getData();
        assertTrue(bs.get(0));
        assertFalse(bs.get(1));
        assertFalse(bs.get(25));
        assertTrue(bs.get(26));

        assertTrue(Arrays.equals(prim.getExpirationDate().getData(), this.getTimeData()));

        ArrayList<APN> lipaAllowedAPNList = prim.getLipaAllowedAPNList();
        assertNotNull(lipaAllowedAPNList);
        assertEquals(lipaAllowedAPNList.size(), 1);
        assertTrue(Arrays.equals(lipaAllowedAPNList.get(0).getData(), this.getAPNData()));

        MAPExtensionContainer extensionContainer = prim.getExtensionContainer();
        assertNotNull(extensionContainer);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(extensionContainer));
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {
        MAPExtensionContainer extensionContainer = MAPExtensionContainerTest.GetTestExtensionContainer();

        BitSetStrictLength bs = new BitSetStrictLength(27);
        bs.set(0);
        bs.set(26);
        CSGId csgId = new CSGIdImpl(bs);
        Time expirationDate = new TimeImpl(this.getTimeData());
        ArrayList<APN> lipaAllowedAPNList = new ArrayList<APN>();
        APN apn = new APNImpl(this.getAPNData());
        lipaAllowedAPNList.add(apn);

        CSGSubscriptionDataImpl prim = new CSGSubscriptionDataImpl(csgId, expirationDate, extensionContainer,
                lipaAllowedAPNList);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
