
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationQuintuplet;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationQuintupletImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationSetListImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.QuintupletListImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class QuintupletListTest {

    private byte[] getEncodedData() {
        return new byte[] { -95, 80, 48, 78, 4, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 2, 2, 2, 4, 16, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 16, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 16, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
    }

    @Test
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        QuintupletListImpl asc = new QuintupletListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, AuthenticationSetListImpl._TAG_quintupletList);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(asc.getAuthenticationQuintuplets().size(), 1);

        assertTrue(Arrays.equals(asc.getAuthenticationQuintuplets().get(0).getRand(),
                AuthenticationQuintupletTest.getRandData()));
        assertTrue(Arrays.equals(asc.getAuthenticationQuintuplets().get(0).getXres(),
                AuthenticationQuintupletTest.getXresData()));
        assertTrue(Arrays.equals(asc.getAuthenticationQuintuplets().get(0).getCk(), AuthenticationQuintupletTest.getCkData()));
        assertTrue(Arrays.equals(asc.getAuthenticationQuintuplets().get(0).getIk(), AuthenticationQuintupletTest.getIkData()));
        assertTrue(Arrays.equals(asc.getAuthenticationQuintuplets().get(0).getAutn(),
                AuthenticationQuintupletTest.getAutnData()));

    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        AuthenticationQuintupletImpl d1 = new AuthenticationQuintupletImpl(AuthenticationQuintupletTest.getRandData(),
                AuthenticationQuintupletTest.getXresData(), AuthenticationQuintupletTest.getCkData(),
                AuthenticationQuintupletTest.getIkData(), AuthenticationQuintupletTest.getAutnData());
        ArrayList<AuthenticationQuintuplet> arr = new ArrayList<AuthenticationQuintuplet>();
        arr.add(d1);
        QuintupletListImpl asc = new QuintupletListImpl(arr);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, AuthenticationSetListImpl._TAG_quintupletList);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }
}
