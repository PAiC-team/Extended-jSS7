
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.SMSAddressStringImpl;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SMSAddressStringTest {

    public byte[] getData() {
        return new byte[] { 4, 9, -111, 33, 67, 101, -121, 25, 50, 84, 118 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 11, (byte) 209, (byte) 208, (byte) 178, (byte) 188, 60, (byte) 167, (byte) 203, (byte) 223, (byte) 233, 117, 24 };
    };

	@Test(groups = { "functional.decode", "primitives" })
	public void testDecode() throws Exception {
		byte[] data = this.getData();
		AsnInputStream asn = new AsnInputStream(data);
		int tag = asn.readTag();
		SMSAddressStringImpl prim = new SMSAddressStringImpl();
		prim.decodeAll(asn);
		
		assertEquals(tag, Tag.STRING_OCTET);
		assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
		
		assertEquals(prim.getAddressNature(), AddressNature.international_number);
        assertEquals(prim.getNumberingPlan(), NumberingPlan.ISDN);
        assertEquals(prim.getAddress(), "1234567891234567");


		data = this.getData2();
        asn = new AsnInputStream(data);
        tag = asn.readTag();
        prim = new SMSAddressStringImpl();
        prim.decodeAll(asn);
        
        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        
        assertEquals(prim.getAddressNature(), AddressNature.reserved);
        assertEquals(prim.getNumberingPlan(), NumberingPlan.ISDN);
        assertEquals(prim.getAddress(), "Perestroika");
	}
	
	@Test(groups = { "functional.encode", "primitives" })
	public void testEncode() throws Exception {
        SMSAddressStringImpl prim = new SMSAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, "1234567891234567");
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));


        prim = new SMSAddressStringImpl(AddressNature.reserved, NumberingPlan.ISDN, "Perestroika");
        asn = new AsnOutputStream();
        prim.encodeAll(asn);
        
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
	}
	
}
