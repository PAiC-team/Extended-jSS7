
package org.restcomm.protocols.ss7.cap.gap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Tone;
import org.restcomm.protocols.ss7.cap.gap.GapTreatmentImpl;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.InformationToSendImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.ToneImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CauseIndicatorsImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class GapTreatmentTest {

    public byte[] getData() {
        return new byte[] { (byte) 160, 8, (byte) 161, 6, (byte) 128, 1, 10, (byte) 129, 1, 20 };
    }

    public byte[] getData2() {
        return new byte[] { (byte) 129, 2, (byte) 192, (byte) 131 };
    }

    @Test(groups = { "functional.decode", "gap" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        GapTreatmentImpl elem = new GapTreatmentImpl();
        int tag = ais.readTag();
        assertEquals(tag, GapTreatmentImpl._ID_InformationToSend);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        elem.decodeAll(ais);

        assertEquals(elem.getInformationToSend().getTone().getToneID(), 10);
        assertEquals((int) (elem.getInformationToSend().getTone().getDuration()), 20);


        data = this.getData2();
        ais = new AsnInputStream(data);
        elem = new GapTreatmentImpl();
        tag = ais.readTag();
        assertEquals(tag, GapTreatmentImpl._ID_CauseCap);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        elem.decodeAll(ais);

        assertEquals(elem.getCause().getCauseIndicators().getCauseValue(), 3);
    }

    @Test(groups = { "functional.encode", "gap" })
    public void testEncode() throws Exception {
        Tone tone = new ToneImpl(10, 20);
        InformationToSend informationToSend = new InformationToSendImpl(tone);
        GapTreatmentImpl elem = new GapTreatmentImpl(informationToSend);

        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);

        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));


        // int codingStandard, int location, int recommendation, int causeValue, byte[] diagnostics
        CauseIndicators causeIndicators = new CauseIndicatorsImpl(2, 0, 0, 3, null);
        CauseCap releaseCause = new CauseCapImpl(causeIndicators);
        elem = new GapTreatmentImpl(releaseCause);

        aos = new AsnOutputStream();
        elem.encodeAll(aos);

        assertTrue(Arrays.equals(aos.toByteArray(), this.getData2()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall" })
    public void testXMLSerialize() throws Exception {

        Tone tone = new ToneImpl(10, 20);
        InformationToSend informationToSend = new InformationToSendImpl(tone);
        GapTreatmentImpl original = new GapTreatmentImpl(informationToSend);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "gapTreatment", GapTreatmentImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);

        GapTreatmentImpl copy = reader.read("gapTreatment", GapTreatmentImpl.class);

        assertTrue(isEqual(original, copy));


        CauseIndicators causeIndicators = new CauseIndicatorsImpl(2, 0, 0, 3, null);
        CauseCap releaseCause = new CauseCapImpl(causeIndicators);
        original = new GapTreatmentImpl(releaseCause);

        baos = new ByteArrayOutputStream();
        writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "gapTreatment", GapTreatmentImpl.class);
        writer.close();

        rawData = baos.toByteArray();
        serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        bais = new ByteArrayInputStream(rawData);
        reader = XMLObjectReader.newInstance(bais);

        copy = reader.read("gapTreatment", GapTreatmentImpl.class);

        assertTrue(isEqual(original, copy));
    }

    private boolean isEqual(GapTreatmentImpl o1, GapTreatmentImpl o2) {
        if (o1 == o2)
            return true;
        if (o1 == null && o2 != null || o1 != null && o2 == null)
            return false;
        if (o1 == null && o2 == null)
            return true;
        if (!o1.toString().equals(o2.toString()))
            return false;
        return true;
    }

}
