
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.primitives.ProtocolId;
import org.restcomm.protocols.ss7.map.api.primitives.SignalInfo;
import org.restcomm.protocols.ss7.map.primitives.ExternalSignalInfoImpl;
import org.restcomm.protocols.ss7.map.primitives.SignalInfoImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
 *
 * @author cristian veliscu
 *
 */
public class ExternalSignalInfoTest {
    Logger logger = Logger.getLogger(ExternalSignalInfoTest.class);

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

    @Test(groups = { "functional.decode", "service.callhandling" })
    public void testDecode() throws Exception {
        byte[] data = new byte[] { 48, 9, 10, 1, 2, 4, 4, 10, 20, 30, 40 };
        byte[] data_ = new byte[] { 10, 20, 30, 40 };

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        ExternalSignalInfoImpl extSignalInfo = new ExternalSignalInfoImpl();
        extSignalInfo.decodeAll(asn);

        ProtocolId protocolId = extSignalInfo.getProtocolId();
        byte[] signalInfo = extSignalInfo.getSignalInfo().getData();

        assertTrue(Arrays.equals(data_, signalInfo));
        assertNotNull(protocolId);
        assertTrue(protocolId == ProtocolId.gsm_0806);
    }

    @Test(groups = { "functional.encode", "service.callhandling" })
    public void testEncode() throws Exception {
        byte[] data = new byte[] { 48, 9, 10, 1, 2, 4, 4, 10, 20, 30, 40 };
        byte[] data_ = new byte[] { 10, 20, 30, 40 };

        SignalInfo signalInfo = new SignalInfoImpl(data_);
        ProtocolId protocolId = ProtocolId.gsm_0806;
        ExternalSignalInfoImpl extSignalInfo = new ExternalSignalInfoImpl(signalInfo, protocolId, null);

        AsnOutputStream asnOS = new AsnOutputStream();
        extSignalInfo.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        assertTrue(Arrays.equals(data, encodedData));
    }

    @Test(groups = { "functional.serialize", "service.callhandling" })
    public void testSerialization() throws Exception {

    }
}
