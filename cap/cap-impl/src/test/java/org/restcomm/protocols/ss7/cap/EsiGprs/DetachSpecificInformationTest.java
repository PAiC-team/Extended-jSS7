
package org.restcomm.protocols.ss7.cap.EsiGprs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.EsiGprs.DetachSpecificInformationImpl;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.InitiatingEntity;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class DetachSpecificInformationTest {

    public byte[] getData() {
        return new byte[] { -80, 5, -128, 1, 2, -127, 0 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        DetachSpecificInformationImpl prim = new DetachSpecificInformationImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(prim.getInitiatingEntity(), InitiatingEntity.hlr);
        assertTrue(prim.getRouteingAreaUpdate());

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        DetachSpecificInformationImpl prim = new DetachSpecificInformationImpl(InitiatingEntity.hlr, true);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
