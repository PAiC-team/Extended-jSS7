
package org.restcomm.protocols.ss7.map.service.sms;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.AlertReason;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.map.service.sms.ReadyForSMRequestImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ReadyForSMRequestTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 12, (byte) 128, 7, 17, 17, 33, 34, 34, 51, (byte) 243, 10, 1, 1 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { 48, 57, (byte) 128, 7, 17, 17, 33, 34, 34, 51, (byte) 243, 10, 1, 1, 5, 0, 48, 39, (byte) 160, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12,
                13, 14, 15, 48, 5, 6, 3, 42, 3, 6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, (byte) 161, 3, 31, 32, 33, (byte) 129, 0 };
    }

    @Test(groups = { "functional.decode", "service.sms" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        ReadyForSMRequestImpl impl = new ReadyForSMRequestImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getImsi().getData(), "1111122222333");
        assertEquals(impl.getAlertReason(), AlertReason.memoryAvailable);

        assertFalse(impl.getAlertReasonIndicator());
        assertNull(impl.getExtensionContainer());
        assertFalse(impl.getAdditionalAlertReasonIndicator());


        rawData = getEncodedData2();

        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        impl = new ReadyForSMRequestImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getImsi().getData(), "1111122222333");
        assertEquals(impl.getAlertReason(), AlertReason.memoryAvailable);

        assertTrue(impl.getAlertReasonIndicator());
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(impl.getExtensionContainer()));
        assertTrue(impl.getAdditionalAlertReasonIndicator());
    }

    @Test(groups = { "functional.encode", "service.sms" })
    public void testEncode() throws Exception {

        IMSI imsi = new IMSIImpl("1111122222333");
        ReadyForSMRequestImpl impl = new ReadyForSMRequestImpl(imsi, AlertReason.memoryAvailable, false, null, false);
//        IMSI imsi, AlertReason alertReason, boolean alertReasonIndicator, MAPExtensionContainer extensionContainer,
//        boolean additionalAlertReasonIndicator

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));


        impl = new ReadyForSMRequestImpl(imsi, AlertReason.memoryAvailable, true, MAPExtensionContainerTest.GetTestExtensionContainer(), true);

        asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
