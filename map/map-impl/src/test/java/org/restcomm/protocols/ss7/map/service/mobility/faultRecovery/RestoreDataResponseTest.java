
package org.restcomm.protocols.ss7.map.service.mobility.faultRecovery;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.mobility.faultRecovery.RestoreDataResponseImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class RestoreDataResponseTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 7, 4, 5, (byte) 145, 17, 33, 34, (byte) 242 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 50, 4, 5, (byte) 145, 17, 33, 34, (byte) 242, 5, 0, 48, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5,
                6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, (byte) 161, 3, 31, 32, 33 };
    }

    @Test
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        RestoreDataResponseImpl prim = new RestoreDataResponseImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        ISDNAddressString hlrNumber = prim.getHlrNumber();
        assertTrue(hlrNumber.getAddress().equals("1112222"));

        assertNull(prim.getExtensionContainer());
        assertFalse(prim.getMsNotReachable());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        prim = new RestoreDataResponseImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        hlrNumber = prim.getHlrNumber();
        assertTrue(hlrNumber.getAddress().equals("1112222"));

        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(prim.getExtensionContainer()));
        assertTrue(prim.getMsNotReachable());

    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        ISDNAddressString hlrNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "1112222");
        RestoreDataResponseImpl prim = new RestoreDataResponseImpl(hlrNumber, false, null);
        // ISDNAddressString hlrNumber, boolean msNotReachable, MAPExtensionContainer extensionContainer

        AsnOutputStream asnOS = new AsnOutputStream();
        prim.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
//      ISDNAddressString getHlrNumber();
//      boolean getMsNotReachable();
//      MAPExtensionContainer getExtensionContainer();


        prim = new RestoreDataResponseImpl(hlrNumber, true, MAPExtensionContainerTest.GetTestExtensionContainer());

        asnOS = new AsnOutputStream();
        prim.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
