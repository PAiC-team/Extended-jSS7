
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationQuintuplet;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationTriplet;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationQuintupletImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationSetListImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.AuthenticationTripletImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.QuintupletListImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.TripletListImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AuthenticationSetListTest {

    private byte[] getEncodedData_V3_tripl() {
        return new byte[] { (byte) 160, 36, 48, 34, 4, 16, 15, -2, 18, -92, -49, 43, -35, -71, -78, -98, 109, 83, -76, -87, 77,
                -128, 4, 4, -32, 82, -17, -14, 4, 8, 31, 72, -93, 97, 78, -17, -52, 0 };
    }

    private byte[] getEncodedData_V3_q() {
        return new byte[] { -95, 80, 48, 78, 4, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 2, 2, 2, 4, 16, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 16, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 16, 5,
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
    }

    private byte[] getEncodedData_V2_tripl() {
        return new byte[] { 48, 36, 48, 34, 4, 16, 15, -2, 18, -92, -49, 43, -35, -71, -78, -98, 109, 83, -76, -87, 77, -128,
                4, 4, -32, 82, -17, -14, 4, 8, 31, 72, -93, 97, 78, -17, -52, 0 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData_V3_tripl();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        AuthenticationSetListImpl asc = new AuthenticationSetListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, AuthenticationSetListImpl._TAG_tripletList);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(asc.getMapProtocolVersion(), 3);
        assertEquals(asc.getTripletList().getAuthenticationTriplets().size(), 1);
        assertNull(asc.getQuintupletList());

        rawData = getEncodedData_V2_tripl();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new AuthenticationSetListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(asc.getMapProtocolVersion(), 2);
        assertEquals(asc.getTripletList().getAuthenticationTriplets().size(), 1);
        assertNull(asc.getQuintupletList());

        rawData = getEncodedData_V3_q();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        asc = new AuthenticationSetListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, AuthenticationSetListImpl._TAG_quintupletList);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(asc.getMapProtocolVersion(), 3);
        assertNull(asc.getTripletList());
        assertEquals(asc.getQuintupletList().getAuthenticationQuintuplets().size(), 1);

    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        ArrayList<AuthenticationTriplet> ats = new ArrayList<AuthenticationTriplet>();
        AuthenticationTripletImpl at = new AuthenticationTripletImpl(AuthenticationTripletTest.getRandData(),
                AuthenticationTripletTest.getSresData(), AuthenticationTripletTest.getKcData());
        ats.add(at);
        TripletListImpl tl = new TripletListImpl(ats);
        AuthenticationSetListImpl asc = new AuthenticationSetListImpl(tl);
        asc.setMapProtocolVersion(3);

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData_V3_tripl();
        assertTrue(Arrays.equals(rawData, encodedData));

        ats = new ArrayList<AuthenticationTriplet>();
        at = new AuthenticationTripletImpl(AuthenticationTripletTest.getRandData(), AuthenticationTripletTest.getSresData(),
                AuthenticationTripletTest.getKcData());
        ats.add(at);
        tl = new TripletListImpl(ats);
        asc = new AuthenticationSetListImpl(tl);
        asc.setMapProtocolVersion(2);

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData_V2_tripl();
        assertTrue(Arrays.equals(rawData, encodedData));

        ArrayList<AuthenticationQuintuplet> qts = new ArrayList<AuthenticationQuintuplet>();
        AuthenticationQuintuplet qt = new AuthenticationQuintupletImpl(AuthenticationQuintupletTest.getRandData(),
                AuthenticationQuintupletTest.getXresData(), AuthenticationQuintupletTest.getCkData(),
                AuthenticationQuintupletTest.getIkData(), AuthenticationQuintupletTest.getAutnData());
        qts.add(qt);
        QuintupletListImpl ql = new QuintupletListImpl(qts);
        asc = new AuthenticationSetListImpl(ql);

        asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData_V3_q();
        assertTrue(Arrays.equals(rawData, encodedData));
    }
}
