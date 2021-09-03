
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
import org.restcomm.protocols.ss7.cap.api.primitives.ErrorTreatment;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.CollectedDigitsImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CollectedDigitsTest {

    public byte[] getData1() {
        return new byte[] { 48, 34, (byte) 128, 1, 15, (byte) 129, 1, 30, (byte) 130, 1, 1, (byte) 131, 2, 2, 2, (byte) 132, 1,
                55, (byte) 133, 1, 100, (byte) 134, 1, 101, (byte) 135, 1, 2, (byte) 136, 1, 0, (byte) 137, 1, (byte) 255,
                (byte) 138, 1, 0 };
    }

    public byte[] getEndOfReplyDigit() {
        return new byte[] { 1 };
    }

    public byte[] getCancelDigit() {
        return new byte[] { 2, 2 };
    }

    public byte[] getStartDigit() {
        return new byte[] { 55 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        CollectedDigitsImpl elem = new CollectedDigitsImpl();
        int tag = ais.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        elem.decodeAll(ais);
        assertEquals((int) elem.getMinimumNumberOfDigits(), 15);
        assertEquals((int) elem.getMaximumNumberOfDigits(), 30);
        assertTrue(Arrays.equals(elem.getEndOfReplyDigit(), getEndOfReplyDigit()));
        assertTrue(Arrays.equals(elem.getCancelDigit(), getCancelDigit()));
        assertTrue(Arrays.equals(elem.getStartDigit(), getStartDigit()));
        assertEquals((int) elem.getFirstDigitTimeOut(), 100);
        assertEquals((int) elem.getInterDigitTimeOut(), 101);
        assertEquals(elem.getErrorTreatment(), ErrorTreatment.repeatPrompt);
        assertFalse((boolean) elem.getInterruptableAnnouncementIndicator());
        assertTrue((boolean) elem.getVoiceInformation());
        assertFalse((boolean) elem.getVoiceBack());
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        CollectedDigitsImpl elem = new CollectedDigitsImpl(15, 30, getEndOfReplyDigit(), getCancelDigit(), getStartDigit(),
                100, 101, ErrorTreatment.repeatPrompt, false, true, false);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));

        // Integer minimumNbOfDigits, int maximumNbOfDigits, byte[] endOfReplyDigit, byte[] cancelDigit, byte[] startDigit,
        // Integer firstDigitTimeOut, Integer interDigitTimeOut, ErrorTreatment errorTreatment, Boolean interruptableAnnInd,
        // Boolean voiceInformation,
        // Boolean voiceBack
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall" })
    public void testXMLSerialize() throws Exception {

        CollectedDigitsImpl original = new CollectedDigitsImpl(15, 30, getEndOfReplyDigit(), getCancelDigit(), getStartDigit(),
                100, 101, ErrorTreatment.repeatPrompt, false, true, false);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "collectedDigits", CollectedDigitsImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CollectedDigitsImpl copy = reader.read("collectedDigits", CollectedDigitsImpl.class);

        assertEquals((int) copy.getMinimumNumberOfDigits(), 15);
        assertEquals(copy.getMaximumNumberOfDigits(), 30);
        assertEquals(copy.getEndOfReplyDigit(), getEndOfReplyDigit());
        assertEquals(copy.getCancelDigit(), getCancelDigit());
        assertEquals(copy.getStartDigit(), getStartDigit());
        assertEquals((int) copy.getFirstDigitTimeOut(), 100);
        assertEquals((int) copy.getInterDigitTimeOut(), 101);
        assertEquals(copy.getErrorTreatment(), ErrorTreatment.repeatPrompt);
        assertFalse(copy.getInterruptableAnnouncementIndicator());
        assertTrue(copy.getVoiceInformation());
        assertFalse(copy.getVoiceBack());


        original = new CollectedDigitsImpl(null, 31, null, null, null, null, null, null, null, null, null);

        // Writes the area to a file.
        baos = new ByteArrayOutputStream();
        writer = XMLObjectWriter.newInstance(baos);
        writer.setIndentation("\t");
        writer.write(original, "collectedDigits", CollectedDigitsImpl.class);
        writer.close();

        rawData = baos.toByteArray();
        serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        bais = new ByteArrayInputStream(rawData);
        reader = XMLObjectReader.newInstance(bais);
        copy = reader.read("collectedDigits", CollectedDigitsImpl.class);

        assertNull(copy.getMinimumNumberOfDigits());
        assertEquals(copy.getMaximumNumberOfDigits(), 31);
        assertNull(copy.getEndOfReplyDigit());
        assertNull(copy.getCancelDigit());
        assertNull(copy.getStartDigit());
        assertNull(copy.getFirstDigitTimeOut());
        assertNull(copy.getInterDigitTimeOut());
        assertNull(copy.getErrorTreatment());
        assertNull(copy.getInterruptableAnnouncementIndicator());
        assertNull(copy.getVoiceInformation());
        assertNull(copy.getVoiceBack());

    }
}
