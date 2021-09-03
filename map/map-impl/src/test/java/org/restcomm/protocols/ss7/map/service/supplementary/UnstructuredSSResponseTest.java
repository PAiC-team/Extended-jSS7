
package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.UnstructuredSSResponseImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Amit Bhayani
 * 
 */
public class UnstructuredSSResponseTest {

    /**
     * 
     */
    public UnstructuredSSResponseTest() {
        // TODO Auto-generated constructor stub
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeTest
    public void setUp() {
    }

    @AfterTest
    public void tearDown() {
    }

    @Test(groups = { "functional.xml.serialize", "service.ussd" })
    public void testXMLSerialize() throws Exception {

        USSDString ussdStr = new USSDStringImpl("1", null, null);
        UnstructuredSSResponseImpl original = new UnstructuredSSResponseImpl(new CBSDataCodingSchemeImpl(0x0f), ussdStr);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "unstructuredSSResponse", UnstructuredSSResponseImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        UnstructuredSSResponseImpl copy = reader.read("unstructuredSSResponse", UnstructuredSSResponseImpl.class);

        assertEquals(copy.getDataCodingScheme().getCode(), original.getDataCodingScheme().getCode());
        assertEquals(copy.getUSSDString(), original.getUSSDString());

    }
    
    @Test(groups = { "functional.xml.serialize", "service.ussd" })
    public void testXMLSerializeNullUssd() throws Exception {

        UnstructuredSSResponseImpl original = new UnstructuredSSResponseImpl();

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "unstructuredSSResponse", UnstructuredSSResponseImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        UnstructuredSSResponseImpl copy = reader.read("unstructuredSSResponse", UnstructuredSSResponseImpl.class);

        assertNull(copy.getUSSDString());

    }    

}
