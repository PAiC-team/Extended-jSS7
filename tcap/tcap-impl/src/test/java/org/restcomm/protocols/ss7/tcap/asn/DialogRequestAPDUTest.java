package org.restcomm.protocols.ss7.tcap.asn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class DialogRequestAPDUTest {

    private byte[] getData() {
        return new byte[] { 96, 15, (byte) 128, 2, 7, (byte) 128, (byte) 161, 9, 6, 7, 4, 0, 0, 1, 0, 21, 2 };
    }

    private byte[] getData2() {
        return new byte[] { 96, 32, (byte) 128, 2, 7, (byte) 128, (byte) 161, 9, 6, 7, 4, 0, 0, 1, 0, 25, 2, (byte) 190, 15,
                40, 13, 6, 7, 4, 0, 0, 1, 1, 1, 1, (byte) 160, 2, (byte) 160, 0 };
    }

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        byte[] b = getData();
        AsnInputStream asnIs = new AsnInputStream(b);
        int tag = asnIs.readTag();
        assertEquals(0, tag);
        DialogRequestAPDU d = TcapFactory.createDialogAPDURequest();
        d.decode(asnIs);
        assertTrue(Arrays.equals(new long[] { 0, 4, 0, 0, 1, 0, 21, 2 }, d.getApplicationContextName().getOid()));
        UserInformation ui = d.getUserInformation();
        assertNull(ui);

        AsnOutputStream aos = new AsnOutputStream();
        d.encode(aos);
        assertTrue(Arrays.equals(b, aos.toByteArray()));

        b = getData2();
        asnIs = new AsnInputStream(b);
        tag = asnIs.readTag();
        assertEquals(0, tag);
        d = TcapFactory.createDialogAPDURequest();
        d.decode(asnIs);
        assertTrue(Arrays.equals(new long[] { 0, 4, 0, 0, 1, 0, 25, 2 }, d.getApplicationContextName().getOid()));
        ui = d.getUserInformation();
        assertNotNull(ui);
        assertTrue(Arrays.equals(new long[] { 0, 4, 0, 0, 1, 1, 1, 1 }, ui.getOidValue()));
        assertNotNull(ui.getEncodeType());
        ui.getEncodeType();
        assertTrue(Arrays.equals(new byte[] { -96, 0 }, ui.getEncodeType()));

        aos = new AsnOutputStream();
        d.encode(aos);
        assertTrue(Arrays.equals(b, aos.toByteArray()));
    }
}
