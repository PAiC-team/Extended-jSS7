
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.primitives.AppendFreeFormatData;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FreeFormatDataSMS;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.FCIBCCCAMELsequence1SMSImpl;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.FreeFormatDataSMSImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class FCIBCCCAMELsequence1SMSTest {
	
	public byte[] getData() {
		return new byte[] { -96, 13, -128, 8, 48, 6, -128, 1, 3, -118, 1, 1, -127, 1, 1 };
	};
	
	public byte[] getFreeFormatData() {
		return new byte[] { 48, 6, -128, 1, 3, -118, 1, 1 };
	};
	
	@Test(groups = { "functional.decode", "primitives" })
	public void testDecode() throws Exception {
		byte[] data = this.getData();
		AsnInputStream asn = new AsnInputStream(data);
		int tag = asn.readTag();
		FCIBCCCAMELsequence1SMSImpl prim = new FCIBCCCAMELsequence1SMSImpl();
		prim.decodeAll(asn);
		
		assertEquals(tag, FCIBCCCAMELsequence1SMSImpl._ID_FCIBCCCAMELsequence1);
		assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
		
		assertTrue(Arrays.equals(prim.getFreeFormatData().getData(), this.getFreeFormatData()));
		assertEquals(prim.getAppendFreeFormatData(), AppendFreeFormatData.append);
		
	}
	
	@Test(groups = { "functional.encode", "primitives" })
	public void testEncode() throws Exception {
		FreeFormatDataSMS freeFormatData = new FreeFormatDataSMSImpl(getFreeFormatData());
		FCIBCCCAMELsequence1SMSImpl prim = new FCIBCCCAMELsequence1SMSImpl(freeFormatData, AppendFreeFormatData.append);
		AsnOutputStream asn = new AsnOutputStream();
		prim.encodeAll(asn);
		
		assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
	}

	@Test(groups = {"functional.xml.serialize", "primitives"})
	public void testXMLSerialize() throws Exception {

		FreeFormatDataSMS freeFormatData = new FreeFormatDataSMSImpl(getFreeFormatData());
		FCIBCCCAMELsequence1SMSImpl original = new FCIBCCCAMELsequence1SMSImpl(freeFormatData, AppendFreeFormatData.append);

		// Writes the area to a file.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
		writer.setIndentation("\t");
		writer.write(original, "fciBCCCAMELsequence1SMS", FCIBCCCAMELsequence1SMSImpl.class);
		writer.close();

		byte[] rawData = baos.toByteArray();
		String serializedEvent = new String(rawData);

		System.out.println(serializedEvent);

		ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
		XMLObjectReader reader = XMLObjectReader.newInstance(bais);
		FCIBCCCAMELsequence1SMSImpl copy = reader.read("fciBCCCAMELsequence1SMS", FCIBCCCAMELsequence1SMSImpl.class);

		assertEquals(copy.getFreeFormatData().getData(), this.getFreeFormatData());
		assertEquals(copy.getAppendFreeFormatData(), AppendFreeFormatData.append);

		original = new FCIBCCCAMELsequence1SMSImpl(freeFormatData, null);

		// Writes the area to a file.
		baos = new ByteArrayOutputStream();
		writer = XMLObjectWriter.newInstance(baos);
		writer.setIndentation("\t");
		writer.write(original, "fciBCCCAMELsequence1SMS", FCIBCCCAMELsequence1SMSImpl.class);
		writer.close();

		rawData = baos.toByteArray();
		serializedEvent = new String(rawData);

		System.out.println(serializedEvent);

		bais = new ByteArrayInputStream(rawData);
		reader = XMLObjectReader.newInstance(bais);
		copy = reader.read("fciBCCCAMELsequence1SMS", FCIBCCCAMELsequence1SMSImpl.class);

		assertEquals(copy.getFreeFormatData().getData(), this.getFreeFormatData());
		assertNull(copy.getAppendFreeFormatData());
	}
}
