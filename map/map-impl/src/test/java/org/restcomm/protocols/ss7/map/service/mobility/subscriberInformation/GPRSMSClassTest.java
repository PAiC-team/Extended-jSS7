
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GPRSMSClassImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSNetworkCapabilityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSRadioAccessCapabilityImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class GPRSMSClassTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 11, -128, 3, 1, 2, 3, -127, 4, 11, 22, 33, 44 };
    }

    private byte[] getEncodedDataNetworkCapability() {
        return new byte[] { 1, 2, 3 };
    }

    private byte[] getEncodedDataRadioAccessCapability() {
        return new byte[] { 11, 22, 33, 44 };
    }

    @Test(groups = { "functional.decode", "subscriberInformation" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        GPRSMSClassImpl impl = new GPRSMSClassImpl();

        // TODO: fix a test

        // impl.decodeAll(asn);
        // assertEquals(tag, Tag.SEQUENCE);
        //
        // assertTrue(Arrays.equals(impl.getMSNetworkCapability().getData(), this.getEncodedDataNetworkCapability()));
        // assertTrue(Arrays.equals(impl.getMSRadioAccessCapability().getData(), this.getEncodedDataRadioAccessCapability()));
    }

    @Test(groups = { "functional.encode", "subscriberInformation" })
    public void testEncode() throws Exception {

        MSNetworkCapabilityImpl nc = new MSNetworkCapabilityImpl(this.getEncodedDataNetworkCapability());
        MSRadioAccessCapabilityImpl rac = new MSRadioAccessCapabilityImpl(this.getEncodedDataRadioAccessCapability());
        GPRSMSClassImpl impl = new GPRSMSClassImpl(nc, rac);
        AsnOutputStream asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS);
        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

    @Test(groups = { "functional.xml.serialize", "subscriberInformation" })
    public void testXMLSerialize() throws Exception {

        MSNetworkCapabilityImpl nc = new MSNetworkCapabilityImpl(this.getEncodedDataNetworkCapability());
        MSRadioAccessCapabilityImpl rac = new MSRadioAccessCapabilityImpl(this.getEncodedDataRadioAccessCapability());
        GPRSMSClassImpl original = new GPRSMSClassImpl(nc, rac);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "gprsMSClass", GPRSMSClassImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        GPRSMSClassImpl copy = reader.read("gprsMSClass", GPRSMSClassImpl.class);

        assertEquals(copy.getMSNetworkCapability().getData(), original.getMSNetworkCapability().getData());
        assertEquals(copy.getMSRadioAccessCapability().getData(), original.getMSRadioAccessCapability().getData());
    }

}
