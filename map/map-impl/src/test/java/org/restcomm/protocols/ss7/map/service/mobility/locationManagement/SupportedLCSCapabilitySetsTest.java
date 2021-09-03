package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * TODO : Self generated trace. Get real ones
 *
 * @author amit bhayani
 *
 */
public class SupportedLCSCapabilitySetsTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 2, 4, 64 };
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
        // byte[] rawData = getEncodedData();
        // AsnInputStream asn = new AsnInputStream(rawData);
        //
        // int tag = asn.readTag();
        // SupportedLCSCapabilitySetsImpl supportedLCSCapabilityTest = new SupportedLCSCapabilitySetsImpl();
        // supportedLCSCapabilityTest.decodeAll(asn);
        //
        // assertEquals( tag,Tag.STRING_BIT);
        // assertEquals( asn.getTagClass(),Tag.CLASS_UNIVERSAL);
        //
        // assertEquals( (boolean)supportedLCSCapabilityTest.getCapabilitySetRelease98_99(),false);
        // assertEquals( (boolean)supportedLCSCapabilityTest.getCapabilitySetRelease4(),true);
        // assertEquals( (boolean)supportedLCSCapabilityTest.getCapabilitySetRelease5(),false);
        // assertEquals( (boolean)supportedLCSCapabilityTest.getCapabilitySetRelease6(),false);
        // assertEquals( (boolean)supportedLCSCapabilityTest.getCapabilitySetRelease7(),false);
    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {

        // SupportedLCSCapabilitySetsImpl supportedLCSCapabilityTest = new SupportedLCSCapabilitySetsImpl(false, true, false,
        // false, false);
        //
        // AsnOutputStream asnOS = new AsnOutputStream();
        // supportedLCSCapabilityTest.encodeAll(asnOS);
        //
        // byte[] encodedData = asnOS.toByteArray();
        // byte[] rawData = getEncodedData();
        // assertTrue( Arrays.equals(rawData,encodedData));

    }

    @Test(groups = { "functional.serialize", "service.lsm" })
    public void testSerialization() throws Exception {
        // SupportedLCSCapabilitySetsImpl original = new SupportedLCSCapabilitySetsImpl(false, true, false, false, false);
        //
        // // serialize
        // ByteArrayOutputStream out = new ByteArrayOutputStream();
        // ObjectOutputStream oos = new ObjectOutputStream(out);
        // oos.writeObject(original);
        // oos.close();
        //
        // // deserialize
        // byte[] pickled = out.toByteArray();
        // InputStream in = new ByteArrayInputStream(pickled);
        // ObjectInputStream ois = new ObjectInputStream(in);
        // Object o = ois.readObject();
        // SupportedLCSCapabilitySetsImpl copy = (SupportedLCSCapabilitySetsImpl) o;
        //
        // //test result
        // assertEquals(copy.getCapabilitySetRelease98_99(), original.getCapabilitySetRelease98_99());
        // assertEquals(copy.getCapabilitySetRelease4(), original.getCapabilitySetRelease4());
        // assertEquals(copy.getCapabilitySetRelease5(), original.getCapabilitySetRelease5());
        // assertEquals(copy.getCapabilitySetRelease6(), original.getCapabilitySetRelease6());
        // assertEquals(copy.getCapabilitySetRelease7(), original.getCapabilitySetRelease6());

    }
}
