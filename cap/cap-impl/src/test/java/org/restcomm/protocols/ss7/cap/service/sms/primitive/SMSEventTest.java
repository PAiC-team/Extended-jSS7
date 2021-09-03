
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.primitives.MonitorMode;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventTypeSMS;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.SMSEventImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SMSEventTest {
	
	public byte[] getData() {
		return new byte[] { 48, 6, -128, 1, 3, -127, 1, 1 };
	};
	
	@Test(groups = { "functional.decode", "primitives" })
	public void testDecode() throws Exception {
		byte[] data = this.getData();
		AsnInputStream asn = new AsnInputStream(data);
		int tag = asn.readTag();
		SMSEventImpl prim = new SMSEventImpl();
		prim.decodeAll(asn);
		
		assertEquals(tag, Tag.SEQUENCE);
		assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
		
		assertEquals(prim.getEventTypeSMS(), EventTypeSMS.oSmsSubmission);
		assertEquals(prim.getMonitorMode(),  MonitorMode.notifyAndContinue);
		
	}
	
	@Test(groups = { "functional.encode", "primitives" })
	public void testEncode() throws Exception {
		SMSEventImpl prim = new SMSEventImpl(EventTypeSMS.oSmsSubmission, MonitorMode.notifyAndContinue);
		AsnOutputStream asn = new AsnOutputStream();
		prim.encodeAll(asn);
	
		assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
	}
	
}
