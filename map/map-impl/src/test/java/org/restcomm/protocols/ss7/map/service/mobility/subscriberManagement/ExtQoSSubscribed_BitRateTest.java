
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtQoSSubscribed_BitRateImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ExtQoSSubscribed_BitRateTest {

    @Test(groups = { "functional.decode", "mobility.subscriberManagement" })
    public void testDecode() throws Exception {

        ExtQoSSubscribed_BitRateImpl prim = new ExtQoSSubscribed_BitRateImpl(1, true);
        assertEquals(prim.getBitRate(), 1);

        prim = new ExtQoSSubscribed_BitRateImpl(63, true);
        assertEquals(prim.getBitRate(), 63);

        prim = new ExtQoSSubscribed_BitRateImpl(64, true);
        assertEquals(prim.getBitRate(), 64);

        prim = new ExtQoSSubscribed_BitRateImpl(120, true);
        assertEquals(prim.getBitRate(), 512);

        prim = new ExtQoSSubscribed_BitRateImpl(127, true);
        assertEquals(prim.getBitRate(), 568);

        prim = new ExtQoSSubscribed_BitRateImpl(128, true);
        assertEquals(prim.getBitRate(), 576);

        prim = new ExtQoSSubscribed_BitRateImpl(129, true);
        assertEquals(prim.getBitRate(), 576 + 64);

        prim = new ExtQoSSubscribed_BitRateImpl(232, true);
        assertEquals(prim.getBitRate(), 7232);

        prim = new ExtQoSSubscribed_BitRateImpl(254, true);
        assertEquals(prim.getBitRate(), 8640);

        prim = new ExtQoSSubscribed_BitRateImpl(0, true);
        assertEquals(prim.getBitRate(), 0);

        prim = new ExtQoSSubscribed_BitRateImpl(255, true);
        assertEquals(prim.getBitRate(), 0);

        prim = new ExtQoSSubscribed_BitRateImpl(300, true);
        assertEquals(prim.getBitRate(), 0);
    }

    @Test(groups = { "functional.encode", "mobility.subscriberManagement" })
    public void testEncode() throws Exception {

        ExtQoSSubscribed_BitRateImpl prim = new ExtQoSSubscribed_BitRateImpl(1, false);
        assertEquals(prim.getSourceData(), 1);

        prim = new ExtQoSSubscribed_BitRateImpl(63, false);
        assertEquals(prim.getSourceData(), 63);

        prim = new ExtQoSSubscribed_BitRateImpl(64, false);
        assertEquals(prim.getSourceData(), 64);

        prim = new ExtQoSSubscribed_BitRateImpl(512, false);
        assertEquals(prim.getSourceData(), 120);

        prim = new ExtQoSSubscribed_BitRateImpl(568, false);
        assertEquals(prim.getSourceData(), 127);

        prim = new ExtQoSSubscribed_BitRateImpl(576, false);
        assertEquals(prim.getSourceData(), 128);

        prim = new ExtQoSSubscribed_BitRateImpl(576 + 64, false);
        assertEquals(prim.getSourceData(), 129);

        prim = new ExtQoSSubscribed_BitRateImpl(7232, false);
        assertEquals(prim.getSourceData(), 232);

        prim = new ExtQoSSubscribed_BitRateImpl(8640, false);
        assertEquals(prim.getSourceData(), 254);

        prim = new ExtQoSSubscribed_BitRateImpl(0, false);
        assertEquals(prim.getSourceData(), 0);

        prim = new ExtQoSSubscribed_BitRateImpl(100000, false);
        assertEquals(prim.getSourceData(), 0);
    }

}
