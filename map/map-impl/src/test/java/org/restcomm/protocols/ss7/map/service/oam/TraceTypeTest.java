
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.oam.BssRecordType;
import org.restcomm.protocols.ss7.map.api.service.oam.HlrRecordType;
import org.restcomm.protocols.ss7.map.api.service.oam.MscRecordType;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceTypeInvokingEvent;
import org.restcomm.protocols.ss7.map.service.oam.TraceTypeImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceTypeTest {

    private byte[] getEncodedData() {
        return new byte[] { 4, 1, 39 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 4, 1, (byte) 133 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        TraceTypeImpl asc = new TraceTypeImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getBssRecordType(), BssRecordType.Radio);
        assertEquals(asc.getMscRecordType(), MscRecordType.Detailed);
        assertEquals(asc.getTraceTypeInvokingEvent(), TraceTypeInvokingEvent.InvokingEvent_3);
        assertFalse(asc.isPriorityIndication());


        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new TraceTypeImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getHlrRecordType(), HlrRecordType.Detailed);
        assertEquals(asc.getTraceTypeInvokingEvent(), TraceTypeInvokingEvent.InvokingEvent_1);
        assertTrue(asc.isPriorityIndication());
    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        TraceTypeImpl asc = new TraceTypeImpl(BssRecordType.Radio, MscRecordType.Detailed, TraceTypeInvokingEvent.InvokingEvent_3, false);
//        BssRecordType bssRecordType, MscRecordType mscRecordType, TraceTypeInvokingEvent traceTypeInvokingEvent, boolean priorityIndication

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        asc = new TraceTypeImpl(HlrRecordType.Detailed, TraceTypeInvokingEvent.InvokingEvent_1, true);
//        HlrRecordType hlrRecordType, TraceTypeInvokingEvent traceTypeInvokingEvent, boolean priorityIndication

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
