
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GuidanceInfo;
import org.restcomm.protocols.ss7.map.service.supplementary.GetPasswordRequestImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class GetPasswordRequestTest {

    private byte[] getEncodedData() {
        return new byte[] { 10, 1, 2 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.ENUMERATED);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        GetPasswordRequestImpl impl = new GetPasswordRequestImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getGuidanceInfo(), GuidanceInfo.enterNewPWAgain);


    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        GetPasswordRequestImpl impl = new GetPasswordRequestImpl(GuidanceInfo.enterNewPWAgain);
        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


    }

}
