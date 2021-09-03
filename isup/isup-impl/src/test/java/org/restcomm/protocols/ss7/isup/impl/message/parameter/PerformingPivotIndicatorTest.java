package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.impl.message.parameter.PerformingPivotIndicatorImpl;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.PivotReasonImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.PivotReason;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author baranowb
 * 
 */
public class PerformingPivotIndicatorTest {

    /**
     * 
     */
    public PerformingPivotIndicatorTest() {
        // TODO Auto-generated constructor stub
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testSetEncodeDecodeGet() throws Exception {
        PerformingPivotIndicatorImpl ppi = new PerformingPivotIndicatorImpl();
        PivotReasonImpl pr1 = new PivotReasonImpl();
        pr1.setPivotReason((byte) 1);
        pr1.setPivotPossibleAtPerformingExchange((byte) 5);
        PivotReasonImpl pr2 = new PivotReasonImpl();
        pr2.setPivotReason((byte) 21);
        pr2.setPivotPossibleAtPerformingExchange((byte) 3);
        ppi.setReason(pr1, pr2);
        byte[] b = ppi.encode();
        ppi = new PerformingPivotIndicatorImpl();
        ppi.decode(b);
        PivotReason[] reasons = ppi.getReason();
        Assert.assertNotNull(reasons);
        Assert.assertEquals(reasons.length, 2);
        Assert.assertNotNull(reasons[0]);
        Assert.assertNotNull(reasons[1]);
        Assert.assertEquals(reasons[0].getPivotReason(), (byte) 1);
        Assert.assertEquals(reasons[0].getPivotPossibleAtPerformingExchange(), (byte) 5);
        Assert.assertEquals(reasons[1].getPivotReason(), (byte) 21);
        Assert.assertEquals(reasons[1].getPivotPossibleAtPerformingExchange(), (byte) 3);
    }
}