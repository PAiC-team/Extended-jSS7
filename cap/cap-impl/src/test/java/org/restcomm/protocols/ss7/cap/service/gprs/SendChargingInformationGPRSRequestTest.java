
package org.restcomm.protocols.ss7.cap.service.gprs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AOCSubsequent;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAI_GSM0224;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AOCGPRS;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.AOCSubsequentImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.CAI_GSM0224Impl;
import org.restcomm.protocols.ss7.cap.service.gprs.SendChargingInformationGPRSRequestImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.AOCGPRSImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristicsImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.PDPIDImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SendChargingInformationGPRSRequestTest {

    public byte[] getData() {
        return new byte[] { 48, 46, -128, 44, 48, 42, -96, 37, -96, 21, -128, 1, 1, -127, 1, 2, -126, 1, 3, -125, 1, 4, -124,
                1, 5, -123, 1, 6, -122, 1, 7, -95, 12, -96, 6, -125, 1, 4, -124, 1, 5, -127, 2, 0, -34, -127, 1, 1 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        SendChargingInformationGPRSRequestImpl prim = new SendChargingInformationGPRSRequestImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE1(), 1);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE2(), 2);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE3(), 3);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE4(), 4);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE5(), 5);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE6(), 6);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCInitial().getE7(), 7);
        assertNull(prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224().getE1());
        assertNull(prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224().getE2());
        assertNull(prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224().getE3());
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224()
                .getE4(), 4);
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224()
                .getE5(), 5);
        assertNull(prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224().getE6());
        assertNull(prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent().getCAI_GSM0224().getE7());
        assertEquals((int) prim.getSCIGPRSBillingChargingCharacteristics().getAOCGPRS().getAOCSubsequent()
                .getTariffSwitchInterval(), 222);
        assertEquals(prim.getSCIGPRSBillingChargingCharacteristics().getPDPID().getId(), 1);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        CAI_GSM0224 aocInitial = new CAI_GSM0224Impl(1, 2, 3, 4, 5, 6, 7);
        CAI_GSM0224Impl cai_GSM0224 = new CAI_GSM0224Impl(null, null, null, 4, 5, null, null);
        AOCSubsequent aocSubsequent = new AOCSubsequentImpl(cai_GSM0224, 222);
        AOCGPRS aocGPRS = new AOCGPRSImpl(aocInitial, aocSubsequent);
        PDPID pdpID = new PDPIDImpl(1);
        CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics = new CAMELSCIGPRSBillingChargingCharacteristicsImpl(
                aocGPRS, pdpID);

        SendChargingInformationGPRSRequestImpl prim = new SendChargingInformationGPRSRequestImpl(
                sciGPRSBillingChargingCharacteristics);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
