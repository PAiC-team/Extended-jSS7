
package org.restcomm.protocols.ss7.sccpext.impl;

import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpConnection;
import org.restcomm.protocols.ss7.sccp.SccpConnectionState;
import org.restcomm.protocols.ss7.sccp.impl.SccpHarnessExt;
import org.restcomm.protocols.ss7.sccp.impl.User;
import org.restcomm.protocols.ss7.sccp.impl.parameter.BCDEvenEncodingScheme;
import org.restcomm.protocols.ss7.sccp.impl.parameter.CreditImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ImportanceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.LocalReferenceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ProtocolClassImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ReleaseCauseImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ResetCauseImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpConnCrMessage;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConnectionTest extends SccpHarnessExt {

    private SccpAddress a1, a2;

    public ConnectionTest() {
    }

    @DataProvider(name="ConnectionTestDataProvider")
    public static Object[][] createData() {
        return new Object[][] {
                new Object[] {false},
                new Object[] {true}
        };
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        this.sccpStack1Name = "MessageTransferTestStack1";
        this.sccpStack2Name = "MessageTransferTestStack2";
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp(Object[] testArgs) throws Exception {
        boolean onlyOneStack = (Boolean)testArgs[0];
        this.onlyOneStack = onlyOneStack;

        ssn2 = 6;
        super.setUp();

        GlobalTitle gtPattern = super.parameterFactory.createGlobalTitle("*", 0, NumberingPlan.ISDN_TELEPHONY, new BCDEvenEncodingScheme(), NatureOfAddress.INTERNATIONAL);
        SccpAddress pattern = super.parameterFactory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gtPattern, 0, 0);

        GlobalTitle gtRa11 = super.parameterFactory.createGlobalTitle("1111", 0, NumberingPlan.ISDN_TELEPHONY, new BCDEvenEncodingScheme(), NatureOfAddress.INTERNATIONAL);
        SccpAddress routingAddress11 = super.parameterFactory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gtRa11, getStack2PC(), 0);
        SccpAddress routingAddress12 = super.parameterFactory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gtRa11, getStack1PC(), 0);
        routerExt1.addRoutingAddress(1, routingAddress11);
        routerExt1.addRoutingAddress(2, routingAddress12);
        routerExt1.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, -1, null, 0, null);
        routerExt1.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.REMOTE, pattern, "K", 2, -1, null, 0, null);

        if (onlyOneStack) {
            sccpStack2 = sccpStack1;
            sccpProvider2 = sccpProvider1;
            sccpStack2Name = sccpStack1Name;

            return;
        }
        SccpAddress routingAddress21 = super.parameterFactory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gtRa11, getStack1PC(), 0);
        SccpAddress routingAddress22 = super.parameterFactory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gtRa11, getStack2PC(), 0);
        routerExt2.addRoutingAddress(1, routingAddress21);
        routerExt2.addRoutingAddress(2, routingAddress22);
        routerExt2.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, -1, null, 0, null);
        routerExt2.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.REMOTE, pattern, "K", 2, -1, null, 0, null);
    }

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }

    private void stackParameterInit() {
        this.setReferenceNumberCounterStack1WithoutChecking(20);

        this.setIasTimerDelayStack1WithoutChecking(7500 * 60);
        this.setIarTimerDelayStack1WithoutChecking(16000 * 60);

        this.setSstTimerDuration_MinStack1WithoutChecking(10000);

        this.setRelTimerDelayStack1WithoutChecking(15000);
        this.setRepeatRelTimerDelayStack1WithoutChecking(15000);
        this.setIntTimerDelayStack1WithoutChecking(30000);

        // sccpStack1.referenceNumberCounter = 20;
//
//        sccpStack1.iasTimerDelay = 7500 * 60;
//        sccpStack1.iarTimerDelay = 16000 * 60;
//
//        sccpStack1.sstTimerDuration_Min = 10000;
//
//        sccpStack1.relTimerDelay = 15000;
//        sccpStack1.repeatRelTimerDelay = 15000;
//        sccpStack1.intTimerDelay = 30000;

        if (!onlyOneStack) {
            this.setReferenceNumberCounterStack2WithoutChecking(50);

            this.setIasTimerDelayStack2WithoutChecking(7500 * 60);
            this.setIarTimerDelayStack2WithoutChecking(16000 * 60);

            this.setSstTimerDuration_MinStack2WithoutChecking(10000);

            this.setRelTimerDelayStack2WithoutChecking(15000);
            this.setRepeatRelTimerDelayStack2WithoutChecking(15000);
            this.setIntTimerDelayStack2WithoutChecking(30000);

//            sccpStack2.referenceNumberCounter = 50;
//
//            sccpStack2.iasTimerDelay = 7500 * 60;
//            sccpStack2.iarTimerDelay = 16000 * 60;
//
//            sccpStack2.sstTimerDuration_Min = 10000;
//
//            sccpStack2.relTimerDelay = 15000;
//            sccpStack2.repeatRelTimerDelay = 15000;
//            sccpStack2.intTimerDelay = 30000;
        }
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionEstablish(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(200);

        assertBothConnectionsExist();

        assertEquals(getConn2().getState(), SccpConnectionState.ESTABLISHED);
        assertEquals(sccpProvider1.getConnections().values().iterator().next().getState(), SccpConnectionState.ESTABLISHED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionEstablish_GT(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        GlobalTitle gt1 = sccpProvider1.getParameterFactory().createGlobalTitle("111111", 0, NumberingPlan.ISDN_TELEPHONY,
                null, NatureOfAddress.INTERNATIONAL);
        GlobalTitle gt2 = sccpProvider1.getParameterFactory().createGlobalTitle("222222", 0, NumberingPlan.ISDN_TELEPHONY,
                null, NatureOfAddress.INTERNATIONAL);
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 0, getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt2, 0, getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        // crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();

        assertEquals(getConn2().getState(), SccpConnectionState.ESTABLISHED);
        assertEquals(sccpProvider1.getConnections().values().iterator().next().getState(), SccpConnectionState.ESTABLISHED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionEstablishWithConfirmData(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();
        u2.getOptions().setSendConfirmData(new byte[] {1, 2, 3});

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();

        assertEquals(getConn2().getState(), SccpConnectionState.ESTABLISHED);
        assertEquals(sccpProvider1.getConnections().values().iterator().next().getState(), SccpConnectionState.ESTABLISHED);

        assertEquals(u1.getReceivedData().size(), 1);
        assertEquals(u1.getReceivedData().iterator().next(), new byte[] {1, 2, 3});
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionRelease(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);


        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), new byte[] {});

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionReset(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();

        assertEquals(getConn2().getState(), SccpConnectionState.ESTABLISHED);
        assertEquals(sccpProvider1.getConnections().values().iterator().next().getState(), SccpConnectionState.ESTABLISHED);

        conn1.reset(new ResetCauseImpl(ResetCauseValue.UNQUALIFIED));
        Thread.sleep(100);

        assertEquals(u1.getResetCount(), 1);
        assertEquals(u2.getResetCount(), 1);
        assertEquals(getConn2().getState(), SccpConnectionState.ESTABLISHED);
        assertEquals(sccpProvider1.getConnections().values().iterator().next().getState(), SccpConnectionState.ESTABLISHED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionRefuseProtocolClass2(boolean onlyOneStack) throws Exception {
        testConnectionRefuse(onlyOneStack, new ProtocolClassImpl(2));
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionRefuseProtocolClass3(boolean onlyOneStack) throws Exception {
        testConnectionRefuse(onlyOneStack, new ProtocolClassImpl(3));
    }

    public void testConnectionRefuse(boolean onlyOneStack, ProtocolClass protocolClass) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());
        u2.setRefuseConnections(true);

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertEquals(u1.getRefusedCount(), 1);
        assertEquals(u2.getRefusedCount(), 1);

        assertEquals(sccpStack2.getConnectionsNumber(), 0);
        assertEquals(sccpStack1.getConnectionsNumber(), 0);

        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionInactivityKeepAliveProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testConnectionInactivityKeepAlive(new ProtocolClassImpl(2));
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionInactivityKeepAliveProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testConnectionInactivityKeepAlive(new ProtocolClassImpl(3));
    }

    private void testConnectionInactivityKeepAlive(ProtocolClass protocolClass) throws Exception {
        this.setIasTimerDelayStack1WithoutChecking(300);
        this.setIarTimerDelayStack2WithoutChecking(1200);

//        sccpStack1.iasTimerDelay = 300;
//        sccpStack2.iarTimerDelay = 1200;

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();

        Thread.sleep(300);

        assertEquals(getConn2().getState(), SccpConnectionState.ESTABLISHED);
        assertEquals(sccpProvider1.getConnections().values().iterator().next().getState(), SccpConnectionState.ESTABLISHED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionInactivityReleaseProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testConnectionInactivityRelease(new ProtocolClassImpl(2));
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testConnectionInactivityReleaseProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testConnectionInactivityRelease(new ProtocolClassImpl(3));
    }

    private void testConnectionInactivityRelease(ProtocolClass protocolClass) throws Exception {
        this.setIasTimerDelayStack1WithoutChecking(1200);
        this.setIarTimerDelayStack2WithoutChecking(300);

//        sccpStack1.iasTimerDelay = 1200;
//        sccpStack2.iarTimerDelay = 300;

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(300);

        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendDataProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        conn1.send(new byte[]{1, 2, 3, 4, 5});

        Thread.sleep(100);

        assertEquals(u2.getReceivedData().size(), 1);
        assertEquals(u2.getReceivedData().iterator().next(), new byte[] {1, 2, 3, 4, 5}); // check if an incoming message content is the same as was sent

        Thread.sleep(100);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), new byte[] {});

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendDataWhenDlnAndSlnDifferClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        conn1.send(new byte[]{1, 2, 3, 4, 5});

        Thread.sleep(100);

        assertEquals(u2.getReceivedData().size(), 1);
        assertEquals(u2.getReceivedData().iterator().next(), new byte[] {1, 2, 3, 4, 5}); // check if an incoming message content is the same as was sent

        Thread.sleep(100);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), new byte[] {});

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendDataWhenSsnDifferClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), 15);
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), 16);

        resource1.removeRemoteSsn(1);
        resource1.addRemoteSsn(1, getStack2PC(), a2.getSubsystemNumber(), 0, false);

        if (!onlyOneStack) {
            resource2.removeRemoteSsn(1);
            resource2.addRemoteSsn(1, getStack1PC(), a1.getSubsystemNumber(), 0, false);
        }

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, a1.getSubsystemNumber());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, a2.getSubsystemNumber());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(a1.getSubsystemNumber(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(a1.getSubsystemNumber(), new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        conn1.send(new byte[]{1, 2, 3, 4, 5});

        Thread.sleep(100);

        assertEquals(u2.getReceivedData().size(), 1);
        assertEquals(u2.getReceivedData().iterator().next(), new byte[] {1, 2, 3, 4, 5}); // check if an incoming message content is the same as was sent

        Thread.sleep(100);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), new byte[] {});

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendDataProtocolClass3(boolean onlyOneStack) throws Exception {
//        this.saveTrafficInFile();
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] { 0x51, 0x53, 0x55, 0x57 }, new ImportanceImpl((byte)1));
        crMsg.setProtocolClass(new ProtocolClassImpl(3));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(3));
        conn1.establish(crMsg);

        Thread.sleep(300); // 100

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        conn1.send(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});

        for (int i=0; i<=127; i++) {
            conn2.send(new byte[]{1, (byte)i, (byte)i, (byte)i, (byte)i});
        }

        conn2.send(new byte[]{1, 2, 3, 4, 5, 6});

        Thread.sleep(2500); // 1600

        assertEquals(u1.getReceivedData().size(), 129);
        for (int i=0; i<=127; i++) {
            assertEquals(u1.getReceivedData().get(i), new byte[]{1, (byte)i, (byte)i, (byte)i, (byte)i});
        }
        assertEquals(u1.getReceivedData().get(128), new byte[] {1, 2, 3, 4, 5, 6}); // check if an incoming message content is the same as was sent

        assertEquals(u2.getReceivedData().size(), 1);
        assertEquals(u2.getReceivedData().iterator().next(), new byte[] {1, 2, 3, 4, 5, 6, 7, 8}); // check if an incoming message content is the same as was sent

        Thread.sleep(200);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), null);

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }


    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendSegmentedDataProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(2));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(2));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        byte[] largeData = new byte[255*3 + 10];
        for (int i = 0; i < 255*3 + 10; i++) {
            largeData[i] = (byte)i;
        }

        conn1.send(largeData);

        Thread.sleep(200);

        assertEquals(u2.getReceivedData().size(), 1);
        assertEquals(u2.getReceivedData().iterator().next(), largeData); //check if an incoming message content is the same as was sent

        Thread.sleep(100);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), new byte[] {});

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendSegmentedDataProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(3));
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(3));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        byte[] largeData = new byte[255*3 + 10];
        for (int i = 0; i < 255*3 + 10; i++) {
            largeData[i] = (byte)i;
        }

        conn1.send(largeData);

        Thread.sleep(200);

        assertEquals(u2.getReceivedData().size(), 1);
        assertEquals(u2.getReceivedData().iterator().next(), largeData); //check if an incoming message content is the same as was sent

        Thread.sleep(100);

        conn1.disconnect(new ReleaseCauseImpl(ReleaseCauseValue.UNQUALIFIED), new byte[] {});

        Thread.sleep(200);

        assertEquals(sccpStack1.getConnectionsNumber(), 0);
        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn2.getState(), SccpConnectionState.CLOSED);
        assertEquals(conn1.getState(), SccpConnectionState.CLOSED);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testIncreaseCreditByUserProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testChangeCreditByUserProtocolClass3(100, 127);
    }

    @Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testDecreaseCreditByUserProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testChangeCreditByUserProtocolClass3(127, 100);
    }

    private void testChangeCreditByUserProtocolClass3(int initialCredit, int finalCredit) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        u2.getOptions().confirmCredit = new CreditImpl(finalCredit);

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(8, a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(new ProtocolClassImpl(3));
        crMsg.setCredit(new CreditImpl(initialCredit));

        SccpConnection conn1 = sccpProvider1.newConnection(8, new ProtocolClassImpl(3));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        assertEquals(conn1.getReceiveCredit().getValue(), finalCredit);
        assertEquals(conn1.getSendCredit().getValue(), finalCredit);
        assertEquals(conn2.getReceiveCredit().getValue(), finalCredit);
        assertEquals(conn2.getSendCredit().getValue(), finalCredit);
    }
}
