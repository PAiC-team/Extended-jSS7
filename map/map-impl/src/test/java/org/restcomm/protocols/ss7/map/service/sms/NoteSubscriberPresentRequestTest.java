
package org.restcomm.protocols.ss7.map.service.sms;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.service.sms.NoteSubscriberPresentRequestImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class NoteSubscriberPresentRequestTest {

    private byte[] getEncodedData() {
        return new byte[] { 4, 7, 17, 17, 33, 34, 34, 51, (byte) 243 };
    }

    @Test(groups = { "functional.decode", "service.sms" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        NoteSubscriberPresentRequestImpl impl = new NoteSubscriberPresentRequestImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getIMSI().getData(), "1111122222333");
    }

    @Test(groups = { "functional.encode", "service.sms" })
    public void testEncode() throws Exception {

        IMSI imsi = new IMSIImpl("1111122222333");
        NoteSubscriberPresentRequestImpl impl = new NoteSubscriberPresentRequestImpl(imsi);

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
