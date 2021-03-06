
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CauseValueCodeValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CauseValueImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CauseValueTest {

    public byte[] getData() {
        return new byte[] { 4, 1, 83 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        CauseValueImpl prim = new CauseValueImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);
        assertEquals(prim.getCauseValueCodeValue(), CauseValueCodeValue.ASuspendedCallExists);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        CauseValueImpl prim = new CauseValueImpl(CauseValueCodeValue.ASuspendedCallExists);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }
}
