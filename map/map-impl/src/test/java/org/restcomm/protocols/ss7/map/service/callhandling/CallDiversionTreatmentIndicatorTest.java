
package org.restcomm.protocols.ss7.map.service.callhandling;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallDiversionTreatmentIndicatorValue;
import org.restcomm.protocols.ss7.map.service.callhandling.CallDiversionTreatmentIndicatorImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CallDiversionTreatmentIndicatorTest {

    public byte[] getData() {
        return new byte[] { 4, 1, 2 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        CallDiversionTreatmentIndicatorImpl prim = new CallDiversionTreatmentIndicatorImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertEquals(prim.getCallDiversionTreatmentIndicatorValue(),
                CallDiversionTreatmentIndicatorValue.callDiversionNotAllowed);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        CallDiversionTreatmentIndicatorImpl prim = new CallDiversionTreatmentIndicatorImpl(
                CallDiversionTreatmentIndicatorValue.callDiversionNotAllowed);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }
}
