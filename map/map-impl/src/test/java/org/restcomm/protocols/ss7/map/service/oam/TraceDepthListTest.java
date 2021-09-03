
package org.restcomm.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepth;
import org.restcomm.protocols.ss7.map.service.oam.TraceDepthListImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceDepthListTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 30, (byte) 128, 1, 0, (byte) 129, 1, 1, (byte) 130, 1, 2, (byte) 131, 1, 0, (byte) 132, 1, 0, (byte) 133, 1, 1, (byte) 134, 1,
                1, (byte) 135, 1, 2, (byte) 136, 1, 2, (byte) 137, 1, 0 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        TraceDepthListImpl asc = new TraceDepthListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getMscSTraceDepth(), TraceDepth.minimum);
        assertEquals(asc.getMgwTraceDepth(), TraceDepth.medium);
        assertEquals(asc.getSgsnTraceDepth(), TraceDepth.maximum);
        assertEquals(asc.getGgsnTraceDepth(), TraceDepth.minimum);
        assertEquals(asc.getRncTraceDepth(), TraceDepth.minimum);
        assertEquals(asc.getBmscTraceDepth(), TraceDepth.medium);
        assertEquals(asc.getMmeTraceDepth(), TraceDepth.medium);
        assertEquals(asc.getSgwTraceDepth(), TraceDepth.maximum);
        assertEquals(asc.getPgwTraceDepth(), TraceDepth.maximum);
        assertEquals(asc.getEnbTraceDepth(), TraceDepth.minimum);

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        TraceDepthListImpl asc = new TraceDepthListImpl(TraceDepth.minimum, TraceDepth.medium, TraceDepth.maximum, TraceDepth.minimum, TraceDepth.minimum,
                TraceDepth.medium, TraceDepth.medium, TraceDepth.maximum, TraceDepth.maximum, TraceDepth.minimum);
//         TraceDepth mscSTraceDepth, TraceDepth mgwTraceDepth, TraceDepth sgsnTraceDepth, TraceDepth ggsnTraceDepth,
//        TraceDepth rncTraceDepth, TraceDepth bmscTraceDepth, TraceDepth mmeTraceDepth, TraceDepth sgwTraceDepth, TraceDepth pgwTraceDepth,
//        TraceDepth enbTraceDepth

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
