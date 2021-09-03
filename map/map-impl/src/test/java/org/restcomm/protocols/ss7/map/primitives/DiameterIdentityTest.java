
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class DiameterIdentityTest {

    private byte[] getData() {
        return new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }

    @Test(groups = { "functional.xml.serialize", "primitives" })
    public void testXMLSerialize() throws Exception {

        DiameterIdentityImpl original = new DiameterIdentityImpl(getData());

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "diameterIdentity", DiameterIdentityImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        DiameterIdentityImpl copy = reader.read("diameterIdentity", DiameterIdentityImpl.class);

        assertEquals(copy.getData(), original.getData());

    }

}
