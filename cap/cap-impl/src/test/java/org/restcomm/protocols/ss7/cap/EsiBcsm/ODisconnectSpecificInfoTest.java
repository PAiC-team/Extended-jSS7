
package org.restcomm.protocols.ss7.cap.EsiBcsm;

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
import org.restcomm.protocols.ss7.cap.EsiBcsm.ODisconnectSpecificInfoImpl;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSMImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CauseIndicatorsImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.testng.annotations.Test;

/**
 * @author Amit Bhayani
 * @author sergey vetyutnev
 *
 */
public class ODisconnectSpecificInfoTest {

    public byte[] getData() {
        return new byte[] { (byte) 167, 4, (byte) 128, 2, (byte) 132, (byte) 144 };
    }

    public byte[] getIntData() {
        return new byte[] { (byte) 132, (byte) 144 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        ODisconnectSpecificInfoImpl elem = new ODisconnectSpecificInfoImpl();
        int tag = ais.readTag();
        assertEquals(tag, EventSpecificInformationBCSMImpl._ID_oDisconnectSpecificInfo);
        assertEquals(ais.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);
        elem.decodeAll(ais);
        assertTrue(Arrays.equals(elem.getReleaseCause().getData(), this.getIntData()));
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        CauseCapImpl cause = new CauseCapImpl(this.getIntData());
        ODisconnectSpecificInfoImpl elem = new ODisconnectSpecificInfoImpl(cause);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, EventSpecificInformationBCSMImpl._ID_oDisconnectSpecificInfo);
        assertEquals(aos.toByteArray(), this.getData());

    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall.primitive" })
    public void testXMLSerializaion() throws Exception {

        CauseIndicatorsImpl prim = new CauseIndicatorsImpl(CauseIndicators._CODING_STANDARD_ITUT,
                CauseIndicators._LOCATION_PRIVATE_NSRU, 0, CauseIndicators._CV_CALL_REJECTED, null);

        CauseCapImpl cause = new CauseCapImpl(prim);
        ODisconnectSpecificInfoImpl original = new ODisconnectSpecificInfoImpl(cause);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "oDisconnectSpecificInfo", ODisconnectSpecificInfoImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        ODisconnectSpecificInfoImpl copy = reader.read("oDisconnectSpecificInfo", ODisconnectSpecificInfoImpl.class);

        assertEquals(copy.getReleaseCause().getCauseIndicators().getLocation(), original.getReleaseCause().getCauseIndicators()
                .getLocation());
        assertEquals(copy.getReleaseCause().getCauseIndicators().getCauseValue(), original.getReleaseCause()
                .getCauseIndicators().getCauseValue());
        assertEquals(copy.getReleaseCause().getCauseIndicators().getCodingStandard(), original.getReleaseCause()
                .getCauseIndicators().getCodingStandard());
    }

}
