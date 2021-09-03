
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.oam.ActivateTraceModeResponseImpl_Base;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ActivateTraceModeResponseTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 43, (byte) 160, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5,
                21, 22, 23, 24, 25, 26, (byte) 161, 3, 31, 32, 33, (byte) 129, 0 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ActivateTraceModeResponseImpl_Base asc = new ActivateTraceModeResponseImpl_Base();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(asc.getExtensionContainer()));
        assertTrue(asc.getTraceSupportIndicator());
    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        ActivateTraceModeResponseImpl_Base asc = new ActivateTraceModeResponseImpl_Base(MAPExtensionContainerTest.GetTestExtensionContainer(), true);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
