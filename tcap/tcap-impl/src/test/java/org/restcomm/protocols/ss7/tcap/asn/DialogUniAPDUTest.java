package org.restcomm.protocols.ss7.tcap.asn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.DialogUniAPDU;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class DialogUniAPDUTest {

    private byte[] getData() {
        return new byte[] { 96, 27, (byte) 128, 2, 7, (byte) 128, (byte) 161, 6, 6, 4, 4, 2, 2, 2, (byte) 190, 13, 40, 11, 6,
                4, 1, 1, 2, 3, (byte) 160, 3, 11, 22, 33 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] b = getData();
        AsnInputStream asnIs = new AsnInputStream(b);
        int tag = asnIs.readTag();
        assertEquals(0, tag);
        DialogUniAPDU d = TcapFactory.createDialogAPDUUni();
        d.decode(asnIs);
        assertTrue(Arrays.equals(new long[] { 0, 4, 2, 2, 2 }, d.getApplicationContextName().getOid()));
        UserInformation ui = d.getUserInformation();
        assertNotNull(ui);
        assertTrue(Arrays.equals(new byte[] { 11, 22, 33 }, ui.getEncodeType()));

        AsnOutputStream aos = new AsnOutputStream();
        d.encode(aos);
        assertTrue(Arrays.equals(b, aos.toByteArray()));
    }
}
