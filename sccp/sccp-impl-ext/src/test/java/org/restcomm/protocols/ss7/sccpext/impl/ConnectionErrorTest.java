
package org.restcomm.protocols.ss7.sccpext.impl;

import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.SccpConnection;
import org.restcomm.protocols.ss7.sccp.SccpConnectionState;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.Mtp3UserPartImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpConnectionBaseImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpHarnessExt;
import org.restcomm.protocols.ss7.sccp.impl.User;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnDt2MessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnItMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnRlcMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnRlsdMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnRscMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnRsrMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.CreditImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ImportanceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.LocalReferenceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ProtocolClassImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ReleaseCauseImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ResetCauseImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SequenceNumberImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SequencingSegmentingImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpConnCrMessage;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.ReleaseCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.restcomm.protocols.ss7.sccp.SccpConnectionState.CLOSED;
import static org.restcomm.protocols.ss7.sccp.SccpConnectionState.ESTABLISHED;
import static org.restcomm.protocols.ss7.sccp.parameter.ErrorCauseValue.LRN_MISMATCH_INCONSISTENT_SOURCE_LRN;
import static org.restcomm.protocols.ss7.sccp.parameter.ReleaseCauseValue.SCCP_FAILURE;
import static org.restcomm.protocols.ss7.sccp.parameter.ReleaseCauseValue.SUBSYSTEM_FAILURE;
import static org.testng.Assert.assertEquals;

public class ConnectionErrorTest extends SccpHarnessExt {

    private SccpAddress a1, a2;

    public ConnectionErrorTest() {
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
        this.sccpStack1Name = "ConnectionErrorTestStack1";
        this.sccpStack2Name = "ConnectionErrorTestStack2";
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp(Object[] testArgs) throws Exception {
        boolean onlyOneStack = false;
        if (testArgs.length > 0) {
            onlyOneStack = (Boolean) testArgs[0];
        }
        this.onlyOneStack = onlyOneStack;

        mtp3UserPart1 = new Mtp3UserPartImpl(this);
        mtp3UserPart2 = new Mtp3UserPartImpl(this);

        super.setUp();

        if (onlyOneStack) {
            sccpStack2 = sccpStack1;
            sccpProvider2 = sccpProvider1;
            sccpStack2Name = sccpStack1Name;
            ssn2 = 7;

            mtp3UserPart1.setOtherPart(mtp3UserPart1);
            mtp3UserPart1.addDpc(getStack1PC());

            sccpStack1.setMtp3UserPart(1, mtp3UserPart1);
        } else {
            mtp3UserPart1.setOtherPart(mtp3UserPart2);
            mtp3UserPart2.setOtherPart(mtp3UserPart1);

            sccpStack1.setMtp3UserPart(1, mtp3UserPart1);
            sccpStack2.setMtp3UserPart(1, mtp3UserPart2);
        }
    }

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }

    protected int getStack2PC() {
        if (onlyOneStack) {
            return getStack1PC();
        }

        if (sccpStack1.getSccpProtocolVersion() == SccpProtocolVersion.ANSI)
            return 8000002;
        else
            return 2;
    }

    private void stackParameterInit() {
        this.setReferenceNumberCounterStack1WithoutChecking(20);
        this.setReferenceNumberCounterStack2WithoutChecking(50);

        this.setIasTimerDelayStack1WithoutChecking(7500 * 60);
        this.setIarTimerDelayStack1WithoutChecking(16000 * 60);
        this.setIasTimerDelayStack2WithoutChecking(7500 * 60);
        this.setIarTimerDelayStack2WithoutChecking(16000 * 60);

        this.setSstTimerDuration_MinStack1WithoutChecking(10000);
        this.setSstTimerDuration_MinStack2WithoutChecking(10000);

        this.setRelTimerDelayStack1WithoutChecking(15000);
        this.setRepeatRelTimerDelayStack1WithoutChecking(15000);
        this.setIntTimerDelayStack1WithoutChecking(30000);

        this.setRelTimerDelayStack2WithoutChecking(15000);
        this.setRepeatRelTimerDelayStack2WithoutChecking(15000);
        this.setIntTimerDelayStack2WithoutChecking(30000);

//        sccpStack1.referenceNumberCounter = 20;
//        sccpStack2.referenceNumberCounter = 50;
//
//        sccpStack1.iasTimerDelay = 7500 * 60;
//        sccpStack1.iarTimerDelay = 16000 * 60;
//        sccpStack2.iasTimerDelay = 7500 * 60;
//        sccpStack2.iarTimerDelay = 16000 * 60;
//
//        sccpStack1.sstTimerDuration_Min = 10000;
//        sccpStack2.sstTimerDuration_Min = 10000;
//
//        sccpStack1.relTimerDelay = 15000;
//        sccpStack1.repeatRelTimerDelay = 15000;
//        sccpStack1.intTimerDelay = 30000;

//        sccpStack2.relTimerDelay = 15000;
//        sccpStack2.repeatRelTimerDelay = 15000;
//        sccpStack2.intTimerDelay = 30000;
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRlsdErrProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRlsdErr(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRlsdErrProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRlsdErr(new ProtocolClassImpl(3));
    }

    public void testRlsdErr(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        SccpConnRlsdMessageImpl msg = new SccpConnRlsdMessageImpl(conn1.getSls(), conn1.getLocalSsn());
        msg.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        msg.setReleaseCause(new ReleaseCauseImpl(ReleaseCauseValue.END_USER_ORIGINATED));
        msg.setSourceLocalReferenceNumber(new LocalReferenceImpl(100)); // wrong number
        msg.setUserData(new byte[] {1, 1, 1, 1, 1});
        msg.setOutgoingDpc(getStack2PC());

        sccpStack1.getSccpRoutingControl().sendConn(msg);

        Thread.sleep(200);

        if (!onlyOneStack) {
            assertEquals(sccpStack1.getConnectionsNumber(), 0);
            assertEquals(sccpStack2.getConnectionsNumber(), 1);
        } else {
            assertEquals(sccpStack1.getConnectionsNumber(), 1);
        }

        assertEquals(conn1.getState(), CLOSED);
        assertEquals(conn2.getState(), SccpConnectionState.ESTABLISHED); // will be closed later due to no messages received timeout
        assertEquals(u1.getStats().getDisconnectError().getValue(), LRN_MISMATCH_INCONSISTENT_SOURCE_LRN);
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRsrErrProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRsrErr(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRsrErrProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRsrErr(new ProtocolClassImpl(3));
    }

    public void testRsrErr(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        SccpConnRsrMessageImpl rsr = new SccpConnRsrMessageImpl(conn1.getSls(), conn1.getLocalSsn());
        rsr.setSourceLocalReferenceNumber(new LocalReferenceImpl(100));
        rsr.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        rsr.setResetCause(new ResetCauseImpl(ResetCauseValue.END_USER_ORIGINATED));
        rsr.setOutgoingDpc(getStack2PC());

        sccpStack1.getSccpRoutingControl().sendConn(rsr);

        Thread.sleep(200);

        if (!onlyOneStack) {
            assertEquals(sccpStack1.getConnectionsNumber(), 0);
            assertEquals(sccpStack2.getConnectionsNumber(), 1);
        } else {
            assertEquals(sccpStack1.getConnectionsNumber(), 1);
        }

        assertEquals(conn1.getState(), CLOSED);
        assertEquals(conn2.getState(), SccpConnectionState.ESTABLISHED); // will be closed later due to no messages received timeout
        assertEquals(u1.getStats().getDisconnectError().getValue(), LRN_MISMATCH_INCONSISTENT_SOURCE_LRN);
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRscErrProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRscErr(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRscErrProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRscErr(new ProtocolClassImpl(3));
    }

    public void testRscErr(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        SccpConnRscMessageImpl rsc = new SccpConnRscMessageImpl(conn1.getSls(), conn1.getLocalSsn());
        rsc.setSourceLocalReferenceNumber(new LocalReferenceImpl(100));
        rsc.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        rsc.setOutgoingDpc(getStack2PC());

        sccpStack1.getSccpRoutingControl().sendConn(rsc);

        Thread.sleep(200);

        if (!onlyOneStack) {
            assertEquals(sccpStack1.getConnectionsNumber(), 0);
            assertEquals(sccpStack2.getConnectionsNumber(), 1);
        } else {
            assertEquals(sccpStack1.getConnectionsNumber(), 1);
        }

        assertEquals(conn1.getState(), CLOSED);
        assertEquals(conn2.getState(), SccpConnectionState.ESTABLISHED); // will be closed later due to no messages received timeout
        assertEquals(u1.getStats().getDisconnectError().getValue(), LRN_MISMATCH_INCONSISTENT_SOURCE_LRN);
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testItErrProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testItErr(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testItErrProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testItErr(new ProtocolClassImpl(3));
    }

    public void testItErr(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        SccpConnItMessageImpl it = new SccpConnItMessageImpl(conn1.getSls(), conn1.getLocalSsn());
        it.setProtocolClass(protocolClass);
        it.setSourceLocalReferenceNumber(new LocalReferenceImpl(100));
        it.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        it.setOutgoingDpc(getStack2PC());
        it.setCredit(new CreditImpl(0));
        it.setSequencingSegmenting(new SequencingSegmentingImpl(new SequenceNumberImpl(0, false),
                new SequenceNumberImpl(0, false), false));

        sccpStack1.getSccpRoutingControl().sendConn(it);

        Thread.sleep(200);

        if (!onlyOneStack) {
            assertEquals(sccpStack1.getConnectionsNumber(), 0);
            assertEquals(sccpStack2.getConnectionsNumber(), 1);
        } else {
            assertEquals(sccpStack1.getConnectionsNumber(), 1);
        }

        assertEquals(conn1.getState(), CLOSED);
        assertEquals(conn2.getState(), SccpConnectionState.ESTABLISHED); // will be closed later due to no messages received timeout
        assertEquals(u1.getStats().getDisconnectError().getValue(), LRN_MISMATCH_INCONSISTENT_SOURCE_LRN);
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRlcErrProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRlcErr(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testRlcErrProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testRlcErr(new ProtocolClassImpl(3));
    }

    public void testRlcErr(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        Thread.sleep(100);

        SccpConnRlcMessageImpl rlc = new SccpConnRlcMessageImpl(conn1.getSls(), conn1.getLocalSsn());
        rlc.setSourceLocalReferenceNumber(new LocalReferenceImpl(100));
        rlc.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        rlc.setOutgoingDpc(getStack2PC());

        sccpStack1.getSccpRoutingControl().sendConn(rlc);

        Thread.sleep(200);

        // RLC message was discarded
        if (!onlyOneStack) {
            assertEquals(sccpStack1.getConnectionsNumber(), 1);
            assertEquals(sccpStack2.getConnectionsNumber(), 1);
        } else {
            assertEquals(sccpStack1.getConnectionsNumber(), 2);
        }

        assertEquals(conn1.getState(), ESTABLISHED);
        assertEquals(conn2.getState(), ESTABLISHED);
        assertNull(u1.getStats().getDisconnectError());
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testReleaseDueToErrorProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testReleaseDueToError(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testReleaseDueToErrorProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testReleaseDueToError(new ProtocolClassImpl(3));
    }

    public void testReleaseDueToError(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        crMsg.setCredit(new CreditImpl(100));

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = sccpProvider2.getConnections().values().iterator().next();

        u2.deregister(); // there will be no listener for SSN=8 and this will cause error when receiving next messages

        Thread.sleep(100);

        conn1.send(new byte[] {1, 1, 1, 1, 1});

        Thread.sleep(200);

        if (!onlyOneStack) {
            assertEquals(sccpStack1.getConnectionsNumber(), 0);
        } else {
            assertEquals(sccpStack1.getConnectionsNumber(), 1); // conn2
        }
//        assertEquals(sccpStack2.getConnectionsNumber(), 0);

        assertEquals(conn1.getState(), CLOSED);
//        assertEquals(conn2.getState(), CLOSED); // will be closed later due to no messages received timeout
        assertEquals(u1.getStats().getReleaseCause().getValue(), SUBSYSTEM_FAILURE);
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendDataNoConnectionProtocolClass2(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testSendDataNoConnection(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testSendDataNoConnectionProtocolClass3(boolean onlyOneStack) throws Exception {
        stackParameterInit();
        testSendDataNoConnection(new ProtocolClassImpl(3));
    }

    private void testSendDataNoConnection(ProtocolClass protocolClass) throws Exception {
        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        if (protocolClass.getProtocolClass() == 3) {
            crMsg.setCredit(new CreditImpl(100));
        }

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();

        sccpProvider2.getConnections().clear();

        Thread.sleep(100);

        conn1.send(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});

        Thread.sleep(100);

        // i. e. message was discarded
        assertTrue(u2.getReceivedData().isEmpty());
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" })
    public void testSendDataChannelIsDownProtocolClass2() throws Exception {
        //no dataProvider = "ConnectionTestDataProvider" because this test needs network
        stackParameterInit();
        testSendDataNoConnection(new ProtocolClassImpl(2));
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" })
    public void testSendDataChannelIsDownProtocolClass3() throws Exception {
        //no dataProvider = "ConnectionTestDataProvider" because this test needs network
        stackParameterInit();
        testSendDataChannelIsDown(new ProtocolClassImpl(3));
    }

    private void testSendDataChannelIsDown(ProtocolClass protocolClass) throws Exception {
        this.setSstTimerDuration_MinStack1WithoutChecking(100 * 1000);
        this.setSstTimerDuration_MinStack2WithoutChecking(100 * 1000);

        this.setRelTimerDelayStack1WithoutChecking(100);
        this.setRepeatRelTimerDelayStack1WithoutChecking(100);

        this.setRelTimerDelayStack2WithoutChecking(100);
        this.setRepeatRelTimerDelayStack2WithoutChecking(100);

        this.setIntTimerDelayStack1WithoutChecking(100);
        this.setIntTimerDelayStack2WithoutChecking(100);

        this.setIarTimerDelayStack1WithoutChecking(500);

//        sccpStack1.sstTimerDuration_Min = 100*1000;
//        sccpStack2.sstTimerDuration_Min = 100*1000;
//
//        sccpStack1.relTimerDelay = 100;
//        sccpStack1.repeatRelTimerDelay = 100;
//
//        sccpStack2.relTimerDelay = 100;
//        sccpStack2.repeatRelTimerDelay = 100;
//
//        sccpStack1.intTimerDelay = 100;
//        sccpStack2.intTimerDelay = 100;
//
//        sccpStack2.iarTimerDelay = 500;

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), getSSN());
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), getSSN2());

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN2());

        u1.register();
        u2.register();

        Thread.sleep(100);

        SccpConnCrMessage crMsg = sccpProvider1.getMessageFactory().createConnectMessageClass2(getSSN(), a2, a1, new byte[] {}, new ImportanceImpl((byte)1));
        crMsg.setSourceLocalReferenceNumber(new LocalReferenceImpl(1));
        crMsg.setProtocolClass(protocolClass);
        if (protocolClass.getProtocolClass() == 3) {
            crMsg.setCredit(new CreditImpl(100));
        }

        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), protocolClass);
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
        SccpConnection conn2 = getConn2();

        this.getSccpManagement1().handleMtp3Pause(getStack2PC());
        this.getSccpManagement2().handleMtp3Pause(getStack1PC());

        Thread.sleep(100);

        conn1.send(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});

        Thread.sleep(500);

        assertEquals(conn1.getState(), CLOSED);
        assertNotNull(u1.getStats().getReleaseCause());
        assertEquals(u1.getStats().getReleaseCause().getValue(), SCCP_FAILURE);

        Thread.sleep(500);
        assertEquals(conn2.getState(), CLOSED);
        assertNotNull(u2.getStats().getReleaseCause());
        assertEquals(u2.getStats().getReleaseCause().getValue(), SCCP_FAILURE);


        assertTrue(u2.getReceivedData().isEmpty());
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testBadSequenceNumberProtocolClass3(boolean onlyOneStack) throws Exception {
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
        crMsg.setProtocolClass(new ProtocolClassImpl(3));
        crMsg.setCredit(new CreditImpl(100));


        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), new ProtocolClassImpl(3));
        conn1.establish(crMsg);

        Thread.sleep(200);

        assertBothConnectionsExist();
//        SccpConnection conn2 = sccpProvider2.getConnections().values().iterator().next();

        Thread.sleep(100);

        SccpConnDt2MessageImpl dataMessage = new SccpConnDt2MessageImpl(255, conn1.getSls(), conn1.getLocalSsn());
        dataMessage.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        dataMessage.setSourceLocalReferenceNumber(conn1.getLocalReference());
        dataMessage.setUserData(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
        dataMessage.setMoreData(false);

        ((SccpConnectionBaseImpl)conn1).prepareMessageForSending(dataMessage);
        int originalSendSequenceNumber = dataMessage.getSequencingSegmenting().getSendSequenceNumber().getValue();
        dataMessage.getSequencingSegmenting().setSendSequenceNumber(new SequenceNumberImpl(originalSendSequenceNumber + 1, false));

        sccpStack1.getSccpRoutingControl().routeMsgFromSccpUserConn(dataMessage);
        Thread.sleep(200);

        assertEquals(u1.getResetCount(), 1);
        assertEquals(u2.getResetCount(), 1);
        assertTrue(u2.getReceivedData().isEmpty());
    }

    @org.testng.annotations.Test(groups = { "SccpMessage", "functional.connection" }, dataProvider = "ConnectionTestDataProvider")
    public void testBadSequenceConfirmationProtocolClass3(boolean onlyOneStack) throws Exception {
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
        crMsg.setProtocolClass(new ProtocolClassImpl(3));
        crMsg.setCredit(new CreditImpl(100));


        SccpConnection conn1 = sccpProvider1.newConnection(getSSN(), new ProtocolClassImpl(3));
        conn1.establish(crMsg);

        Thread.sleep(100);

        assertBothConnectionsExist();
//        SccpConnection conn2 = sccpProvider2.getConnections().values().iterator().next();

        Thread.sleep(100);

        SccpConnDt2MessageImpl dataMessage = new SccpConnDt2MessageImpl(255, conn1.getSls(), conn1.getLocalSsn());
        dataMessage.setDestinationLocalReferenceNumber(conn1.getRemoteReference());
        dataMessage.setSourceLocalReferenceNumber(conn1.getLocalReference());
        dataMessage.setUserData(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
        dataMessage.setMoreData(false);

        ((SccpConnectionBaseImpl)conn1).prepareMessageForSending(dataMessage);
        int originalConfirmationNumber = dataMessage.getSequencingSegmenting().getReceiveSequenceNumber().getValue();
        dataMessage.getSequencingSegmenting().setReceiveSequenceNumber(new SequenceNumberImpl(originalConfirmationNumber + 1, false));

        sccpStack1.getSccpRoutingControl().routeMsgFromSccpUserConn(dataMessage);
        Thread.sleep(200);

        assertEquals(u1.getResetCount(), 1);
        assertEquals(u2.getResetCount(), 1);
        assertTrue(u2.getReceivedData().isEmpty());
    }
}
