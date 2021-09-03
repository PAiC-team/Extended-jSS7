
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.CK;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.IK;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.KSI;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.CKImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.IKImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.KSIImpl;
import org.restcomm.protocols.ss7.map.service.mobility.authentication.UMTSSecurityContextDataImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class UMTSSecurityContextDataTest {

    public byte[] getData() {
        return new byte[] { 48, 39, 4, 16, 4, 4, 1, 2, 3, 4, 4, 4, 1, 2, 3, 4, 4, 4, 1, 2, 4, 16, 2, 5, 27, 6, 23, 23, 34, 56,
                34, 76, 34, 4, 4, 1, 2, 3, 4, 1, 2 };
    };

    public byte[] getDataCk() {
        return new byte[] { 4, 4, 1, 2, 3, 4, 4, 4, 1, 2, 3, 4, 4, 4, 1, 2 };
    };

    public byte[] getDataIk() {
        return new byte[] { 2, 5, 27, 6, 23, 23, 34, 56, 34, 76, 34, 4, 4, 1, 2, 3 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        UMTSSecurityContextDataImpl prim = new UMTSSecurityContextDataImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(Arrays.equals(prim.getCK().getData(), getDataCk()));
        assertTrue(Arrays.equals(prim.getIK().getData(), getDataIk()));
        assertEquals(prim.getKSI().getData(), 2);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        CK ck = new CKImpl(getDataCk());
        IK ik = new IKImpl(getDataIk());
        KSI ksi = new KSIImpl(2);
        UMTSSecurityContextDataImpl prim = new UMTSSecurityContextDataImpl(ck, ik, ksi);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

    }
}
