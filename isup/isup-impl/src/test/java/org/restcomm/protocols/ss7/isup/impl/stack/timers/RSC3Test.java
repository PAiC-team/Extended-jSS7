
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.testng.annotations.Test;

/**
 * @author baranowb
 *
 */
public class RSC3Test extends RSCTest {
    // thanks to magic of super class, this is whole test :)

    @Test(groups = { "functional.timer", "timer.timeout.big.woanswer" })
    public void testSmallTimeoutWithAnswer() throws Exception {
        super.testSmallTimeoutWithAnswer();
    }

}
