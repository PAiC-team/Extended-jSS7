
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiBcsm.CollectedInfoSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.isup.CalledPartyNumberCapImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CalledPartyNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CalledPartyNumber;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class CollectedInfoSpecificInfoTest {

    public byte[] getData1() {
        return new byte[] { 48, 10, (byte) 128, 8, (byte) 132, 16, 34, 34, 34, 33, 67, 5 };
    }

    @Test(groups = { "functional.decode", "EsiBcsm" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        CollectedInfoSpecificInfoImpl elem = new CollectedInfoSpecificInfoImpl();
        int tag = ais.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(ais.getTagClass(), Tag.CLASS_UNIVERSAL);

        elem.decodeAll(ais);
        assertEquals(elem.getCalledPartyNumber().getCalledPartyNumber().getAddress(), "22222212345");
        assertEquals(elem.getCalledPartyNumber().getCalledPartyNumber().getNatureOfAddressIndicator(), CalledPartyNumberImpl._NAI_INTERNATIONAL_NUMBER);
        assertEquals(elem.getCalledPartyNumber().getCalledPartyNumber().getNumberingPlanIndicator(), CalledPartyNumberImpl._NPI_ISDN);
    }

    @Test(groups = { "functional.encode", "EsiBcsm" })
    public void testEncode() throws Exception {

        CalledPartyNumber calledPartyNumber = new CalledPartyNumberImpl();
        calledPartyNumber.setAddress("22222212345");
        calledPartyNumber.setNatureOfAddresIndicator(CalledPartyNumberImpl._NAI_INTERNATIONAL_NUMBER);
        calledPartyNumber.setNumberingPlanIndicator(CalledPartyNumberImpl._NPI_ISDN);
        CalledPartyNumberCap calledPartyNumberCap = new CalledPartyNumberCapImpl(calledPartyNumber);
        CollectedInfoSpecificInfoImpl elem = new CollectedInfoSpecificInfoImpl(calledPartyNumberCap);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertEquals(aos.toByteArray(), this.getData1());
    }

    @Test(groups = { "functional.xml.serialize", "EsiBcsm" })
    public void testXMLSerializaion() throws Exception {
        CalledPartyNumber calledPartyNumber = new CalledPartyNumberImpl();
        calledPartyNumber.setAddress("22222212345");
        calledPartyNumber.setNatureOfAddresIndicator(CalledPartyNumberImpl._NAI_INTERNATIONAL_NUMBER);
        calledPartyNumber.setNumberingPlanIndicator(CalledPartyNumberImpl._NPI_ISDN);
        CalledPartyNumberCap calledPartyNumberCap = new CalledPartyNumberCapImpl(calledPartyNumber);
        CollectedInfoSpecificInfoImpl original = new CollectedInfoSpecificInfoImpl(calledPartyNumberCap);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "collectedInfoSpecificInfo", CollectedInfoSpecificInfoImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CollectedInfoSpecificInfoImpl copy = reader.read("collectedInfoSpecificInfo", CollectedInfoSpecificInfoImpl.class);

        assertEquals(copy.getCalledPartyNumber().getCalledPartyNumber().getAddress(), original.getCalledPartyNumber().getCalledPartyNumber().getAddress());
        assertEquals(copy.getCalledPartyNumber().getCalledPartyNumber().getNatureOfAddressIndicator(), original.getCalledPartyNumber().getCalledPartyNumber()
                .getNatureOfAddressIndicator());
        assertEquals(copy.getCalledPartyNumber().getCalledPartyNumber().getNumberingPlanIndicator(), original.getCalledPartyNumber().getCalledPartyNumber()
                .getNumberingPlanIndicator());
    }

}
