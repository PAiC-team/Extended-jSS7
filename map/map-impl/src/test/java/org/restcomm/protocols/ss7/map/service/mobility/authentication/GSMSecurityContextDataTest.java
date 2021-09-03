
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.Cksn;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.Kc;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.CksnImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.GSMSecurityContextDataImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.KcImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GSMSecurityContextDataTest {

    public byte[] getData() {
        return new byte[] { 48, 13, 4, 8, 4, 4, 1, 2, 3, 4, 4, 4, 4, 1, 4 };
    };

    public byte[] getDataKc() {
        return new byte[] { 4, 4, 1, 2, 3, 4, 4, 4 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        GSMSecurityContextDataImpl prim = new GSMSecurityContextDataImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(Arrays.equals(prim.getKc().getData(), getDataKc()));
        assertEquals(prim.getCksn().getData(), 4);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        Kc kc = new KcImpl(getDataKc());
        Cksn cksn = new CksnImpl(4);

        GSMSecurityContextDataImpl prim = new GSMSecurityContextDataImpl(kc, cksn);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

    }
}
