package org.restcomm.protocols.ss7.sccp.impl.parameter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0010Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ParameterFactoryImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class GT0010Test {
    private byte[] data = new byte[] { 3, 0x09, 0x32, 0x26, 0x59, 0x18 };
    private ParameterFactoryImpl factory = new ParameterFactoryImpl();

    public GT0010Test() {
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

    @Test(groups = { "parameter", "functional.decode" })
    public void testDecodeEven() throws Exception {
        // TODO: we are testing here BCD even. We will need to add national encoding when we add soem staff

        // wrap data with input stream
        ByteArrayInputStream in = new ByteArrayInputStream(data);

        // create GT object and read data from stream
        GlobalTitle0010Impl gt1 = new GlobalTitle0010Impl();
        gt1.decode(in, factory, SccpProtocolVersion.ITU);

        // check results
        assertEquals(gt1.getTranslationType(), 3);
        assertEquals(gt1.getDigits(), "9023629581");
    }

    @Test(groups = { "parameter", "functional.encode" })
    public void testEncodeEven() throws Exception {
        // TODO: we are testing here BCD even. We will need to add national encoding when we add soem staff

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GlobalTitle0010Impl gt = new GlobalTitle0010Impl("9023629581", 3);

        gt.encode(bout, false, SccpProtocolVersion.ITU);

        byte[] res = bout.toByteArray();

        boolean correct = Arrays.equals(data, res);
        assertTrue(correct, "Incorrect encoding");
    }

    @Test(groups = { "parameter", "functional.encode" })
    public void testSerialization() throws Exception {
        GlobalTitle0010Impl gt = new GlobalTitle0010Impl("9023629581",0);

        // Writes
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(output);
        writer.setIndentation("\t"); // Optional (use tabulation for
        // indentation).
        writer.write(gt, "GT0010", GlobalTitle0010Impl.class);
        writer.close();

        System.out.println(output.toString());

        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        XMLObjectReader reader = XMLObjectReader.newInstance(input);
        GlobalTitle0010Impl aiOut = reader.read("GT0010", GlobalTitle0010Impl.class);

        // check results
        assertEquals(aiOut.getTranslationType(), 0);
        assertEquals(aiOut.getDigits(), "9023629581");
    }

}
