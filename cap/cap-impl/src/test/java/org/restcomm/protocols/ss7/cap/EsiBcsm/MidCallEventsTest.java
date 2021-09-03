
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
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.GenericDigitsImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericDigits;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class MidCallEventsTest {

    public byte[] getData1() {
        return new byte[] { (byte) 131, 5, 99, 1, 2, 3, 4 };
    }

    public byte[] getData2() {
        return new byte[] { (byte) 132, 5, 99, 1, 2, 3, 4 };
    }

    public byte[] getDigitsData() {
        return new byte[] { 1, 2, 3, 4 };
    }

    @Test(groups = { "functional.decode", "EsiBcsm" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        MidCallEventsImpl elem = new MidCallEventsImpl();
        int tag = ais.readTag();
        assertEquals(tag, MidCallEventsImpl._ID_dTMFDigitsCompleted);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        elem.decodeAll(ais);
        assertEquals(elem.getDTMFDigitsCompleted().getGenericDigits().getEncodingScheme(), GenericDigits._ENCODING_SCHEME_BINARY);
        assertEquals(elem.getDTMFDigitsCompleted().getGenericDigits().getEncodedDigits(), getDigitsData());
        assertNull(elem.getDTMFDigitsTimeOut());


        data = this.getData2();
        ais = new AsnInputStream(data);
        elem = new MidCallEventsImpl();
        tag = ais.readTag();
        assertEquals(tag, MidCallEventsImpl._ID_dTMFDigitsTimeOut);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        elem.decodeAll(ais);
        assertEquals(elem.getDTMFDigitsTimeOut().getGenericDigits().getEncodingScheme(), GenericDigits._ENCODING_SCHEME_BINARY);
        assertEquals(elem.getDTMFDigitsTimeOut().getGenericDigits().getEncodedDigits(), getDigitsData());
        assertNull(elem.getDTMFDigitsCompleted());
    }

    @Test(groups = { "functional.encode", "EsiBcsm" })
    public void testEncode() throws Exception {

        GenericDigits genericDigits = new GenericDigitsImpl(GenericDigits._ENCODING_SCHEME_BINARY, GenericDigits._TOD_BGCI, getDigitsData());
        // int encodingScheme, int typeOfDigits, byte[] digits
        Digits dtmfDigits = new DigitsImpl(genericDigits);
        MidCallEventsImpl elem = new MidCallEventsImpl(dtmfDigits, true);
//        Digits dtmfDigits, boolean isDtmfDigitsCompleted
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertEquals(aos.toByteArray(), this.getData1());

        elem = new MidCallEventsImpl(dtmfDigits, false);
        aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertEquals(aos.toByteArray(), this.getData2());
    }

    @Test(groups = { "functional.xml.serialize", "EsiBcsm" })
    public void testXMLSerializaion() throws Exception {
        GenericDigits genericDigits = new GenericDigitsImpl(GenericDigits._ENCODING_SCHEME_BINARY, GenericDigits._TOD_BGCI, getDigitsData());
        Digits dtmfDigits = new DigitsImpl(genericDigits);
        MidCallEventsImpl original = new MidCallEventsImpl(dtmfDigits, true);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "midCallEvents", MidCallEventsImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        MidCallEventsImpl copy = reader.read("midCallEvents", MidCallEventsImpl.class);

        assertEquals(copy.getDTMFDigitsCompleted().getGenericDigits().getEncodingScheme(), original.getDTMFDigitsCompleted().getGenericDigits().getEncodingScheme());
        assertEquals(copy.getDTMFDigitsCompleted().getGenericDigits().getEncodedDigits(), original.getDTMFDigitsCompleted().getGenericDigits().getEncodedDigits());
        assertNull(copy.getDTMFDigitsTimeOut());


        original = new MidCallEventsImpl(dtmfDigits, false);

        // Writes the area to a file.
        baos = new ByteArrayOutputStream();
        writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "midCallEvents", MidCallEventsImpl.class);
        writer.close();

        rawData = baos.toByteArray();
        serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        bais = new ByteArrayInputStream(rawData);
        reader = XMLObjectReader.newInstance(bais);
        copy = reader.read("midCallEvents", MidCallEventsImpl.class);

        assertEquals(copy.getDTMFDigitsTimeOut().getGenericDigits().getEncodingScheme(), original.getDTMFDigitsTimeOut().getGenericDigits().getEncodingScheme());
        assertEquals(copy.getDTMFDigitsTimeOut().getGenericDigits().getEncodedDigits(), original.getDTMFDigitsTimeOut().getGenericDigits().getEncodedDigits());
        assertNull(copy.getDTMFDigitsCompleted());
    }

}
