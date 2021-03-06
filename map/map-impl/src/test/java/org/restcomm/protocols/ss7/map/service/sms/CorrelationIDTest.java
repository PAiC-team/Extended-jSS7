
package org.restcomm.protocols.ss7.map.service.sms;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
*
* @author kostiantyn nosach
*
*/

public class CorrelationIDTest {

    private byte[] getEncodedData() {
        return new byte[] {32, 7, -128, 5, 17, 17, 33, 34, 34};
    }

    private byte[] getEncodedDataFull() {
        return new byte[] {32, 61, -128, 5, 17, 17, 33, 34, 34, -127, 27, 115, 105, 112, 58, 107, 111, 110, 
                115, 116, 97, 110, 116, 105, 110, 64, 116, 101, 108, 101, 115, 116, 97, 120, 46, 99, 111, 109, 
                -126, 23, 115, 105, 112, 58, 110, 111, 115, 97, 99, 104, 64, 116, 101, 108, 101, 115, 116, 97, 120, 46, 99, 111, 109};
    }

    @Test(groups = { "functional.decode", "service.sms" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        CorrelationIDImpl correlationId = new CorrelationIDImpl();
        correlationId.decodeAll(asn);

        assertEquals(tag, 0);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertTrue(correlationId.getHlrId().getData().equals("1111122222"));

        rawData = getEncodedDataFull();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        correlationId = new CorrelationIDImpl();
        correlationId.decodeAll(asn);

        assertEquals(tag, 0);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(correlationId.getHlrId().getData().equals("1111122222"));
        assertEquals(new String (correlationId.getSipUriA().getData()), "sip:konstantin@telestax.com");
        assertEquals(new String (correlationId.getSipUriB().getData()), "sip:nosach@telestax.com");
    }

    @Test(groups = { "functional.encode", "service.sms" })
    public void testEncode() throws Exception {

        IMSIImpl hlrId = new IMSIImpl("1111122222");

        CorrelationIDImpl cid = new CorrelationIDImpl(hlrId, null, null);

        AsnOutputStream asnOS = new AsnOutputStream();
        cid.encodeAll(asnOS, Tag.CLASS_UNIVERSAL, 0);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

        byte [] dataSipA = "sip:konstantin@telestax.com".getBytes();
        byte [] dataSipB = "sip:nosach@telestax.com".getBytes();

        SipUriImpl sipA = new SipUriImpl(dataSipA);
        SipUriImpl sipB = new SipUriImpl(dataSipB);
        
        cid = new CorrelationIDImpl(hlrId, sipA, sipB);
        asnOS.reset();
        cid.encodeAll(asnOS, Tag.CLASS_UNIVERSAL, 0);
        
        encodedData = asnOS.toByteArray();
        rawData = getEncodedDataFull();
        assertTrue(Arrays.equals(rawData, encodedData));

    }
}

