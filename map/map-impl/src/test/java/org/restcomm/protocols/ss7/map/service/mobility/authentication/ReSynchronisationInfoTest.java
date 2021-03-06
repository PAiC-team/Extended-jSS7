
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.ReSynchronisationInfoImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ReSynchronisationInfoTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 34, 4, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 14, 2, 2, 2, 2, 3, 3, 3, 2, 2, 2,
                2, 3, 3, 3 };
    }

    static protected byte[] getRandData() {
        return new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    }

    static protected byte[] getAutsData() {
        return new byte[] { 2, 2, 2, 2, 3, 3, 3, 2, 2, 2, 2, 3, 3, 3 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ReSynchronisationInfoImpl asc = new ReSynchronisationInfoImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(Arrays.equals(asc.getRand(), getRandData()));
        assertTrue(Arrays.equals(asc.getAuts(), getAutsData()));

    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        ReSynchronisationInfoImpl asc = new ReSynchronisationInfoImpl(getRandData(), getAutsData());

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
