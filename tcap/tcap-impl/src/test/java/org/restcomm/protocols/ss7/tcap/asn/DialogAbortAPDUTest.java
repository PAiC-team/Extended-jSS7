package org.restcomm.protocols.ss7.tcap.asn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.AbortSource;
import org.restcomm.protocols.ss7.tcap.asn.AbortSourceType;
import org.restcomm.protocols.ss7.tcap.asn.DialogAbortAPDU;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class DialogAbortAPDUTest {

    private byte[] getData() {
        return new byte[] { 100, 3, (byte) 128, 1, 0 };
    }

    private byte[] getData2() {
        return new byte[] { 100, 21, (byte) 128, 1, 1, (byte) 190, 16, 40, 14, 6, 7, 4, 0, 0, 1, 1, 1, 1, (byte) 160, 3, 1, 2,
                3 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] b = getData();
        AsnInputStream asnIs = new AsnInputStream(b);
        int tag = asnIs.readTag();
        assertEquals(4, tag);
        DialogAbortAPDU d = TcapFactory.createDialogAPDUAbort();
        d.decode(asnIs);
        AbortSource as = d.getAbortSource();
        assertEquals(AbortSourceType.User, as.getAbortSourceType());
        UserInformation ui = d.getUserInformation();
        assertNull(ui);

        AsnOutputStream aos = new AsnOutputStream();
        d.encode(aos);
        assertTrue(Arrays.equals(b, aos.toByteArray()));

        b = getData2();
        asnIs = new AsnInputStream(b);
        tag = asnIs.readTag();
        assertEquals(4, tag);
        d = TcapFactory.createDialogAPDUAbort();
        d.decode(asnIs);
        as = d.getAbortSource();
        assertEquals(AbortSourceType.Provider, as.getAbortSourceType());
        ui = d.getUserInformation();
        assertNotNull(ui);
        assertTrue(Arrays.equals(new byte[] { 1, 2, 3 }, ui.getEncodeType()));

        aos = new AsnOutputStream();
        d.encode(aos);
        assertTrue(Arrays.equals(b, aos.toByteArray()));
    }
}
