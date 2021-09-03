
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.BitSetStrictLength;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CSGIdImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CSGIdTest {

    private BitSetStrictLength getData() {
        BitSetStrictLength res = new BitSetStrictLength(27);
        res.set(1);
        res.set(5);
        res.set(10);
        res.set(26);
        return res;
    }

    @Test(groups = { "functional.xml.serialize", "subscriberInformation" })
    public void testXMLSerialize() throws Exception {

        CSGIdImpl original = new CSGIdImpl(getData());

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "csgId", CSGIdImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CSGIdImpl copy = reader.read("csgId", CSGIdImpl.class);

        for (int i1 = 0; i1 < original.getData().getStrictLength(); i1++) {
            assertEquals(copy.getData().get(i1), original.getData().get(i1));
        }
        assertEquals(copy.getData().getStrictLength(), original.getData().getStrictLength());

    }

}
