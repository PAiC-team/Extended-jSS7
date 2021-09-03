
package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MAPParameterFactoryImpl;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.primitives.MAPPrivateExtension;
import org.restcomm.protocols.ss7.map.primitives.MAPPrivateExtensionImpl;
import org.restcomm.protocols.ss7.map.service.lsm.SLRArgExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.lsm.SLRArgPCSExtensionsImpl;
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
public class SLRArgExtensionContainerTest {

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
        return new byte[] { 48, 14, -96, 10, 48, 8, 6, 3, 42, 22, 33, 1, 2, 3, -95, 0 };
    }

    public long[] getDataOId() {
        return new long[] { 1, 2, 22, 33 };
    }

    public byte[] getDataPe() {
        return new byte[] { 1, 2, 3 };
    }

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode() throws Exception {
        byte[] data = getEncodedData();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);

        SLRArgExtensionContainerImpl imp = new SLRArgExtensionContainerImpl();
        imp.decodeAll(asn);

        assertEquals(imp.getPrivateExtensionList().size(), 1);
        assertTrue(Arrays.equals(imp.getPrivateExtensionList().get(0).getOId(), getDataOId()));
        assertTrue(Arrays.equals(imp.getPrivateExtensionList().get(0).getData(), getDataPe()));
        assertFalse(imp.getSlrArgPcsExtensions().getNaEsrkRequest());

    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {

        byte[] data = getEncodedData();

        ArrayList<MAPPrivateExtension> privateExtensionList = new ArrayList<MAPPrivateExtension>();
        MAPPrivateExtensionImpl pe = new MAPPrivateExtensionImpl(getDataOId(), getDataPe());
        privateExtensionList.add(pe);
        SLRArgPCSExtensionsImpl slrArgPcsExtensions = new SLRArgPCSExtensionsImpl(false);

        SLRArgExtensionContainerImpl imp = new SLRArgExtensionContainerImpl(privateExtensionList, slrArgPcsExtensions);
        // ArrayList<MAPPrivateExtension> privateExtensionList, SLRArgPCSExtensions slrArgPcsExtensions

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();

        assertTrue(Arrays.equals(data, encodedData));

    }
}
