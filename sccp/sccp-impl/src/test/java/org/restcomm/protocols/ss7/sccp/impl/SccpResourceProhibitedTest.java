
package org.restcomm.protocols.ss7.sccp.impl;

import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.sccp.ConcernedSignalingPointCode;
import org.restcomm.protocols.ss7.sccp.RemoteSignalingPointCode;
import org.restcomm.protocols.ss7.sccp.RemoteSubSystem;
import org.restcomm.protocols.ss7.sccp.impl.SccpResourceImpl;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * @author gennadiy dubina
 *
 */
public class SccpResourceProhibitedTest {

    private SccpResourceImpl resource = null;

    public SccpResourceProhibitedTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @AfterMethod
    public void tearDown() {
        resource.removeAllResources();
        resource.stop();
    }

    @Test(groups = { "sccpresource", "functional.sccp" })
    public void testProhibitedTrue() throws Exception {
        resource = new SccpResourceImpl("SccpResourceProhibitedTest", true, new Ss7ExtSccpDetailedInterfaceDefault());
        resource.setPersistDir(Util.getTmpTestDir());
        resource.start();
        resource.removeAllResources();

        resource.addRemoteSpc(1, 6034, 0, 0);
        resource.addRemoteSpc(2, 6045, 0, 0);

        assertTrue(resource.getRemoteSpc(1).isRemoteSpcProhibited());
        assertTrue(resource.getRemoteSpc(1).isRemoteSccpProhibited());

        assertTrue(resource.getRemoteSpc(2).isRemoteSpcProhibited());
        assertTrue(resource.getRemoteSpc(2).isRemoteSccpProhibited());
    }

    @Test(groups = { "sccpresource", "functional.sccp" })
    public void testProhibitedFalse() throws Exception {
        resource = new SccpResourceImpl("SccpResourceProhibitedTest", false, new Ss7ExtSccpDetailedInterfaceDefault());
        resource.setPersistDir(Util.getTmpTestDir());
        resource.start();
        resource.removeAllResources();

        resource.addRemoteSpc(1, 6034, 0, 0);
        resource.addRemoteSpc(2, 6045, 0, 0);

        assertFalse(resource.getRemoteSpc(1).isRemoteSpcProhibited());
        assertFalse(resource.getRemoteSpc(1).isRemoteSccpProhibited());

        assertFalse(resource.getRemoteSpc(2).isRemoteSpcProhibited());
        assertFalse(resource.getRemoteSpc(2).isRemoteSccpProhibited());
    }

    @Test(groups = { "sccpresource", "functional.encode" })
    public void testSerialization() throws Exception {

        resource = new SccpResourceImpl("SccpResourceProhibitedTest", true, new Ss7ExtSccpDetailedInterfaceDefault());
        resource.setPersistDir(Util.getTmpTestDir());
        resource.start();
        resource.removeAllResources();

        resource.addRemoteSpc(1, 6034, 0, 0);
        resource.addRemoteSpc(2, 6045, 0, 0);

        resource.addRemoteSsn(1, 6034, 8, 0, false);
        resource.addRemoteSsn(2, 6045, 8, 0, false);

        resource.addConcernedSpc(1, 603);
        resource.addConcernedSpc(2, 604);

        SccpResourceImpl resource1 = new SccpResourceImpl("SccpResourceProhibitedTest", true, new Ss7ExtSccpDetailedInterfaceDefault());
        resource1.setPersistDir(Util.getTmpTestDir());
        resource1.start();

        assertEquals(resource1.getRemoteSpcs().size(), 2);
        RemoteSignalingPointCode rsp1Temp = resource1.getRemoteSpc(1);
        assertNotNull(rsp1Temp);
        assertEquals(rsp1Temp.getRemoteSpc(), 6034);

        assertTrue(resource1.getRemoteSpc(1).isRemoteSpcProhibited());
        assertTrue(resource1.getRemoteSpc(1).isRemoteSccpProhibited());

        assertTrue(resource1.getRemoteSpc(2).isRemoteSpcProhibited());
        assertTrue(resource1.getRemoteSpc(2).isRemoteSccpProhibited());

        assertEquals(resource1.getRemoteSsns().size(), 2);
        RemoteSubSystem rss1Temp = resource1.getRemoteSsn(1);
        assertEquals(rss1Temp.getRemoteSsn(), 8);

        assertEquals(resource1.getConcernedSpcs().size(), 2);
        ConcernedSignalingPointCode cspc1Temp = resource1.getConcernedSpc(1);
        assertEquals(cspc1Temp.getRemoteSpc(), 603);
    }

}
