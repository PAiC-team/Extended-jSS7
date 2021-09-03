
package org.restcomm.protocols.ss7.sccpext.impl.translation;

import static org.testng.Assert.assertTrue;

import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.impl.SccpHarness;
import org.restcomm.protocols.ss7.sccp.impl.User;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author amit bhayani
 * @author kulikov
 * @author baranowb
 */
public class PCSSNSccpStackImplTest extends SccpHarness {

    private SccpAddress a1, a2;

    public PCSSNSccpStackImplTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        this.sccpStack1Name = "PCSSNSccTestSccpStack1";
        this.sccpStack2Name = "PCSSNSccTestSccpStack2";
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }

    /**
     * Test of configure method, of class SccpStackImpl.
     */
    @Test(groups = { "gtt", "functional.route" })
    public void testRemoteRoutingBasedOnSsn() throws Exception {
        a1 = super.sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, 1, 8);
        a2 = super.sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, 2, 8);

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN());

        u1.register();
        u2.register();

        u1.send();
        u2.send();

        Thread.currentThread().sleep(3000);

        assertTrue(u1.check(), "Message not received");
        assertTrue(u2.check(), "Message not received");
    }

}
