
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CallCompletionTreatmentIndicator;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ConferenceTreatmentIndicator;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.BackwardServiceInteractionIndImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class BackwardServiceInteractionIndTest {

    public byte[] getData1() {
        return new byte[] { 48, 6, (byte) 129, 1, 2, (byte) 130, 1, 1 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        BackwardServiceInteractionIndImpl elem = new BackwardServiceInteractionIndImpl();
        int tag = ais.readTag();
        assertEquals(ais.getTag(), Tag.SEQUENCE);
        assertEquals(ais.getTagClass(), Tag.CLASS_UNIVERSAL);

        elem.decodeAll(ais);

        assertEquals(elem.getConferenceTreatmentIndicator(), ConferenceTreatmentIndicator.rejectConferenceRequest);
        assertEquals(elem.getCallCompletionTreatmentIndicator(), CallCompletionTreatmentIndicator.acceptCallCompletionServiceRequest);
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        BackwardServiceInteractionIndImpl elem = new BackwardServiceInteractionIndImpl(ConferenceTreatmentIndicator.rejectConferenceRequest,
                CallCompletionTreatmentIndicator.acceptCallCompletionServiceRequest);
//        ConferenceTreatmentIndicator conferenceTreatmentIndicator, CallCompletionTreatmentIndicator callCompletionTreatmentIndicator

        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall.primitive" })
    public void testXMLSerialize() throws Exception {

        BackwardServiceInteractionIndImpl original = new BackwardServiceInteractionIndImpl(ConferenceTreatmentIndicator.rejectConferenceRequest,
                CallCompletionTreatmentIndicator.acceptCallCompletionServiceRequest);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "backwardServiceInteractionInd", BackwardServiceInteractionIndImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        BackwardServiceInteractionIndImpl copy = reader.read("backwardServiceInteractionInd", BackwardServiceInteractionIndImpl.class);

        assertEquals(original.getConferenceTreatmentIndicator(), copy.getConferenceTreatmentIndicator());
        assertEquals(original.getCallCompletionTreatmentIndicator(), copy.getCallCompletionTreatmentIndicator());
    }

}
