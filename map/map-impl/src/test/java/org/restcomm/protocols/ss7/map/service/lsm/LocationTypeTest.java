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
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredLocationEventType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationEstimateType;
import org.restcomm.protocols.ss7.map.service.lsm.DeferredLocationEventTypeImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LocationTypeImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author amit bhayani
 *
 */
public class LocationTypeTest {

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
        byte[] data = new byte[] { 0x30, 0x03, (byte) 0x80, 0x01, 0x00 };
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        LocationTypeImpl locType = new LocationTypeImpl();
        locType.decodeAll(asn);

        assertNotNull(locType.getLocationEstimateType());
        assertEquals(locType.getLocationEstimateType(), LocationEstimateType.currentLocation);
        assertNull(locType.getDeferredLocationEventType());

    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {
        byte[] data = new byte[] { 0x30, 0x03, (byte) 0x80, 0x01, 0x00 };

        LocationTypeImpl locType = new LocationTypeImpl(LocationEstimateType.currentLocation, null);
        AsnOutputStream asnOS = new AsnOutputStream();
        locType.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));

    }

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode1() throws Exception {
        byte[] data = new byte[] { 0x30, 0x07, (byte) 0x80, 0x01, 0x00, (byte) 0x81, 0x02, 0x04, (byte) -16 };
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        LocationTypeImpl locType = new LocationTypeImpl();
        locType.decodeAll(asn);

        assertNotNull(locType.getLocationEstimateType());
        assertEquals(locType.getLocationEstimateType(), LocationEstimateType.currentLocation);
        assertNotNull(locType.getDeferredLocationEventType());

        assertNotNull(locType.getDeferredLocationEventType());
        assertTrue(locType.getDeferredLocationEventType().getEnteringIntoArea());
        assertTrue(locType.getDeferredLocationEventType().getMsAvailable());
        assertTrue(locType.getDeferredLocationEventType().getLeavingFromArea());
        assertTrue(locType.getDeferredLocationEventType().getBeingInsideArea());

    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode1() throws Exception {
        byte[] data = new byte[] { 0x30, 0x07, (byte) 0x80, 0x01, 0x00, (byte) 0x81, 0x02, 0x04, (byte) -16 };

        DeferredLocationEventType deferredLocationEventType = new DeferredLocationEventTypeImpl(true, true, true, true, false);

        LocationTypeImpl locType = new LocationTypeImpl(LocationEstimateType.currentLocation, deferredLocationEventType);

        AsnOutputStream asnOS = new AsnOutputStream();
        locType.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));
    }

    @Test(groups = { "functional.serialize", "service.lsm" })
    public void testSerialization() throws Exception {
        DeferredLocationEventType deferredLocationEventType = new DeferredLocationEventTypeImpl(true, true, true, true, false);

        LocationTypeImpl original = new LocationTypeImpl(LocationEstimateType.currentLocation, deferredLocationEventType);

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
        LocationTypeImpl copy = (LocationTypeImpl) o;

        // test result
        assertEquals(copy, original);
    }

}
