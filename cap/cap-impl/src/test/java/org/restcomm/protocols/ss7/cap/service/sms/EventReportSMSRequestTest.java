
package org.restcomm.protocols.ss7.cap.service.sms;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiSms.OSmsFailureSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.api.EsiSms.OSmsFailureSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventSpecificInformationSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventTypeSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.MOSMSCause;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsTest;
import org.restcomm.protocols.ss7.cap.service.sms.EventReportSMSRequestImpl;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.EventSpecificInformationSMSImpl;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfoMessageType;
import org.restcomm.protocols.ss7.inap.primitives.MiscCallInfoImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class EventReportSMSRequestTest {

    public byte[] getData() {
        return new byte[] { 48, 35, -128, 1, 3, -95, 5, -96, 3, -128, 1, 2, -94, 3, -128, 1, 1, -86, 18, 48, 5, 2, 1,
                2, -127, 0, 48, 9, 2, 1, 3, 10, 1, 1, -127, 1, -1 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        EventReportSMSRequestImpl prim = new EventReportSMSRequestImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getEventTypeSMS(), EventTypeSMS.oSmsSubmission);
        EventSpecificInformationSMS eventSpecificInformationSMS = prim.getEventSpecificInformationSMS();
        assertNotNull(eventSpecificInformationSMS.getOSmsFailureSpecificInfo());
        OSmsFailureSpecificInfo oSmsFailureSpecificInfo = eventSpecificInformationSMS.getOSmsFailureSpecificInfo();
        assertEquals(oSmsFailureSpecificInfo.getFailureCause(), MOSMSCause.facilityNotSupported);
        assertNull(eventSpecificInformationSMS.getOSmsSubmissionSpecificInfo());
        assertNull(eventSpecificInformationSMS.getTSmsFailureSpecificInfo());
        assertNull(eventSpecificInformationSMS.getTSmsDeliverySpecificInfo());

        assertNotNull(prim.getMiscCallInfo());

        // extensions
        CAPExtensionsTest.checkTestCAPExtensions(prim.getExtensions());

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        EventTypeSMS eventTypeSMS = EventTypeSMS.oSmsSubmission;
        OSmsFailureSpecificInfo oSmsFailureSpecificInfo = new OSmsFailureSpecificInfoImpl(
                MOSMSCause.facilityNotSupported);
        EventSpecificInformationSMSImpl eventSpecificInformationSMS = new EventSpecificInformationSMSImpl(
                oSmsFailureSpecificInfo);
        MiscCallInfo miscCallInfo = new MiscCallInfoImpl(MiscCallInfoMessageType.notification, null);

        CAPExtensions extensions = CAPExtensionsTest.createTestCAPExtensions();

        EventReportSMSRequestImpl prim = new EventReportSMSRequestImpl(eventTypeSMS, eventSpecificInformationSMS,
                miscCallInfo, extensions);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
