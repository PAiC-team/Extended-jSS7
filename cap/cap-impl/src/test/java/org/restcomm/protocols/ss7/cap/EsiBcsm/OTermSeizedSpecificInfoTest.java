
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiBcsm.OTermSeizedSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSMImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class OTermSeizedSpecificInfoTest {

    public byte[] getData1() {
        return new byte[] { (byte) 173, 7, (byte) 191, 50, 4, 2, 2, 0, (byte) 200 };
    }

    @Test(groups = { "functional.decode", "EsiBcsm" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        OTermSeizedSpecificInfoImpl elem = new OTermSeizedSpecificInfoImpl();
        int tag = ais.readTag();
        assertEquals(tag, EventSpecificInformationBCSMImpl._ID_oTermSeizedSpecificInfo);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        elem.decodeAll(ais);
        assertEquals((int) elem.getLocationInformation().getAgeOfLocationInformation(), 200);
    }

    @Test(groups = { "functional.encode", "EsiBcsm" })
    public void testEncode() throws Exception {

        LocationInformation locationInformation = new LocationInformationImpl(200, null, null, null, null, null, null, null, null, false, false, null, null);
        OTermSeizedSpecificInfoImpl elem = new OTermSeizedSpecificInfoImpl(locationInformation);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, EventSpecificInformationBCSMImpl._ID_oTermSeizedSpecificInfo);
        assertEquals(aos.toByteArray(), this.getData1());
    }

    @Test(groups = { "functional.xml.serialize", "EsiBcsm" })
    public void testXMLSerializaion() throws Exception {
        LocationInformation locationInformation = new LocationInformationImpl(200, null, null, null, null, null, null, null, null, false, false, null, null);
        OTermSeizedSpecificInfoImpl original = new OTermSeizedSpecificInfoImpl(locationInformation);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "oTermSeizedSpecificInfo", OTermSeizedSpecificInfoImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        OTermSeizedSpecificInfoImpl copy = reader.read("oTermSeizedSpecificInfo", OTermSeizedSpecificInfoImpl.class);

        assertEquals((int) copy.getLocationInformation().getAgeOfLocationInformation(), (int) original.getLocationInformation().getAgeOfLocationInformation());
    }

}
