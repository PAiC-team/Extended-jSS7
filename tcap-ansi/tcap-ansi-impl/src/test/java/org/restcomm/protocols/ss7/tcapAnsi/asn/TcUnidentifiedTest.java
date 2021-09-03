
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import static org.testng.Assert.*;

import org.mobicents.protocols.asn.AsnInputStream;
import org.restcomm.protocols.ss7.tcapAnsi.asn.TCUnidentifiedMessage;
import org.testng.annotations.Test;

@Test(groups = { "asn" })
public class TcUnidentifiedTest {

    private byte[] data1 = new byte[] { -26, 15, -57, 8, 1, 1, 2, 2, 3, 3, 4, 4, -7, 3, -37, 1, 66 };

    private byte[] trIdO = new byte[] { 1, 1, 2, 2 };
    private byte[] trIdD = new byte[] { 3, 3, 4, 4 };

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        // 1
        AsnInputStream ais = new AsnInputStream(this.data1);
        int tag = ais.readTag();

        TCUnidentifiedMessage tcm = new TCUnidentifiedMessage();
        tcm.decode(ais);

        assertEquals(tcm.getOriginatingTransactionId(), trIdO);
        assertEquals(tcm.getDestinationTransactionId(), trIdD);
        assertTrue(tcm.isDialogPortionExists());

    }

}
