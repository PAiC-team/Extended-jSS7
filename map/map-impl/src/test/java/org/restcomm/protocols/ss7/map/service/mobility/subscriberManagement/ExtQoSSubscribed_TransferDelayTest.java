
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtQoSSubscribed_TransferDelayImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ExtQoSSubscribed_TransferDelayTest {

    @Test(groups = { "functional.decode", "mobility.subscriberManagement" })
    public void testDecode() throws Exception {

        ExtQoSSubscribed_TransferDelayImpl prim = new ExtQoSSubscribed_TransferDelayImpl(0, true);
        assertEquals(prim.getTransferDelay(), 0);

        prim = new ExtQoSSubscribed_TransferDelayImpl(1, true);
        assertEquals(prim.getTransferDelay(), 10);

        prim = new ExtQoSSubscribed_TransferDelayImpl(15, true);
        assertEquals(prim.getTransferDelay(), 150);

        prim = new ExtQoSSubscribed_TransferDelayImpl(16, true);
        assertEquals(prim.getTransferDelay(), 200);

        prim = new ExtQoSSubscribed_TransferDelayImpl(17, true);
        assertEquals(prim.getTransferDelay(), 250);

        prim = new ExtQoSSubscribed_TransferDelayImpl(31, true);
        assertEquals(prim.getTransferDelay(), 950);

        prim = new ExtQoSSubscribed_TransferDelayImpl(32, true);
        assertEquals(prim.getTransferDelay(), 1000);

        prim = new ExtQoSSubscribed_TransferDelayImpl(33, true);
        assertEquals(prim.getTransferDelay(), 1100);

        prim = new ExtQoSSubscribed_TransferDelayImpl(62, true);
        assertEquals(prim.getTransferDelay(), 4000);
    }

    @Test(groups = { "functional.encode", "mobility.subscriberManagement" })
    public void testEncode() throws Exception {

        ExtQoSSubscribed_TransferDelayImpl prim = new ExtQoSSubscribed_TransferDelayImpl(0, false);
        assertEquals(prim.getSourceData(), 0);

        prim = new ExtQoSSubscribed_TransferDelayImpl(10, false);
        assertEquals(prim.getSourceData(), 1);

        prim = new ExtQoSSubscribed_TransferDelayImpl(150, false);
        assertEquals(prim.getSourceData(), 15);

        prim = new ExtQoSSubscribed_TransferDelayImpl(200, false);
        assertEquals(prim.getSourceData(), 16);

        prim = new ExtQoSSubscribed_TransferDelayImpl(250, false);
        assertEquals(prim.getSourceData(), 17);

        prim = new ExtQoSSubscribed_TransferDelayImpl(950, false);
        assertEquals(prim.getSourceData(), 31);

        prim = new ExtQoSSubscribed_TransferDelayImpl(1000, false);
        assertEquals(prim.getSourceData(), 32);

        prim = new ExtQoSSubscribed_TransferDelayImpl(1100, false);
        assertEquals(prim.getSourceData(), 33);

        prim = new ExtQoSSubscribed_TransferDelayImpl(4000, false);
        assertEquals(prim.getSourceData(), 62);
    }

}
