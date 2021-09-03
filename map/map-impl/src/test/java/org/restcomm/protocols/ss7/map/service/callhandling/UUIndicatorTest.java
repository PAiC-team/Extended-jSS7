
package org.restcomm.protocols.ss7.map.service.callhandling;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.restcomm.protocols.ss7.map.service.callhandling.UUIndicatorImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class UUIndicatorTest {

    @Test(groups = { "functional.xml.serialize", "callhandling" })
    public void testXMLSerialize() throws Exception {

        UUIndicatorImpl original = new UUIndicatorImpl(136);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "uuIndicator", UUIndicatorImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        UUIndicatorImpl copy = reader.read("uuIndicator", UUIndicatorImpl.class);

        assertEquals(copy.getData(), original.getData());

    }

}
