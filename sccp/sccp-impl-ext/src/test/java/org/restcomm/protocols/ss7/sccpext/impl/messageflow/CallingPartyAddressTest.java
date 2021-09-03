
package org.restcomm.protocols.ss7.sccpext.impl.messageflow;

import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.impl.SccpHarnessExt;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImplProxy;
import org.restcomm.protocols.ss7.sccp.impl.User;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallingPartyAddressTest extends SccpHarnessExt {

    private SccpAddress a1, a2;

    public CallingPartyAddressTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        this.sccpStack1Name = "CallingPartyAddressTestStack1";
        this.sccpStack2Name = "CallingPartyAddressTestStack2";
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

//    protected void createStack1() {
//        sccpStack1 = createStack(sccpStack1Name);
//        sccpProvider1 = sccpStack1.getSccpProvider();
//    }
//
//    protected void createStack2() {
//        sccpStack2 = createStack(sccpStack2Name);
//        sccpProvider2 = sccpStack2.getSccpProvider();
//    }

    @Override
    protected SccpStackImpl createStack(String name, Ss7ExtInterface ss7ExtInterface) {
        SccpStackImpl stack = new SccpStackImplProxy(name, ss7ExtInterface);
        final String dir = Util.getTmpTestDir();
        if (dir != null) {
            stack.setPersistDir(dir);
        }
        return stack;
    }

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }

    public byte[] getDataSrc() {
        return new byte[] { 11, 12, 13, 14, 15 };
    }

    @Test(groups = { "SccpMessage", "functional.transfer" })
    public void testTransfer() throws Exception {

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null,
                getStack1PC(), 8);
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null,
                getStack2PC(), 8);

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN());

        u1.register();
        u2.register();

        Thread.sleep(100);

        // no newCallingPartyAddress
        SccpAddress primaryAddress = sccpProvider1.getParameterFactory().createSccpAddress(
                RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN,
                sccpProvider1.getParameterFactory().createGlobalTitle("111111", 1), getStack2PC(), 8);
        routerExt1.addRoutingAddress(1, primaryAddress);
        SccpAddress newCallingPartyAddress = sccpProvider1.getParameterFactory().createSccpAddress(
                RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                sccpProvider1.getParameterFactory().createGlobalTitle("222222", 1), 0, 8);
        routerExt1.addRoutingAddress(2, newCallingPartyAddress);
        SccpAddress pattern = sccpProvider1.getParameterFactory().createSccpAddress(
                RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                sccpProvider1.getParameterFactory().createGlobalTitle("111111", 1), 0, 0);
        routerExt1.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Undefined, OriginationType.ALL, pattern, "K",
                1, -1, null, 0, null);

        SccpAddress a3 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                sccpProvider1.getParameterFactory().createGlobalTitle("111111", 1), 0, 0);
        SccpDataMessage message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a3, a1, getDataSrc(), 0, 8,
                true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);

        assertEquals(u1.getMessages().size(), 0);
        assertEquals(u2.getMessages().size(), 1);
        SccpDataMessage dMsg = (SccpDataMessage) u2.getMessages().get(0);
        assertNull(dMsg.getCallingPartyAddress().getGlobalTitle());

        // present newCallingPartyAddress
        routerExt1.removeRule(1);
        routerExt1.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Undefined, OriginationType.ALL, pattern, "K",
                1, -1, 2, 0, null);

        message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a3, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);

        assertEquals(u1.getMessages().size(), 0);
        assertEquals(u2.getMessages().size(), 2);
        dMsg = (SccpDataMessage) u2.getMessages().get(1);
        assertTrue(dMsg.getCallingPartyAddress().getGlobalTitle().getDigits().equals("222222"));
    }
}
