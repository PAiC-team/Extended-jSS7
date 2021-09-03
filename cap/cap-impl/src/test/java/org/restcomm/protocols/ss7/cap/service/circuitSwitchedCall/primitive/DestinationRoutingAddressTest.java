
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.isup.CalledPartyNumberCapImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.DestinationRoutingAddressImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class DestinationRoutingAddressTest {

    public byte[] getData1() {
        return new byte[] { (byte) 160, 7, 4, 5, 2, 16, 121, 34, 16 };
    }

    public byte[] getIntData1() {
        return new byte[] { 2, 16, 121, 34, 16 };
    }

    @Test(groups = { "functional.decode", "circuitSwitchedCall.primitive" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();
        AsnInputStream ais = new AsnInputStream(data);
        DestinationRoutingAddressImpl elem = new DestinationRoutingAddressImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertNotNull(elem.getCalledPartyNumber());
        assertEquals(elem.getCalledPartyNumber().size(), 1);
        assertTrue(Arrays.equals(elem.getCalledPartyNumber().get(0).getData(), this.getIntData1()));
    }

    @Test(groups = { "functional.encode", "circuitSwitchedCall.primitive" })
    public void testEncode() throws Exception {

        ArrayList<CalledPartyNumberCap> cpnl = new ArrayList<CalledPartyNumberCap>();
        CalledPartyNumberCapImpl cpn = new CalledPartyNumberCapImpl(getIntData1());
        cpnl.add(cpn);
        DestinationRoutingAddressImpl elem = new DestinationRoutingAddressImpl(cpnl);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, 0);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));

        // int natureOfAddresIndicator, String address, int numberingPlanIndicator, int internalNetworkNumberIndicator
    }

    @Test(groups = { "functional.xml.serialize", "circuitSwitchedCall.primitive" })
    public void testXMLSerialize() throws Exception {

        ArrayList<CalledPartyNumberCap> cpnl = new ArrayList<CalledPartyNumberCap>();
        CalledPartyNumberCapImpl cpn = new CalledPartyNumberCapImpl(getIntData1());
        cpnl.add(cpn);
        DestinationRoutingAddressImpl original = new DestinationRoutingAddressImpl(cpnl);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "destinationRoutingAddress", DestinationRoutingAddressImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        DestinationRoutingAddressImpl copy = reader.read("destinationRoutingAddress", DestinationRoutingAddressImpl.class);

        assertEquals(copy.getCalledPartyNumber().size(), original.getCalledPartyNumber().size());
        assertEquals(copy.getCalledPartyNumber().get(0).getData(), original.getCalledPartyNumber().get(0).getData());

    }
}
