
package org.restcomm.protocols.ss7.cap.service.sms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.primitives.AppendFreeFormatData;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FCIBCCCAMELsequence1SMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FreeFormatDataSMS;
import org.restcomm.protocols.ss7.cap.service.sms.FurnishChargingInformationSMSRequestImpl;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.FCIBCCCAMELsequence1SMSImpl;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.FreeFormatDataSMSImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 
 * @author Lasith Waruna Perera
 * 
 */
public class FurnishChargingInformationSMSRequestTest {

    public byte[] getData() {
        return new byte[] { 4, 15, -96, 13, -128, 8, 48, 6, -128, 1, 3, -118, 1, 1, -127, 1, 1 };
//        return new byte[] { 4, 17, 48, 15, -96, 13, -128, 8, 48, 6, -128, 1, 3, -118, 1, 1, -127, 1, 1 };
    };

    public byte[] getFreeFormatData() {
        return new byte[] { 48, 6, -128, 1, 3, -118, 1, 1 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        FurnishChargingInformationSMSRequestImpl prim = new FurnishChargingInformationSMSRequestImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        FCIBCCCAMELsequence1SMS fcIBCCCAMELsequence1 = prim.getFCIBCCCAMELsequence1();
        assertNotNull(fcIBCCCAMELsequence1);
        assertTrue(Arrays.equals(fcIBCCCAMELsequence1.getFreeFormatData().getData(), this.getFreeFormatData()));
        assertEquals(fcIBCCCAMELsequence1.getAppendFreeFormatData(), AppendFreeFormatData.append);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        FreeFormatDataSMS freeFormatData = new FreeFormatDataSMSImpl(getFreeFormatData());
        FCIBCCCAMELsequence1SMSImpl fcIBCCCAMELsequence1 = new FCIBCCCAMELsequence1SMSImpl(freeFormatData,
                AppendFreeFormatData.append);

        FurnishChargingInformationSMSRequestImpl prim = new FurnishChargingInformationSMSRequestImpl(fcIBCCCAMELsequence1);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }
    @Test(groups = {"functional.xml.serialize", "primitives"})
    public void testXMLSerialize() throws Exception {

        FreeFormatDataSMS freeFormatData = new FreeFormatDataSMSImpl(getFreeFormatData());
        FCIBCCCAMELsequence1SMSImpl fcIBCCCAMELsequence1 = new FCIBCCCAMELsequence1SMSImpl(freeFormatData, AppendFreeFormatData.append);
        FurnishChargingInformationSMSRequestImpl original = new FurnishChargingInformationSMSRequestImpl(fcIBCCCAMELsequence1);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "furnishChargingInformationSMSRequest", FurnishChargingInformationSMSRequestImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        FurnishChargingInformationSMSRequestImpl copy = reader.read("furnishChargingInformationSMSRequest", FurnishChargingInformationSMSRequestImpl.class);

        assertNotNull(copy.getFCIBCCCAMELsequence1());
        assertTrue(Arrays.equals(copy.getFCIBCCCAMELsequence1().getFreeFormatData().getData(), this.getFreeFormatData()));
        assertEquals(copy.getFCIBCCCAMELsequence1().getAppendFreeFormatData(), AppendFreeFormatData.append);
    }
}
