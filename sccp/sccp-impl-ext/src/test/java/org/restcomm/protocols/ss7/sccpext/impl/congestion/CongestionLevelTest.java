
package org.restcomm.protocols.ss7.sccpext.impl.congestion;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.mtp.Mtp3StatusCause;
import org.restcomm.protocols.ss7.mtp.Mtp3StatusPrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitiveFactory;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPartListener;
import org.restcomm.protocols.ss7.mtp.RoutingLabelFormat;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.RemoteSccpStatus;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpListener;
import org.restcomm.protocols.ss7.sccp.SignallingPointStatus;
import org.restcomm.protocols.ss7.sccp.impl.BaseSccpListener;
import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.BCDEvenEncodingScheme;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0100Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.impl.router.RouterImpl;
import org.restcomm.protocols.ss7.sccp.message.MessageFactory;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpNoticeMessage;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.sccpext.impl.router.RouterExtImpl;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Sergey Vetyutnev
 *
 */
public class CongestionLevelTest {

    private int TIMER_A = 100;
    private int TIMER_D = 500;
    private int N = 8;
    private int M = 4;
    private int EXTRA_DELAY = 10;

    private SccpStackImpl sccpStack;
    private RouterImpl router;
    private RouterExtImpl routerExt;
    private SccpListenerProxy listenerProxy;
    private MessageFactory messageFactory;

    @BeforeMethod
    public void setUpClass() throws Exception {
        // BasicConfigurator.configure();
        Properties properties = new Properties();
        properties.setProperty("log4j.threshold", "DEBUG");
//        properties.setProperty("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
//        properties.setProperty("log4j.appender.A1.Target", "System.out");
//        properties.setProperty("log4j.appender.A1.Threshold", "DEBUG");
//        properties.setProperty("log4j.appender.A1.layout", "org.apache.log4j.PatternLayout");
//        properties.setProperty("log4j.appender.A1.layout.ConversionPattern", "=%-4r %-5p %c{2} %M.%L %x - %m\n");
//        properties.setProperty("log4j.rootLogger", "DEBUG, A1");
//        properties.setProperty("log4j.logger.org.mobicents", "DEBUG");
//        PropertyConfigurator.configure(properties);

        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        SccpExtModuleImpl sccpExtModule1 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule1);
        sccpStack = new SccpStackImpl("TestSccp", ss7ExtInterface);
        sccpStack.start();
        sccpStack.removeAllResources();
        router = (RouterImpl) sccpStack.getRouter();
        routerExt = (RouterExtImpl) sccpExtModule1.getRouterExt();

        GlobalTitle gt = new GlobalTitle0100Impl("*", 0, new BCDEvenEncodingScheme(), NumberingPlan.ISDN_TELEPHONY,
                NatureOfAddress.INTERNATIONAL);
        SccpAddress pattern = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, 8);
        GlobalTitle gt1 = new GlobalTitle0100Impl("-", 0, new BCDEvenEncodingScheme(), NumberingPlan.ISDN_TELEPHONY,
                NatureOfAddress.INTERNATIONAL);
        SccpAddress sccpAddress1 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 101, 8);
        routerExt.addRoutingAddress(1, sccpAddress1);
        routerExt.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.ALL, pattern, "K", 1, -1, null, 1, pattern);

        listenerProxy = new SccpListenerProxy();
        sccpStack.getSccpProvider().registerSccpListener(8, listenerProxy);

        sccpStack.setCongControlTIMER_A(TIMER_A);
        sccpStack.setCongControlTIMER_D(TIMER_D);
        sccpStack.setCongControlN(N);
        sccpStack.setCongControlM(M);
        sccpStack.setCongControl_blockingOutgoingSccpMessages(true);

        messageFactory = new MessageFactoryImpl(sccpStack);
    }

    @Test
    public void testCongestionLevel1() throws Exception {
        int affectedDpc = 101;
        Mtp3StatusPrimitive msg = new Mtp3StatusPrimitive(affectedDpc, Mtp3StatusCause.SignallingNetworkCongested, 1, 3);

        sccpStack.getSccpResource().addRemoteSpc(1, 101, 0, 0);
        RemoteSignalingPointCodeImpl rspc1 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(1);

        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 1);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 2);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 3);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 1);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);

        Thread.sleep(TIMER_D + EXTRA_DELAY);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 3);

        Thread.sleep(TIMER_D);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 2);

        Thread.sleep(TIMER_D);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 1);

        Thread.sleep(TIMER_D);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);

        Thread.sleep(TIMER_D);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
    }

    @Test
    public void testCongestionLevel2() throws Exception {
        int affectedDpc = 101;
        Mtp3StatusPrimitive msg = new Mtp3StatusPrimitive(affectedDpc, Mtp3StatusCause.SignallingNetworkCongested, 1, 3);

        sccpStack.getSccpResource().addRemoteSpc(1, 101, 0, 0);
        RemoteSignalingPointCodeImpl rspc1 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(1);

        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        assertEquals(listenerProxy.getNI_NISList().size(), 0);

        FastMap<Integer, NetworkIdState> lstState = this.sccpStack.getSccpProvider().getNetworkIdStateList();
        assertEquals(lstState.size(), 1);
        NetworkIdState nis = lstState.get(1);
        assertEquals(nis.getCongLevel(), 0);
        assertTrue(nis.isAvailable());

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 1);
        assertEquals(listenerProxy.getNI_NISList().size(), 0);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 2);
        assertEquals(listenerProxy.getNI_NISList().size(), 0);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 3);
        assertEquals(listenerProxy.getNI_NISList().size(), 0);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 1);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        assertEquals(listenerProxy.getNI_NISList().size(), 1);
        assertTrue(listenerProxy.getNI_NISList().get(0).networkIdState.isAvailable());
        assertEquals(listenerProxy.getNI_NISList().get(0).networkIdState.getCongLevel(), 0);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 1);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 1);
        assertEquals(listenerProxy.getNI_NISList().size(), 1);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 1);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 2);
        assertEquals(listenerProxy.getNI_NISList().size(), 1);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 1);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 3);
        assertEquals(listenerProxy.getNI_NISList().size(), 1);

        Thread.sleep(TIMER_A + EXTRA_DELAY);

        sccpStack.onMtp3StatusMessage(msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 2);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        assertEquals(listenerProxy.getNI_NISList().size(), 2);
        assertTrue(listenerProxy.getNI_NISList().get(1).networkIdState.isAvailable());
        assertEquals(listenerProxy.getNI_NISList().get(1).networkIdState.getCongLevel(), 1);

        lstState = this.sccpStack.getSccpProvider().getNetworkIdStateList();
        assertEquals(lstState.size(), 1);
        nis = lstState.get(1);
        assertEquals(nis.getCongLevel(), 1);
        assertTrue(nis.isAvailable());

        // decreasing of level
        Thread.sleep(TIMER_D * 4 + EXTRA_DELAY);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 1);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        assertEquals(listenerProxy.getNI_NISList().size(), 3);
        assertTrue(listenerProxy.getNI_NISList().get(2).networkIdState.isAvailable());
        assertEquals(listenerProxy.getNI_NISList().get(2).networkIdState.getCongLevel(), 0);

        Thread.sleep(TIMER_D * 4);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        assertEquals(listenerProxy.getNI_NISList().size(), 4);
        assertTrue(listenerProxy.getNI_NISList().get(3).networkIdState.isAvailable());
        assertEquals(listenerProxy.getNI_NISList().get(3).networkIdState.getCongLevel(), 0);
    }

    @Test
    public void testCongestionLevelSendingOfSccpMessageRestriction() throws Exception {
        int affectedDpc = 101;
        Mtp3StatusPrimitive msg = new Mtp3StatusPrimitive(affectedDpc, Mtp3StatusCause.SignallingNetworkCongested, 1, 3);

        Mtp3UserPartProxy mtp3UserPart = new Mtp3UserPartProxy();
        sccpStack.setMtp3UserPart(1, mtp3UserPart);
        router.addMtp3ServiceAccessPoint(1, 1, 201, 0, 0, null);
        router.addMtp3Destination(1, 1, affectedDpc, affectedDpc, 0, 0, 255);

        sccpStack.getSccpResource().addRemoteSpc(1, affectedDpc, 0, 0);
        RemoteSignalingPointCodeImpl rspc1 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(1);
        sccpStack.getSccpResource().addRemoteSsn(1, affectedDpc, 8, 0, false);

        byte[] data = new byte[5];
        SccpAddress destinationAddress = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, affectedDpc,
                8);
        SccpAddress originatingAddress = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN, null, 1, 8);
        SccpDataMessage message = messageFactory.createDataMessageClass1(destinationAddress, originatingAddress, data, 0, 8,
                false, null, null);
        assertEquals(mtp3UserPart.lstMsg.size(), 0);
        sccpStack.getSccpProvider().send(message);
        assertEquals(mtp3UserPart.lstMsg.size(), 1);

        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);

        for (int i0 = 0; i0 < 5; i0++) {
            for (int i1 = 0; i1 < M; i1++) {
                sccpStack.onMtp3StatusMessage(msg);
                Thread.sleep(TIMER_A + EXTRA_DELAY);
            }
            assertEquals(rspc1.getCurrentRestrictionLevel(), i0 + 1);
            assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
            sccpStack.getSccpProvider().send(message);
            assertEquals(mtp3UserPart.lstMsg.size(), i0 + 2);
        }
        assertEquals(rspc1.getCurrentRestrictionLevel(), 5);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        assertEquals(mtp3UserPart.lstMsg.size(), 6);

        for (int i1 = 0; i1 < M; i1++) {
            sccpStack.onMtp3StatusMessage(msg);
            Thread.sleep(TIMER_A + EXTRA_DELAY);
        }
        assertEquals(rspc1.getCurrentRestrictionLevel(), 6);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        sccpStack.getSccpProvider().send(message);
        assertEquals(mtp3UserPart.lstMsg.size(), 6);
    }

    @Test
    public void testSSCRecieve() throws Exception {
        int affectedDpc = 101;
        Mtp3StatusPrimitive msg = new Mtp3StatusPrimitive(affectedDpc, Mtp3StatusCause.SignallingNetworkCongested, 1, 3);

        Mtp3UserPartProxy mtp3UserPart = new Mtp3UserPartProxy();
        sccpStack.setMtp3UserPart(1, mtp3UserPart);
        router.addMtp3ServiceAccessPoint(1, 1, 201, 0, 0, null);
        router.addMtp3Destination(1, 1, affectedDpc, affectedDpc, 0, 0, 255);

        sccpStack.getSccpResource().addRemoteSpc(1, affectedDpc, 0, 0);
        RemoteSignalingPointCodeImpl rspc1 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(1);
        sccpStack.getSccpResource().addRemoteSsn(1, affectedDpc, 8, 0, false);

        Mtp3TransferPrimitiveFactory mtp3TransferPrimitiveFactory = new Mtp3TransferPrimitiveFactory(RoutingLabelFormat.ITU);
        byte[] data = new byte[] { 9, 1, 3, 8, 13, 5, 0x43, 101, 0, 1, 0, 5, 0x43, (byte) 201, 0, 1, 0, 6, 6, 1, 101, 0, 0, 1 };
        Mtp3TransferPrimitive mtp3Msg = mtp3TransferPrimitiveFactory.createMtp3TransferPrimitive(3, 0, 0, affectedDpc, 201, 0,
                data);

        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 0);
        sccpStack.onMtp3TransferMessage(mtp3Msg);
        assertEquals(rspc1.getCurrentRestrictionLevel(), 0);
        assertEquals(rspc1.getCurrentRestrictionSubLevel(), 1);

    }

    private class SccpListenerProxy extends BaseSccpListener implements SccpListener {

        private ArrayList<NI_NIS> lst = new ArrayList<NI_NIS>();

        @Override
        public void onMessage(SccpDataMessage message) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onNotice(SccpNoticeMessage message) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onCoordResponse(int ssn, int multiplicityIndicator) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onState(int dpc, int ssn, boolean inService, int multiplicityIndicator) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPcState(int dpc, SignallingPointStatus status, Integer restrictedImportanceLevel,
                RemoteSccpStatus remoteSccpStatus) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onNetworkIdState(int networkId, NetworkIdState networkIdState) {
            lst.add(new NI_NIS(networkId, networkIdState));
        }

        public ArrayList<NI_NIS> getNI_NISList() {
            return this.lst;
        }
    }

    private class NI_NIS {
        public NI_NIS(int networkId, NetworkIdState networkIdState) {
            this.networkId = networkId;
            this.networkIdState = networkIdState;
        }

        public int networkId;
        public NetworkIdState networkIdState;
    }

    private class Mtp3UserPartProxy implements Mtp3UserPart {

        private Mtp3TransferPrimitiveFactory factory = new Mtp3TransferPrimitiveFactory(RoutingLabelFormat.ITU);
        public ArrayList<Mtp3TransferPrimitive> lstMsg = new ArrayList<Mtp3TransferPrimitive>();

        @Override
        public void addMtp3UserPartListener(Mtp3UserPartListener listener) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void removeMtp3UserPartListener(Mtp3UserPartListener listener) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public RoutingLabelFormat getRoutingLabelFormat() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setRoutingLabelFormat(RoutingLabelFormat routingLabelFormat) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public Mtp3TransferPrimitiveFactory getMtp3TransferPrimitiveFactory() {
            return factory;
        }

        @Override
        public int getMaxUserDataLength(int dpc) {
            // TODO Auto-generated method stub
            return 300;
        }

        @Override
        public void sendMessage(Mtp3TransferPrimitive msg) throws IOException {
            lstMsg.add(msg);
        }

        @Override
        public void setUseLsbForLinksetSelection(boolean useLsbForLinksetSelection) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public boolean isUseLsbForLinksetSelection() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public int getDeliveryMessageThreadCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void setDeliveryMessageThreadCount(int deliveryMessageThreadCount) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public ExecutorCongestionMonitor getExecutorCongestionMonitor() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void start() throws Exception {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void stop() throws Exception {
            // TODO Auto-generated method stub
            
        }
        
    }

}
