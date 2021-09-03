
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.ROTimeGPRSIfTariffSwitchImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ROTimeGPRSIfTariffSwitchTest {

    public byte[] getData() {
        return new byte[] { -95, 6, -128, 1, 12, -127, 1, 24 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ROTimeGPRSIfTariffSwitchImpl prim = new ROTimeGPRSIfTariffSwitchImpl();
        prim.decodeAll(asn);

        assertEquals(tag, ROTimeGPRSIfTariffSwitchImpl._ID_ROTimeGPRSIfTariffSwitch);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(prim.getROTimeGPRSSinceLastTariffSwitch().intValue(), 12);
        assertEquals(prim.getROTimeGPRSTariffSwitchInterval().intValue(), 24);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        ROTimeGPRSIfTariffSwitchImpl prim = new ROTimeGPRSIfTariffSwitchImpl(new Integer(12), new Integer(24));
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
