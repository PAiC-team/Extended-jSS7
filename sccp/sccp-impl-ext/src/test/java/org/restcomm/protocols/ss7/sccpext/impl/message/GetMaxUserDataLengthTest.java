
package org.restcomm.protocols.ss7.sccpext.impl.message;

import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.impl.Mtp3UserPartImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class GetMaxUserDataLengthTest {

    private SccpStackImpl stack;
    private SccpExtModuleImpl sccpExtModule;

    @BeforeMethod
    public void setUp() {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        sccpExtModule = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule);
        stack = new SccpStackImpl("TestStack", ss7ExtInterface);
        stack.setPersistDir(Util.getTmpTestDir());
        stack.start();
        stack.removeAllResources();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(groups = { "SccpMessage", "MessageLength" })
    public void testMessageLength() throws Exception {

        SccpAddress a1 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null,2,  8);
        SccpAddress a2 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, stack.getSccpProvider().getParameterFactory().createGlobalTitle("1122334455",0) ,0 ,18);

        Mtp3UserPartImpl_2 mtp3UserPart = new Mtp3UserPartImpl_2();
        stack.setMtp3UserPart(1, mtp3UserPart);
        stack.getRouter().addMtp3ServiceAccessPoint(1, 1, 1, 2, 0, null);

        stack.getRouter().addMtp3Destination(1, 1, 2, 2, 0, 255, 255);

        SccpAddress primaryAddress = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, stack.getSccpProvider().getParameterFactory().createGlobalTitle("1122334455",0),2,
                 18);

        sccpExtModule.getRouterExt().addRoutingAddress(1, primaryAddress);
        SccpAddress pattern = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, stack.getSccpProvider().getParameterFactory().createGlobalTitle("1122334455",0), 2, 18);
        sccpExtModule.getRouterExt().addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Undefined, OriginationType.ALL, pattern, "K", 1,
                -1, null, 0, null);

        int len = stack.getSccpProvider().getMaxUserDataLength(a1, a2, 0);
        assertEquals(len, 248);

        len = stack.getSccpProvider().getMaxUserDataLength(a2, a1, 0);
        assertEquals(len, 248);

        stack.getRouter().addLongMessageRule(1, 2, 2, LongMessageRuleType.XUDT_ENABLED);

        len = stack.getSccpProvider().getMaxUserDataLength(a1, a2, 0);
        assertEquals(len, 2560);
        stack.getRouter().removeLongMessageRule(1);
        stack.getRouter().addLongMessageRule(1, 2, 2, LongMessageRuleType.LUDT_ENABLED);

        len = stack.getSccpProvider().getMaxUserDataLength(a1, a2, 0);
        assertEquals(len, 231);

        mtp3UserPart.setMtpMsgLen(4000);
        len = stack.getSccpProvider().getMaxUserDataLength(a1, a2, 0);
        assertEquals(len, 2560);

    }

    private class Mtp3UserPartImpl_2 extends Mtp3UserPartImpl {

        public Mtp3UserPartImpl_2() {
            super(null);
        }

        private int mtpMsgLen = 268;

        public void setMtpMsgLen(int mtpMsgLen) {
            this.mtpMsgLen = mtpMsgLen;
        }

        @Override
        public int getMaxUserDataLength(int dpc) {
            return mtpMsgLen;
        }

    }
}
