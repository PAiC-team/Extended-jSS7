
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiBcsm.MidCallEventsImpl;
import org.restcomm.protocols.ss7.cap.EsiBcsm.OMidCallSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.EsiBcsm.TMidCallSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.MidCallEvents;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSMImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.GenericDigitsImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericDigits;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class TMidCallSpecificInfoTest {

    public byte[] getData1() {
        return new byte[] { (byte) 171, 9, (byte) 161, 7, (byte) 131, 5, 99, 1, 2, 3, 4 };
    }

    public byte[] getDigitsData() {
        return new byte[] { 1, 2, 3, 4 };
    }

    @Test(groups = { "functional.decode", "EsiBcsm" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        TMidCallSpecificInfoImpl elem = new TMidCallSpecificInfoImpl();
        int tag = ais.readTag();
        assertEquals(tag, EventSpecificInformationBCSMImpl._ID_tMidCallSpecificInfo);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        elem.decodeAll(ais);
        assertEquals(elem.getMidCallEvents().getDTMFDigitsCompleted().getGenericDigits().getEncodingScheme(), GenericDigits._ENCODING_SCHEME_BINARY);
        assertEquals(elem.getMidCallEvents().getDTMFDigitsCompleted().getGenericDigits().getEncodedDigits(), getDigitsData());
        assertNull(elem.getMidCallEvents().getDTMFDigitsTimeOut());
    }

    @Test(groups = { "functional.encode", "EsiBcsm" })
    public void testEncode() throws Exception {

        GenericDigits genericDigits = new GenericDigitsImpl(GenericDigits._ENCODING_SCHEME_BINARY, GenericDigits._TOD_BGCI, getDigitsData());
        // int encodingScheme, int typeOfDigits, byte[] digits
        Digits dtmfDigits = new DigitsImpl(genericDigits);
        MidCallEvents midCallEvents = new MidCallEventsImpl(dtmfDigits, true);
        TMidCallSpecificInfoImpl elem = new TMidCallSpecificInfoImpl(midCallEvents);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, EventSpecificInformationBCSMImpl._ID_tMidCallSpecificInfo);
        assertEquals(aos.toByteArray(), this.getData1());
    }

    @Test(groups = { "functional.xml.serialize", "EsiBcsm" })
    public void testXMLSerializaion() throws Exception {
        GenericDigits genericDigits = new GenericDigitsImpl(GenericDigits._ENCODING_SCHEME_BINARY, GenericDigits._TOD_BGCI, getDigitsData());
        Digits dtmfDigits = new DigitsImpl(genericDigits);
        MidCallEvents midCallEvents = new MidCallEventsImpl(dtmfDigits, true);
        TMidCallSpecificInfoImpl original = new TMidCallSpecificInfoImpl(midCallEvents);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "tMidCallSpecificInfo", TMidCallSpecificInfoImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        OMidCallSpecificInfoImpl copy = reader.read("tMidCallSpecificInfo", OMidCallSpecificInfoImpl.class);

        assertEquals(copy.getMidCallEvents().getDTMFDigitsCompleted().getGenericDigits().getEncodingScheme(), original.getMidCallEvents()
                .getDTMFDigitsCompleted().getGenericDigits().getEncodingScheme());
        assertEquals(copy.getMidCallEvents().getDTMFDigitsCompleted().getGenericDigits().getEncodedDigits(), original.getMidCallEvents()
                .getDTMFDigitsCompleted().getGenericDigits().getEncodedDigits());
        assertNull(copy.getMidCallEvents().getDTMFDigitsTimeOut());
    }

}
