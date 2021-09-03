
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.EpcAv;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.EpcAvImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.EpsAuthenticationSetListImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class EpsAuthenticationSetListTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, -127, -98, 48, 76, 4, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 2, 2, 2, 4,
                16, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 32, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
                4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 48, 78, 4, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4,
                6, 22, 22, 22, 22, 22, 22, 4, 16, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 32, 4, 4, 4, 4, 4, 4, 4,
                4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
    }

    static protected byte[] getXresData() {
        return new byte[] { 22, 22, 22, 22, 22, 22 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        EpsAuthenticationSetListImpl asc = new EpsAuthenticationSetListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        ArrayList<EpcAv> epcAvs = asc.getEpcAv();
        assertEquals(epcAvs.size(), 2);

        assertTrue(Arrays.equals(epcAvs.get(0).getRand(), EpcAvTest.getRandData()));
        assertTrue(Arrays.equals(epcAvs.get(0).getXres(), EpcAvTest.getXresData()));
        assertTrue(Arrays.equals(epcAvs.get(0).getAutn(), EpcAvTest.getAutnData()));
        assertTrue(Arrays.equals(epcAvs.get(0).getKasme(), EpcAvTest.getKasmeData()));

        assertTrue(Arrays.equals(epcAvs.get(1).getRand(), EpcAvTest.getRandData()));
        assertTrue(Arrays.equals(epcAvs.get(1).getXres(), getXresData()));
        assertTrue(Arrays.equals(epcAvs.get(1).getAutn(), EpcAvTest.getAutnData()));
        assertTrue(Arrays.equals(epcAvs.get(1).getKasme(), EpcAvTest.getKasmeData()));
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        EpcAvImpl d1 = new EpcAvImpl(EpcAvTest.getRandData(), EpcAvTest.getXresData(), EpcAvTest.getAutnData(),
                EpcAvTest.getKasmeData(), null);
        EpcAvImpl d2 = new EpcAvImpl(EpcAvTest.getRandData(), getXresData(), EpcAvTest.getAutnData(), EpcAvTest.getKasmeData(),
                null);
        ArrayList<EpcAv> arr = new ArrayList<EpcAv>();
        arr.add(d1);
        arr.add(d2);
        EpsAuthenticationSetListImpl asc = new EpsAuthenticationSetListImpl(arr);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }
}
