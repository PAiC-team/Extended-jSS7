
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.primitives.AppendFreeFormatData;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.FreeFormatDataGprs;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.FCIBCCCAMELsequence1GprsImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.FreeFormatDataGprsImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.PDPIDImpl;
import org.testng.annotations.Test;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class FCIBCCCAMELsequence1GprsTest {

    public byte[] getData() {
        return new byte[] { -96, 16, -128, 8, 48, 6, -128, 1, 5, -127, 1, 2, -127, 1, 2, -126, 1, 1 };
    };

    public byte[] getFreeFormatData() {
        return new byte[] { 48, 6, -128, 1, 5, -127, 1, 2 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {
        byte[] data = this.getData();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        FCIBCCCAMELsequence1GprsImpl prim = new FCIBCCCAMELsequence1GprsImpl();
        prim.decodeAll(asn);

        assertEquals(tag, FCIBCCCAMELsequence1GprsImpl._ID_FCIBCCCAMELsequence1);
        assertEquals(asn.getTagClass(), Tag.CLASS_CONTEXT_SPECIFIC);

        assertEquals(prim.getFreeFormatData().getData(), this.getFreeFormatData());
        assertEquals(prim.getPDPID().getId(), 2);
        assertEquals(prim.getAppendFreeFormatData(), AppendFreeFormatData.append);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        FreeFormatDataGprs freeFormatData = new FreeFormatDataGprsImpl(this.getFreeFormatData());
        PDPID pdpID = new PDPIDImpl(2);
        FCIBCCCAMELsequence1GprsImpl prim = new FCIBCCCAMELsequence1GprsImpl(freeFormatData, pdpID, AppendFreeFormatData.append);
        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);
        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

}
