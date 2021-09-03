
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.PurgeMSResponseImpl;
import org.testng.annotations.Test;

/**
*
* @author Lasith Waruna Perera
*
*/
public class PurgeMSResponseTest {

    public byte[] getData1() {
        return new byte[] { 48, 47, -128, 0, -127, 0, 48, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3,
                42, 3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33, -126, 0 };
    };

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {
        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        PurgeMSResponseImpl prim = new PurgeMSResponseImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(prim.getFreezeMTMSI());
        assertTrue(prim.getFreezePTMSI());
        assertTrue(prim.getFreezeTMSI());
        
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(prim.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {
       
        MAPExtensionContainer extensionContainer = MAPExtensionContainerTest.GetTestExtensionContainer();
        PurgeMSResponseImpl prim = new PurgeMSResponseImpl(true, true, extensionContainer, true);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
    
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));

    }
}
