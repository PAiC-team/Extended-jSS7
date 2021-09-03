
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ISRInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PDNGWUpdate;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.EPSInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.ISRInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.PDNGWUpdateImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class EPSInfoTest {

    public byte[] getData1() {
        return new byte[] { -96, 44, -126, 1, 2, -93, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42,
                3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33 };
    };

    public byte[] getData2() {
        return new byte[] { -127, 2, 5, -32 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        // option 1
        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        EPSInfoImpl prim = new EPSInfoImpl();
        prim.decodeAll(asn);

        assertEquals(tag, EPSInfoImpl._TAG_pndGwUpdate);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(prim.getPndGwUpdate().getContextId(), new Integer(2));
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(prim.getPndGwUpdate().getExtensionContainer()));

        // option 2
        data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();

        prim = new EPSInfoImpl();
        prim.decodeAll(asn);

        assertEquals(tag, EPSInfoImpl._TAG_isrInformation);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertTrue(prim.getIsrInformation().getCancelSGSN());
        assertTrue(prim.getIsrInformation().getInitialAttachIndicator());
        assertTrue(prim.getIsrInformation().getUpdateMME());
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {
        // option 1
        MAPExtensionContainer extensionContainer = MAPExtensionContainerTest.GetTestExtensionContainer();
        PDNGWUpdate pndGwUpdate = new PDNGWUpdateImpl(null, null, new Integer(2), extensionContainer);
        EPSInfoImpl prim = new EPSInfoImpl(pndGwUpdate);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));

        // option 2
        ISRInformation isrInformation = new ISRInformationImpl(true, true, true);
        prim = new EPSInfoImpl(isrInformation);
        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

}
