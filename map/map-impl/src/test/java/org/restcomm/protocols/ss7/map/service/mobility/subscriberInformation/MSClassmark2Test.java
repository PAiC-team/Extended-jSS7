
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSClassmark2Impl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class MSClassmark2Test {

    private byte[] getMSClassmark2Data() {
        return new byte[] { 11, 12, 13 };
    }

    @Test(groups = { "functional.xml.serialize", "mobility.subscriberInformation" })
    public void testXMLSerialize() throws Exception {

        MSClassmark2Impl original = new MSClassmark2Impl(getMSClassmark2Data());

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "msClassmark2", MSClassmark2Impl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        MSClassmark2Impl copy = reader.read("msClassmark2", MSClassmark2Impl.class);

        assertEquals(copy.getData(), original.getData());
    }

}
