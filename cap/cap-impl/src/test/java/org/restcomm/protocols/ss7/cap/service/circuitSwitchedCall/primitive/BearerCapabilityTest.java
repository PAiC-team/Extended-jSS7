
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.isup.BearerCapImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.BearerCapabilityImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.UserServiceInformationImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.UserServiceInformation;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class BearerCapabilityTest {

    public byte[] getData1() {
        return new byte[] { (byte) 128, 3, (byte) 128, (byte) 144, (byte) 163 };
    }

    public byte[] getIntData1() {
        return new byte[] { (byte) 128, (byte) 144, (byte) 163 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        BearerCapabilityImpl elem = new BearerCapabilityImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertTrue(Arrays.equals(elem.getBearerCap().getData(), this.getIntData1()));
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        BearerCapImpl bc = new BearerCapImpl(this.getIntData1());
        BearerCapabilityImpl elem = new BearerCapabilityImpl(bc);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall.primitive" })
    public void testXMLSerialize() throws Exception {

        UserServiceInformationImpl original0 = new UserServiceInformationImpl();
        original0.setCodingStandart(UserServiceInformation._CS_INTERNATIONAL);
        original0.setInformationTransferCapability(UserServiceInformation._ITS_VIDEO);
        original0.setTransferMode(UserServiceInformation._TM_PACKET);
        original0.setInformationTransferRate(UserServiceInformation._ITR_64x2);

        BearerCapImpl bc = new BearerCapImpl(original0);
        BearerCapabilityImpl original = new BearerCapabilityImpl(bc);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "bearerCapability", BearerCapabilityImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        BearerCapabilityImpl copy = reader.read("bearerCapability", BearerCapabilityImpl.class);

        assertEquals(copy.getBearerCap().getUserServiceInformation().getCodingStandart(), original.getBearerCap()
                .getUserServiceInformation().getCodingStandart());
        assertEquals(copy.getBearerCap().getUserServiceInformation().getInformationTransferCapability(), original
                .getBearerCap().getUserServiceInformation().getInformationTransferCapability());

    }
}
