
package org.restcomm.protocols.ss7.cap.service.gprs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.service.gprs.ConnectGPRSRequestImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.AccessPointNameImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.PDPIDImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ConnectGPRSRequestTest {

    public byte[] getData() {
        return new byte[] { 48, 8, -128, 3, 52, 20, 30, -127, 1, 2 };
    };

    private byte[] getAccessPointNameData() {
        return new byte[] { 52, 20, 30 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        ConnectGPRSRequestImpl prim = new ConnectGPRSRequestImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(Arrays.equals(prim.getAccessPointName().getData(), this.getAccessPointNameData()));
        assertEquals(prim.getPDPID().getId(), 2);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        AccessPointName accessPointName = new AccessPointNameImpl(this.getAccessPointNameData());
        PDPID pdpID = new PDPIDImpl(2);
        ConnectGPRSRequestImpl prim = new ConnectGPRSRequestImpl(accessPointName, pdpID);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
