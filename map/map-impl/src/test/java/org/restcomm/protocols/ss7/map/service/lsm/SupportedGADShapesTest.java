package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.lsm.SupportedGADShapesImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author amit bhayani
 *
 */
public class SupportedGADShapesTest {

    private byte[] getEncodedData() {
        return new byte[] { (byte) 0x03, 0x02, 0x01, (byte) 0xfe };
    }

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

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode() throws Exception {
        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        SupportedGADShapesImpl supportedLCSCapabilityTest = new SupportedGADShapesImpl();
        supportedLCSCapabilityTest.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals((boolean) supportedLCSCapabilityTest.getEllipsoidArc(), true);
        assertEquals((boolean) supportedLCSCapabilityTest.getEllipsoidPoint(), true);
        assertEquals((boolean) supportedLCSCapabilityTest.getEllipsoidPointWithAltitude(), true);
        assertEquals((boolean) supportedLCSCapabilityTest.getEllipsoidPointWithAltitudeAndUncertaintyEllipsoid(), true);
        assertEquals((boolean) supportedLCSCapabilityTest.getEllipsoidPointWithUncertaintyCircle(), true);
        assertEquals((boolean) supportedLCSCapabilityTest.getEllipsoidPointWithUncertaintyEllipse(), true);
        assertEquals((boolean) supportedLCSCapabilityTest.getPolygon(), true);
    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {

        SupportedGADShapesImpl supportedLCSCapabilityTest = new SupportedGADShapesImpl(true, true, true, true, true, true, true);

        AsnOutputStream asnOS = new AsnOutputStream();
        supportedLCSCapabilityTest.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

    @Test(groups = { "functional.serialize", "service.lsm" })
    public void testSerialization() throws Exception {
        SupportedGADShapesImpl original = new SupportedGADShapesImpl(true, true, true, true, true, true, true);

        // serialize
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(original);
        oos.close();

        // deserialize
        byte[] pickled = out.toByteArray();
        InputStream in = new ByteArrayInputStream(pickled);
        ObjectInputStream ois = new ObjectInputStream(in);
        Object o = ois.readObject();
        SupportedGADShapesImpl copy = (SupportedGADShapesImpl) o;

        // test result
        assertEquals(copy, original);
    }
}
