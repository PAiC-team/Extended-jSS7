
package org.restcomm.protocols.ss7.map.service.mobility.imei;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.service.mobility.imei.RequestedEquipmentInfoImpl;
import org.testng.annotations.Test;

/**
 *
 * @author normandes
 *
 */
public class RequestedEquipmentInfoTest {

    private byte[] getEncodedData() {
        // TODO this is self generated trace. We need trace from operator
        return new byte[] { 3, 2, 6, -128 };
    }

    @Test(groups = { "functional.decode", "imei" })
    public void testDecode() throws Exception {
        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        RequestedEquipmentInfoImpl imp = new RequestedEquipmentInfoImpl();
        imp.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(imp.getEquipmentStatus());
        assertFalse(imp.getBmuef());
    }

    @Test(groups = { "functional.encode", "imei" })
    public void testEncode() throws Exception {
        RequestedEquipmentInfoImpl imp = new RequestedEquipmentInfoImpl(true, false);

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
