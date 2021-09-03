
package org.restcomm.protocols.ss7.sccp.impl;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Amit Bhayani
 *
 */
public class SccpStackTest {

    private SccpStackImpl sccpStackImpl = null;

    /**
	 *
	 */
    public SccpStackTest() {
        // TODO Auto-generated constructor stub
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp() throws IOException {

    }

    @AfterMethod
    public void tearDown() {
        this.sccpStackImpl.stop();
    }

    @Test(groups = { "sccpStack", "functional.encode" })
    public void testSerialization() throws Exception {
        this.sccpStackImpl = new SccpStackImpl("SccpStackTest", null);
        this.sccpStackImpl.setPersistDir(Util.getTmpTestDir());
        this.sccpStackImpl.start();

        this.sccpStackImpl.setZMarginXudtMessage(160);
        this.sccpStackImpl.setReassemblyTimerDelay(10000);
        this.sccpStackImpl.setMaxDataMessage(3952);
        this.sccpStackImpl.setRemoveSpc(false);
        this.sccpStackImpl.setSstTimerDuration_Min(5000);
        this.sccpStackImpl.setSstTimerDuration_Max(1200000);
        this.sccpStackImpl.setSstTimerDuration_IncreaseFactor(1.0);
        this.sccpStackImpl.setCanRelay(true);
        this.sccpStackImpl.setConnEstTimerDelay(60001);
        this.sccpStackImpl.setIasTimerDelay(300001);
        this.sccpStackImpl.setIarTimerDelay(660001);
        this.sccpStackImpl.setRelTimerDelay(10001);
        this.sccpStackImpl.setRepeatRelTimerDelay(10001);
        this.sccpStackImpl.setIntTimerDelay(1);
        this.sccpStackImpl.setGuardTimerDelay(1380001);
        this.sccpStackImpl.setResetTimerDelay(10001);

        this.sccpStackImpl.stop();

        this.sccpStackImpl.start();

        assertEquals(this.sccpStackImpl.getZMarginXudtMessage(), 160);
        assertEquals(this.sccpStackImpl.getReassemblyTimerDelay(), 10000);
        assertEquals(this.sccpStackImpl.getMaxDataMessage(), 3952);
        assertEquals(this.sccpStackImpl.isRemoveSpc(), false);
        assertEquals(this.sccpStackImpl.getSstTimerDuration_Min(), 5000);
        assertEquals(this.sccpStackImpl.getSstTimerDuration_Max(), 1200000);
        assertEquals(this.sccpStackImpl.getSstTimerDuration_IncreaseFactor(), 1.0);
        assertEquals(this.sccpStackImpl.isCanRelay(), true);
        assertEquals(this.sccpStackImpl.getConnEstTimerDelay(), 60001);
        assertEquals(this.sccpStackImpl.getIasTimerDelay(), 300001);
        assertEquals(this.sccpStackImpl.getIarTimerDelay(), 660001);
        assertEquals(this.sccpStackImpl.getRelTimerDelay(), 10001);
        assertEquals(this.sccpStackImpl.getRepeatRelTimerDelay(), 10001);
        assertEquals(this.sccpStackImpl.getIntTimerDelay(), 1);
        assertEquals(this.sccpStackImpl.getGuardTimerDelay(), 1380001);
        assertEquals(this.sccpStackImpl.getResetTimerDelay(), 10001);

        this.sccpStackImpl.stop();
    }

}
