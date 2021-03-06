package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.restcomm.protocols.ss7.map.api.smstpdu.NumberingPlanIdentification;
import org.restcomm.protocols.ss7.map.api.smstpdu.TypeOfNumber;
import org.restcomm.protocols.ss7.map.smstpdu.AddressFieldImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class AddressFieldTest {

    public byte[] getData() {
        return new byte[] { 11, -111, 39, 34, -125, 72, 35, -15 };
    }

    // This is real trace
    public byte[] getDataAlphaNumeric_AWCC() {
        return new byte[] { 0x07, (byte) 0xd0, (byte) 0xc1, (byte) 0xeb, 0x70, 0x08 };
    }

    // This is real trace
    public byte[] getDataAlphaNumeric_Ufone() {
        return new byte[] { 0x09, (byte) 0xd0, (byte) 0x55, (byte) 0xf3, (byte) 0xdb, 0x5d, 0x06 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        InputStream stm = new ByteArrayInputStream(this.getData());
        AddressFieldImpl impl = AddressFieldImpl.createMessage(stm);
        assertEquals(impl.getTypeOfNumber(), TypeOfNumber.InternationalNumber);
        assertEquals(impl.getNumberingPlanIdentification(), NumberingPlanIdentification.ISDNTelephoneNumberingPlan);
        assertEquals(impl.getAddressValue(), "72223884321");
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        AddressFieldImpl impl = new AddressFieldImpl(TypeOfNumber.InternationalNumber,
                NumberingPlanIdentification.ISDNTelephoneNumberingPlan, "72223884321");
        ByteArrayOutputStream stm = new ByteArrayOutputStream();
        impl.encodeData(stm);
        assertTrue(Arrays.equals(stm.toByteArray(), this.getData()));
    }

    @Test(groups = { "functional.decode", "smstpduAlphaNumeric" })
    public void testDecodeAlphaNumericAwcc() throws Exception {

        InputStream stm = new ByteArrayInputStream(this.getDataAlphaNumeric_AWCC());
        AddressFieldImpl impl = AddressFieldImpl.createMessage(stm);
        assertEquals(impl.getTypeOfNumber(), TypeOfNumber.Alphanumeric);
        assertEquals(impl.getNumberingPlanIdentification(), NumberingPlanIdentification.Unknown);
        assertEquals(impl.getAddressValue(), "AWCC");
    }

    @Test(groups = { "functional.encode", "smstpduAlphaNumeric" })
    public void testEncodeAlphaNumericAwcc() throws Exception {

        AddressFieldImpl impl = new AddressFieldImpl(TypeOfNumber.Alphanumeric, NumberingPlanIdentification.Unknown, "AWCC");
        ByteArrayOutputStream stm = new ByteArrayOutputStream();
        impl.encodeData(stm);
        byte[] encodedData = stm.toByteArray();
        assertTrue(Arrays.equals(encodedData, this.getDataAlphaNumeric_AWCC()));
    }

    @Test(groups = { "functional.decode", "smstpduAlphaNumeric" })
    public void testDecodeAlphaNumericUfone() throws Exception {

        InputStream stm = new ByteArrayInputStream(this.getDataAlphaNumeric_Ufone());
        AddressFieldImpl impl = AddressFieldImpl.createMessage(stm);
        assertEquals(impl.getTypeOfNumber(), TypeOfNumber.Alphanumeric);
        assertEquals(impl.getNumberingPlanIdentification(), NumberingPlanIdentification.Unknown);
        assertEquals(impl.getAddressValue(), "Ufone");
    }

    @Test(groups = { "functional.encode", "smstpduAlphaNumeric" })
    public void testEncodeAlphaNumericUfone() throws Exception {

        AddressFieldImpl impl = new AddressFieldImpl(TypeOfNumber.Alphanumeric, NumberingPlanIdentification.Unknown, "Ufone");
        ByteArrayOutputStream stm = new ByteArrayOutputStream();
        impl.encodeData(stm);
        byte[] encodedData = stm.toByteArray();
        assertTrue(Arrays.equals(encodedData, this.getDataAlphaNumeric_Ufone()));
    }
}
