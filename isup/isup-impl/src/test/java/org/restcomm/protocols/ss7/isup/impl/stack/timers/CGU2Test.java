
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.testng.annotations.Test;

/**
 * @author baranowb
 *
 */
public class CGU2Test extends CGUTest {
    // thanks to magic of super class, this is whole test :)

    @Test(groups = { "functional.timer", "timer.timeout.big.answer" })
    public void testBigTimeoutWithAnswer() throws Exception {
        super.testBigTimeoutWithAnswer();
    }

}
