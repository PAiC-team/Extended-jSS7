package org.restcomm.protocols.ss7.sccp.impl.parameter;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.restcomm.protocols.ss7.sccp.impl.parameter.NoGlobalTitle;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author amit bhayani
 *
 */
public class NoGlobalTitleTest {

    /**
	 *
	 */
    public NoGlobalTitleTest() {
        // TODO Auto-generated constructor stub
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(groups = { "parameter", "functional.encode" })
    public void testSerialization() throws Exception {
        NoGlobalTitle gt = new NoGlobalTitle("9023629581");

        // Writes
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(output);
        writer.setIndentation("\t"); // Optional (use tabulation for
        // indentation).
        writer.write(gt, "NoGlobalTitle", NoGlobalTitle.class);
        writer.close();

        System.out.println(output.toString());

        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        XMLObjectReader reader = XMLObjectReader.newInstance(input);
        NoGlobalTitle aiOut = reader.read("NoGlobalTitle", NoGlobalTitle.class);

        // check results
        assertEquals(aiOut.getDigits(), "9023629581");
    }

}
