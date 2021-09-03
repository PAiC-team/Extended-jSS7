
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.IMSIWithLMSIImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class IMSIWithLMSITest {

    public byte[] getData1() {
        return new byte[] { 48, 12, 4, 4, 17, 17, 119, 119, 4, 4, 1, 2, 3, 4 };
    };

    public byte[] getDataLmsi() {
        return new byte[] { 1, 2, 3, 4 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData1();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        IMSIWithLMSIImpl prim = new IMSIWithLMSIImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(prim.getImsi().getData().equals("11117777"));
        assertTrue(Arrays.equals(prim.getLmsi().getData(), getDataLmsi()));
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {

        IMSIImpl imsi = new IMSIImpl("11117777");
        LMSIImpl lmsi = new LMSIImpl(getDataLmsi());
        IMSIWithLMSIImpl prim = new IMSIWithLMSIImpl(imsi, lmsi);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData1()));
    }

}
