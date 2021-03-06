
package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MAPParameterFactoryImpl;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.lsm.SubscriberLocationReportResponseImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SubscriberLocationReportResponseTest {

    MAPParameterFactory MAPParameterFactory = new MAPParameterFactoryImpl();

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeTest
    public void setUp() {
    }

    @AfterTest
    public void tearDown() {
    }

    public byte[] getEncodedData() {
        return new byte[] { 48, 51, 48, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11,
                6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33, -128, 3, -111, 34, 34, -127, 3, -111, 17, 17 };
    }

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode() throws Exception {
        byte[] data = getEncodedData();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);

        SubscriberLocationReportResponseImpl imp = new SubscriberLocationReportResponseImpl();
        imp.decodeAll(asn);

        assertTrue(imp.getNaESRD().getAddress().equals("1111"));
        assertTrue(imp.getNaESRK().getAddress().equals("2222"));
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(imp.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {

        byte[] data = getEncodedData();

        ISDNAddressStringImpl naEsrd = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "1111");
        ISDNAddressStringImpl naEsrk = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "2222");

        SubscriberLocationReportResponseImpl imp = new SubscriberLocationReportResponseImpl(naEsrd, naEsrk,
                MAPExtensionContainerTest.GetTestExtensionContainer());
        // ISDNAddressString naEsrd, ISDNAddressString naEsrk, MAPExtensionContainer extensionContainer

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));

    }

}
