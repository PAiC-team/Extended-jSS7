
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.primitives.IMEIImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.ADDInfoImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ADDInfoTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 12, -128, 8, 33, 67, 101, -121, 9, 33, 67, -11, -127, 0 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ADDInfoImpl asc = new ADDInfoImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getImeisv().getIMEI().equals("123456789012345"));
        assertTrue(asc.getSkipSubscriberDataUpdate());
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        IMEIImpl imeisv = new IMEIImpl("123456789012345");
        ADDInfoImpl asc = new ADDInfoImpl(imeisv, true);
        // IMEI imeisv, boolean skipSubscriberDataUpdate

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }
}
