
package org.restcomm.protocols.ss7.cap.gap;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.gap.CalledAddressAndServiceImpl;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.GenericNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericNumber;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sp. z o.o.)</a>
 *
 */
public class CalledAddressAndServiceTest {

    public static final int SERVICE_KEY = 821;

    public byte[] getData() {
        return new byte[] {(byte) 48, 10, (byte) 128, 4, 48, 69, 91, 84, (byte) 129, 2, 3, 53};
    }

    public byte[] getDigitsData() {
        return new byte[] {48, 69, 91, 84};
    }

    @Test(groups = { "functional.decode", "gap" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        CalledAddressAndServiceImpl elem = new CalledAddressAndServiceImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);

        assertEquals(elem.getCalledAddressValue().getData(), getDigitsData());
        assertEquals(elem.getServiceKey(), SERVICE_KEY);
    }

    @Test(groups = { "functional.encode", "gap" })
    public void testEncode() throws Exception {

        Digits calledAddressValue = new DigitsImpl(getDigitsData());
        CalledAddressAndServiceImpl elem = new CalledAddressAndServiceImpl(calledAddressValue, SERVICE_KEY);

        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos);

        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));
    }

    @Test(groups = { "functional.xml.serialize", "gap" })
    public void testXMLSerialize() throws Exception {

        GenericNumberImpl gn = new GenericNumberImpl(GenericNumber._NAI_NATIONAL_SN, "12345",
                GenericNumber._NQIA_CONNECTED_NUMBER, GenericNumber._NPI_TELEX, GenericNumber._APRI_ALLOWED,
                GenericNumber._NI_INCOMPLETE, GenericNumber._SI_USER_PROVIDED_VERIFIED_FAILED);
        Digits digits = new DigitsImpl(gn);

        CalledAddressAndServiceImpl original = new CalledAddressAndServiceImpl(digits, SERVICE_KEY);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "CalledAddressAndServiceArg", CalledAddressAndServiceImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);

        CalledAddressAndServiceImpl copy = reader.read("CalledAddressAndServiceArg", CalledAddressAndServiceImpl.class);

        assertTrue(isEqual(original, copy));
    }

    private boolean isEqual(CalledAddressAndServiceImpl o1, CalledAddressAndServiceImpl o2) {
        if (o1 == o2)
            return true;
        if (o1 == null && o2 != null || o1 != null && o2 == null)
            return false;
        if (o1 == null && o2 == null)
            return true;
        if (!o1.toString().equals(o2.toString()))
            return false;
        return true;
    }

}
