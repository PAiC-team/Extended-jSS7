
package org.restcomm.protocols.ss7.map.service.sms;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.smstpdu.AddressField;
import org.restcomm.protocols.ss7.map.api.smstpdu.NumberingPlanIdentification;
import org.restcomm.protocols.ss7.map.api.smstpdu.TypeOfNumber;
import org.restcomm.protocols.ss7.map.service.sms.SM_RP_SMEAImpl;
import org.restcomm.protocols.ss7.map.smstpdu.AddressFieldImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SM_RP_SMEATest {

    private byte[] getEncodedData() {
        return new byte[] { (byte) 137, 11, 18, (byte) 208, 77, (byte) 170, 19, 36, 12, (byte) 143, (byte) 215, 117, 56 };
    }

    private byte[] getEncodedData2() {
        return new byte[] { (byte) 137, 4, 3, (byte) 129, 54, (byte) 241 };
    }

    private byte[] getEncodedData3() {
        return new byte[] { (byte) 137, 9, 13, (byte) 145, 50, (byte) 132, 48, 80, 22, 38, (byte) 244 };
    }

    private byte[] getEncodedData4() {
        return new byte[] { (byte) 137, 4, 4, (byte) 129, 20, 0 };
    }

    @Test(groups = { "functional.decode", "service.sms" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        SM_RP_SMEAImpl da = new SM_RP_SMEAImpl();
        da.decodeAll(asn);

        assertEquals(tag, 9);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        AddressField af = da.getAddressField();

        assertEquals(af.getTypeOfNumber(), TypeOfNumber.Alphanumeric);
        assertEquals(af.getNumberingPlanIdentification(), NumberingPlanIdentification.Unknown);
        assertTrue(af.getAddressValue().equals("MTN Backup"));

        rawData = getEncodedData2();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        da = new SM_RP_SMEAImpl();
        da.decodeAll(asn);

        assertEquals(tag, 9);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        af = da.getAddressField();

        assertEquals(af.getTypeOfNumber(), TypeOfNumber.Unknown);
        assertEquals(af.getNumberingPlanIdentification(), NumberingPlanIdentification.ISDNTelephoneNumberingPlan);
        assertTrue(af.getAddressValue().equals("631"));

        rawData = getEncodedData3();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        da = new SM_RP_SMEAImpl();
        da.decodeAll(asn);

        assertEquals(tag, 9);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        af = da.getAddressField();

        assertEquals(af.getTypeOfNumber(), TypeOfNumber.InternationalNumber);
        assertEquals(af.getNumberingPlanIdentification(), NumberingPlanIdentification.ISDNTelephoneNumberingPlan);
        assertTrue(af.getAddressValue().equals("2348030561624"));

        rawData = getEncodedData4();
        asn = new AsnInputStream(rawData);

        tag = asn.readTag();
        da = new SM_RP_SMEAImpl();
        da.decodeAll(asn);

        assertEquals(tag, 9);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        af = da.getAddressField();

        assertEquals(af.getTypeOfNumber(), TypeOfNumber.Unknown);
        assertEquals(af.getNumberingPlanIdentification(), NumberingPlanIdentification.ISDNTelephoneNumberingPlan);
        assertTrue(af.getAddressValue().equals("4100"));
    }

    @Test(groups = { "functional.encode", "service.sms" })
    public void testEncode() throws Exception {

        AddressFieldImpl af = new AddressFieldImpl(TypeOfNumber.Alphanumeric, NumberingPlanIdentification.Unknown, "MTN Backup");
        SM_RP_SMEAImpl da = new SM_RP_SMEAImpl(af);

        AsnOutputStream asnOS = new AsnOutputStream();
        da.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, 9);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

        af = new AddressFieldImpl(TypeOfNumber.Unknown, NumberingPlanIdentification.ISDNTelephoneNumberingPlan, "631");
        da = new SM_RP_SMEAImpl(af);

        asnOS = new AsnOutputStream();
        da.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, 9);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData2();
        assertTrue(Arrays.equals(rawData, encodedData));

        af = new AddressFieldImpl(TypeOfNumber.InternationalNumber, NumberingPlanIdentification.ISDNTelephoneNumberingPlan,
                "2348030561624");
        da = new SM_RP_SMEAImpl(af);

        asnOS = new AsnOutputStream();
        da.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, 9);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData3();
        assertTrue(Arrays.equals(rawData, encodedData));

        af = new AddressFieldImpl(TypeOfNumber.Unknown, NumberingPlanIdentification.ISDNTelephoneNumberingPlan, "4100");
        da = new SM_RP_SMEAImpl(af);

        asnOS = new AsnOutputStream();
        da.encodeAll(asnOS, Tag.CLASS_CONTEXT_SPECIFIC, 9);

        encodedData = asnOS.toByteArray();
        rawData = getEncodedData4();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

    @Test(groups = { "functional.serialize", "service.sms" })
    public void testSerialization() throws Exception {
    }

}
