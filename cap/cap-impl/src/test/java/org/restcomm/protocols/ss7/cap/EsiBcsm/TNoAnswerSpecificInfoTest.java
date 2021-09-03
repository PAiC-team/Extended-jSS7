
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiBcsm.TNoAnswerSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.isup.CalledPartyNumberCapImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSMImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CalledPartyNumberImpl;
import org.testng.annotations.Test;

/**
 * @author Amit Bhayani
 * @author sergey vetyutnev
 *
 */
public class TNoAnswerSpecificInfoTest {

    public byte[] getData1() {
        return new byte[] { (byte) 169, 13, (byte) 159, 50, 0, (byte) 159, 52, 7, (byte) 128, (byte) 144, 17, 33, 34, 51, 3 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        TNoAnswerSpecificInfoImpl elem = new TNoAnswerSpecificInfoImpl();
        int tag = ais.readTag();
        assertEquals(tag, EventSpecificInformationBCSMImpl._ID_tNoAnswerSpecificInfo);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        elem.decodeAll(ais);

        assertTrue(elem.getCallForwarded());
        assertEquals(elem.getForwardingDestinationNumber().getCalledPartyNumber().getAddress(), "111222333");
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        CalledPartyNumberImpl calledPartyNumber = new CalledPartyNumberImpl(0, "111222333", 1, 1);
        CalledPartyNumberCapImpl forwardingDestinationNumber = new CalledPartyNumberCapImpl(calledPartyNumber);
        TNoAnswerSpecificInfoImpl elem = new TNoAnswerSpecificInfoImpl(true, forwardingDestinationNumber);
        // boolean callForwarded, CalledPartyNumberCap forwardingDestinationNumber

        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, EventSpecificInformationBCSMImpl._ID_tNoAnswerSpecificInfo);
        assertEquals(aos.toByteArray(), this.getData1());
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall.primitive" })
    public void testXMLSerializaion() throws Exception {
        CalledPartyNumberImpl calledPartyNumber = new CalledPartyNumberImpl(0, "111222333", 1, 1);
        CalledPartyNumberCapImpl forwardingDestinationNumber = new CalledPartyNumberCapImpl(calledPartyNumber);
        TNoAnswerSpecificInfoImpl original = new TNoAnswerSpecificInfoImpl(true, forwardingDestinationNumber);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "tNoAnswerSpecificInfo", TNoAnswerSpecificInfoImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        TNoAnswerSpecificInfoImpl copy = reader.read("tNoAnswerSpecificInfo", TNoAnswerSpecificInfoImpl.class);

        assertEquals(copy.getForwardingDestinationNumber().getCalledPartyNumber().getAddress(), original
                .getForwardingDestinationNumber().getCalledPartyNumber().getAddress());
        assertEquals(copy.getCallForwarded(), original.getCallForwarded());
    }

}
