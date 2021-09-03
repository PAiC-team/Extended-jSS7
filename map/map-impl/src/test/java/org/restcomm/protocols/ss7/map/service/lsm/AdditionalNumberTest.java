package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPParameterFactoryImpl;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.service.lsm.AdditionalNumberImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * TODO Self generated trace. Please test from real trace
 *
 * @author amit bhayani
 *
 */
public class AdditionalNumberTest {
    MAPParameterFactory MAPParameterFactory = null;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeTest
    public void setUp() {
        MAPParameterFactory = new MAPParameterFactoryImpl();
    }

    @AfterTest
    public void tearDown() {
    }

    public byte[] getEncodedMscNumber() {
        return new byte[] { (byte) 0x80, 0x05, (byte) 0x91, (byte) 0x55, 0x16, 0x09, 0x70 };
    }

    public byte[] getEncodedSgsnNumber() {
        return new byte[] { (byte) 0x81, 0x05, (byte) 0x91, (byte) 0x55, 0x16, 0x09, 0x70 };
    }

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode() throws Exception {
        byte[] data = getEncodedMscNumber();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        assertEquals(tag, 0);

        AdditionalNumberImpl addNum = new AdditionalNumberImpl();
        addNum.decodeAll(asn);
        assertNotNull(addNum.getMSCNumber());
        assertNull(addNum.getSGSNNumber());
        ISDNAddressString isdnAdd = addNum.getMSCNumber();
        assertEquals(isdnAdd.getAddressNature(), AddressNature.international_number);
        assertEquals(isdnAdd.getNumberingPlan(), NumberingPlan.ISDN);
        assertTrue(isdnAdd.getAddress().equals("55619007"));

        data = getEncodedSgsnNumber();

        asn = new AsnInputStream(data);
        tag = asn.readTag();
        assertEquals(tag, 1);

        addNum = new AdditionalNumberImpl();
        addNum.decodeAll(asn);
        assertNull(addNum.getMSCNumber());
        assertNotNull(addNum.getSGSNNumber());
        isdnAdd = addNum.getSGSNNumber();
        assertEquals(isdnAdd.getAddressNature(), AddressNature.international_number);
        assertEquals(isdnAdd.getNumberingPlan(), NumberingPlan.ISDN);
        assertTrue(isdnAdd.getAddress().equals("55619007"));
    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {
        byte[] data = getEncodedMscNumber();

        ISDNAddressString isdnAdd = MAPParameterFactory.createISDNAddressString(AddressNature.international_number,
                NumberingPlan.ISDN, "55619007");
        AdditionalNumber addNum = new AdditionalNumberImpl(isdnAdd, null);

        AsnOutputStream asnOS = new AsnOutputStream();
        ((AdditionalNumberImpl) addNum).encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));

        data = getEncodedSgsnNumber();

        addNum = new AdditionalNumberImpl(null, isdnAdd);

        asnOS = new AsnOutputStream();
        ((AdditionalNumberImpl) addNum).encodeAll(asnOS);

        encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));
    }

    @Test(groups = { "functional.serialize", "service.lsm" })
    public void testSerialization() throws Exception {
        ISDNAddressString isdnAdd = MAPParameterFactory.createISDNAddressString(AddressNature.international_number,
                NumberingPlan.ISDN, "55619007");
        AdditionalNumber original = new AdditionalNumberImpl(isdnAdd, null);
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
        AdditionalNumberImpl copy = (AdditionalNumberImpl) o;

        // test result
        assertEquals(copy.getMSCNumber(), original.getMSCNumber());

    }
}
