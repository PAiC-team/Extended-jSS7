
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import org.testng.annotations.Test;

/**
 * @author baranowb
 *
 */
public class CGU4Test extends CGUTest {
    // thanks to magic of super class, this is whole test :)

    @Test(groups = { "functional.timer", "timer.answer" })
    public void testNoTimeoutWithAnswer() throws Exception {
        super.testNoTimeoutWithAnswer();
    }

}
