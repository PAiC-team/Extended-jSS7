
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientExternalID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GMLCRestriction;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.NotificationToMSUser;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientExternalIDImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExternalClientImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ExternalClientTest {

    public byte[] getData() {
        return new byte[] { 48, 96, 48, 47, -128, 4, -111, 34, 34, -8, -95, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13,
                14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33, -128, 1, 0,
                -127, 1, 3, -94, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3,
                42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ExternalClientImpl prim = new ExternalClientImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        MAPExtensionContainer extensionContainer = prim.getExtensionContainer();

        LCSClientExternalID clientIdentity = prim.getClientIdentity();
        ISDNAddressString externalAddress = clientIdentity.getExternalAddress();
        assertTrue(externalAddress.getAddress().equals("22228"));
        assertEquals(externalAddress.getAddressNature(), AddressNature.international_number);
        assertEquals(externalAddress.getNumberingPlan(), NumberingPlan.ISDN);
        assertNotNull(clientIdentity.getExtensionContainer());
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(clientIdentity.getExtensionContainer()));

        assertEquals(prim.getGMLCRestriction(), GMLCRestriction.gmlcList);
        assertEquals(prim.getNotificationToMSUser(), NotificationToMSUser.locationNotAllowed);

        assertNotNull(extensionContainer);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(extensionContainer));
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        MAPExtensionContainer extensionContainer = MAPExtensionContainerTest.GetTestExtensionContainer();
        ISDNAddressString externalAddress = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN,
                "22228");
        LCSClientExternalID clientIdentity = new LCSClientExternalIDImpl(externalAddress, extensionContainer);
        GMLCRestriction gmlcRestriction = GMLCRestriction.gmlcList;
        NotificationToMSUser notificationToMSUser = NotificationToMSUser.locationNotAllowed;
        ExternalClientImpl prim = new ExternalClientImpl(clientIdentity, gmlcRestriction, notificationToMSUser,
                extensionContainer);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
