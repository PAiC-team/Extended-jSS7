
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.service.supplementary.RegisterSSResponseImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSDataImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSInfoImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class RegisterSSResponseTest {

    private byte[] getEncodedData1() {
        return new byte[] { (byte) 163, 3, 4, 1, 18 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, SSInfoImpl._TAG_ssData);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        RegisterSSResponseImpl impl = new RegisterSSResponseImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsInfo().getSsData().getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.clir);
        assertNull(impl.getSsInfo().getCallBarringInfo());
        assertNull(impl.getSsInfo().getForwardingInfo());

    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

        SSCode ssCode = new SSCodeImpl(SupplementaryCodeValue.clir);
        SSDataImpl ssData = new SSDataImpl(ssCode, null, null, null, null, null);
        SSInfoImpl ssInfo = new SSInfoImpl(ssData);

        RegisterSSResponseImpl impl = new RegisterSSResponseImpl(ssInfo);

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
