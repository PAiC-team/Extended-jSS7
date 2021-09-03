
package org.restcomm.protocols.ss7.sccpext.impl.messageflow;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.impl.SccpHarnessExt;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImplProxy;
import org.restcomm.protocols.ss7.sccp.impl.User;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageSegmentationTest;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpNoticeMessage;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MessageTransferTest extends SccpHarnessExt {

    private SccpAddress a1, a2;

    public MessageTransferTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        this.sccpStack1Name = "MessageTransferTestStack1";
        this.sccpStack2Name = "MessageTransferTestStack2";
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @Override
    protected void createStack1() {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        sccpExtModule1 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule1);

        sccpStack1 = createStack(sccpStack1Name, ss7ExtInterface);
        sccpProvider1 = sccpStack1.getSccpProvider();
        sccpStack1.start();
    }

    @Override
    protected void createStack2() {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        sccpExtModule2 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule2);

        sccpStack2 = createStack(sccpStack2Name, ss7ExtInterface);
        sccpProvider2 = sccpStack2.getSccpProvider();
        sccpStack2.start();
    }

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

        a1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), 8);
        a2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), 8);

        User u1 = new User(sccpStack1.getSccpProvider(), a1, a2, getSSN());
        User u2 = new User(sccpStack2.getSccpProvider(), a2, a1, getSSN());

        u1.register();
        u2.register();

        Thread.sleep(100);

        // a try of transfer a UDT message with a big length : UDTS should
        // return locally
        sccpStack1.getRouter().addLongMessageRule(1, 2, 2, LongMessageRuleType.LONG_MESSAGE_FORBBIDEN);
        SccpDataMessage message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a2, a1,
                MessageSegmentationTest.getDataA(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 1);
        assertEquals(u2.getMessages().size(), 0);
        SccpNoticeMessage nMsg = (SccpNoticeMessage) u1.getMessages().get(0);
        assertEquals(nMsg.getReturnCause().getValue(), ReturnCauseValue.SEG_NOT_SUPPORTED);

        // transfer a UDT message: U1 -> U2
        message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a2, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 1);
        assertEquals(u2.getMessages().size(), 1);
        SccpDataMessage dMsg = (SccpDataMessage) u2.getMessages().get(0);
        assertTrue(Arrays.equals(dMsg.getData(), getDataSrc()));
        assertEquals(dMsg.getType(), SccpMessage.MESSAGE_TYPE_UDT);

        // transfer a UDT message: U1 -> U1
        message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a1, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 2);
        assertEquals(u2.getMessages().size(), 1);
        dMsg = (SccpDataMessage) u1.getMessages().get(1);
        assertTrue(Arrays.equals(dMsg.getData(), getDataSrc()));
        assertEquals(dMsg.getType(), SccpMessage.MESSAGE_TYPE_UNDEFINED); // message
                                                                          // type
                                                                          // has
                                                                          // not
                                                                          // assigned
                                                                          // (there
                                                                          // was
                                                                          // no
                                                                          // transfer
                                                                          // via
                                                                          // MTP3)

        // attempt to transfer a UDT message: U1 -> U1(unregistered ssn) ->
        // error
        SccpAddress a1_1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack1PC(), 18);
        message = this.sccpProvider1.getMessageFactory()
                .createDataMessageClass1(a1_1, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 3);
        assertEquals(u2.getMessages().size(), 1);
        nMsg = (SccpNoticeMessage) u1.getMessages().get(2);
        assertTrue(Arrays.equals(nMsg.getData(), getDataSrc()));
        assertEquals(nMsg.getReturnCause().getValue(), ReturnCauseValue.SUBSYSTEM_FAILURE);

        // attempt to transfer a UDT message: U1 -> U2(unregistered ssn at U1)
        // -> error
        SccpAddress a2_1 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, getStack2PC(), 18);
        message = this.sccpProvider1.getMessageFactory()
                .createDataMessageClass1(a2_1, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 4);
        assertEquals(u2.getMessages().size(), 1);
        nMsg = (SccpNoticeMessage) u1.getMessages().get(3);
        assertTrue(Arrays.equals(nMsg.getData(), getDataSrc()));
        assertEquals(nMsg.getReturnCause().getValue(), ReturnCauseValue.SCCP_FAILURE);

        // transfer a splitted XUDT message: U1 -> U2 - success
        sccpStack1.getRouter().removeLongMessageRule(1);
        sccpStack1.getRouter().addLongMessageRule(1, 2, 2, LongMessageRuleType.XUDT_ENABLED);
        message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a2, a1, MessageSegmentationTest.getDataA(), 0,
                8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 4);
        assertEquals(u2.getMessages().size(), 2);
        dMsg = (SccpDataMessage) u2.getMessages().get(1);
        assertTrue(Arrays.equals(dMsg.getData(), MessageSegmentationTest.getDataA()));
        assertEquals(dMsg.getType(), SccpMessage.MESSAGE_TYPE_XUDT);

        // transfer a long LUDT message: U1 -> U2 - success
        sccpStack1.getRouter().removeLongMessageRule(1);
        sccpStack1.getRouter().addLongMessageRule(1, 2, 2, LongMessageRuleType.LUDT_ENABLED);
        message = this.sccpProvider1.getMessageFactory().createDataMessageClass1(a2, a1, MessageSegmentationTest.getDataA(), 0,
                8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 4);
        assertEquals(u2.getMessages().size(), 3);
        dMsg = (SccpDataMessage) u2.getMessages().get(2);
        assertTrue(Arrays.equals(dMsg.getData(), MessageSegmentationTest.getDataA()));
        assertEquals(dMsg.getType(), SccpMessage.MESSAGE_TYPE_LUDT);

        // attempt to transfer a LUDT message: U1 -> U2 - bad translation at U1
        GlobalTitle gt1 = sccpProvider1.getParameterFactory().createGlobalTitle("12345",NatureOfAddress.NATIONAL);
        SccpAddress a2_2 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 0, 18);
        message = this.sccpProvider1.getMessageFactory()
                .createDataMessageClass1(a2_2, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 5);
        assertEquals(u2.getMessages().size(), 3);
        nMsg = (SccpNoticeMessage) u1.getMessages().get(4);
        assertTrue(Arrays.equals(nMsg.getData(), getDataSrc()));
        assertEquals(nMsg.getReturnCause().getValue(), ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS);

        // attempt to transfer a LUDT message: U1 -> U2 - bad translation at U2
        SccpAddress a2_3 = sccpProvider1.getParameterFactory().createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, getStack2PC(), 18);
        message = this.sccpProvider1.getMessageFactory()
                .createDataMessageClass1(a2_3, a1, getDataSrc(), 0, 8, true, null, null);
        sccpProvider1.send(message);
        Thread.sleep(100);
        assertEquals(u1.getMessages().size(), 6);
        assertEquals(u2.getMessages().size(), 3);
        nMsg = (SccpNoticeMessage) u1.getMessages().get(5);
        assertTrue(Arrays.equals(nMsg.getData(), getDataSrc()));
        assertEquals(nMsg.getReturnCause().getValue(), ReturnCauseValue.NO_TRANSLATION_FOR_ADDRESS);
    }
}
