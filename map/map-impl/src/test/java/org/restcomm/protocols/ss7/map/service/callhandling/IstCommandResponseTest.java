
package org.restcomm.protocols.ss7.map.service.callhandling;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.callhandling.IstCommandResponseImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/*
 *
 * @author eva ogallar
 *
 */
public class IstCommandResponseTest {
    Logger logger = Logger.getLogger(IstCommandResponseTest.class);

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

    private byte[] getMAPParameterTestData() {
        return new byte[] { 48, 41, 48, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42,
                3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, (byte) 161, 3, 31, 32, 33};
    }


    public byte[] getData1() {
        return new byte[] { 48, 0 };
    }

    @Test(groups = { "functional.decode", "service.callhandling" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        IstCommandResponseImpl prim = new IstCommandResponseImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertNull(prim.getExtensionContainer());


        data = this.getMAPParameterTestData();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new IstCommandResponseImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(prim.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode", "service.callhandling" })
    public void testEncode() throws Exception {
        byte[] data = getData1();

        IstCommandResponseImpl sri = new IstCommandResponseImpl(null);

        AsnOutputStream asnOS = new AsnOutputStream();
        sri.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        assertEquals(data, encodedData);

        // extensionContainer
        MAPExtensionContainer extensionContainer = MAPExtensionContainerTest.GetTestExtensionContainer();

        IstCommandResponseImpl prim = new IstCommandResponseImpl(extensionContainer);

        asnOS = new AsnOutputStream();
        prim.encodeAll(asnOS);

        assertEquals(getMAPParameterTestData(), asnOS.toByteArray());
    }

    @Test(groups = { "functional.serialize", "service.callhandling" })
    public void testSerialization() throws Exception {

    }
}
