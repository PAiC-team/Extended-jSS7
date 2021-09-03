
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.TimeIfTariffSwitchImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public class TimeIfTariffSwitchTest {

    public byte[] getData1() {
        return new byte[] { 48, 6, (byte) 128, 1, 11, (byte) 129, 1, 22 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        TimeIfTariffSwitchImpl elem = new TimeIfTariffSwitchImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertEquals(elem.getTimeSinceTariffSwitch(), 11);
        assertEquals((int) elem.getTariffSwitchInterval(), 22);
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        TimeIfTariffSwitchImpl elem = new TimeIfTariffSwitchImpl(11, 22);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall" })
    public void testXMLSerializaion() throws Exception {
        TimeIfTariffSwitchImpl original = new TimeIfTariffSwitchImpl(11, 22);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "timeIfTariffSwitch", TimeIfTariffSwitchImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        TimeIfTariffSwitchImpl copy = reader.read("timeIfTariffSwitch", TimeIfTariffSwitchImpl.class);

        assertEquals(copy.getTimeSinceTariffSwitch(), original.getTimeSinceTariffSwitch());
        assertEquals(copy.getTariffSwitchInterval(), original.getTariffSwitchInterval());

    }
}
