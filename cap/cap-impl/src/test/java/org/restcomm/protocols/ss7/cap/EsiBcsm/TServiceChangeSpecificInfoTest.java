
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiBcsm.TServiceChangeSpecificInfoImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtBasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtTeleserviceCodeImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class TServiceChangeSpecificInfoTest {

    public byte[] getData1() {
        return new byte[] { 48, 5, (byte) 160, 3, (byte) 131, 1, 99 };
    }

    @Test(groups = { "functional.decode", "EsiBcsm" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        TServiceChangeSpecificInfoImpl elem = new TServiceChangeSpecificInfoImpl();
        int tag = ais.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(ais.getTagClass(), Tag.CLASS_UNIVERSAL);

        elem.decodeAll(ais);
        assertEquals(elem.getExtBasicServiceCode().getExtTeleservice().getTeleserviceCodeValue(), TeleserviceCodeValue.facsimileGroup4);
    }

    @Test(groups = { "functional.encode", "EsiBcsm" })
    public void testEncode() throws Exception {

        ExtTeleserviceCode extTeleservice = new ExtTeleserviceCodeImpl(TeleserviceCodeValue.facsimileGroup4);
        ExtBasicServiceCode extBasicServiceCode = new ExtBasicServiceCodeImpl(extTeleservice);
        TServiceChangeSpecificInfoImpl elem = new TServiceChangeSpecificInfoImpl(extBasicServiceCode);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertEquals(aos.toByteArray(), this.getData1());
    }

    @Test(groups = { "functional.xml.serialize", "EsiBcsm" })
    public void testXMLSerializaion() throws Exception {
        ExtTeleserviceCode extTeleservice = new ExtTeleserviceCodeImpl(TeleserviceCodeValue.facsimileGroup4);
        ExtBasicServiceCode extBasicServiceCode = new ExtBasicServiceCodeImpl(extTeleservice);
        TServiceChangeSpecificInfoImpl original = new TServiceChangeSpecificInfoImpl(extBasicServiceCode);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "tServiceChangeSpecificInfo", TServiceChangeSpecificInfoImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        TServiceChangeSpecificInfoImpl copy = reader.read("tServiceChangeSpecificInfo", TServiceChangeSpecificInfoImpl.class);

        assertEquals(copy.getExtBasicServiceCode().getExtTeleservice().getTeleserviceCodeValue(), original.getExtBasicServiceCode().getExtTeleservice()
                .getTeleserviceCodeValue());
    }

}
