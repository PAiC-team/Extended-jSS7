
package org.restcomm.protocols.ss7.sccpext.impl.congestion;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.m3ua.impl.M3UAManagementImpl;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpRspProxy;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.BCDEvenEncodingScheme;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0100Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.impl.router.RouterImpl;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.sccpext.impl.router.RouterExtImpl;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Sergey Vetyutnev
 *
 */
public class NetworkIdAffectedPCTest {

    private GlobalTitle gt = new GlobalTitle0100Impl("*", 0, new BCDEvenEncodingScheme(), NumberingPlan.ISDN_TELEPHONY,
            NatureOfAddress.INTERNATIONAL);
    private SccpAddress pattern = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, 8);

    @Test
    public void testNetworkIdAffectedPC() throws Exception {

        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        SccpExtModuleImpl sccpExtModule1 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule1);
        SccpStackImpl sccpStack = new SccpStackImpl("TestSccp", ss7ExtInterface);
        sccpStack.start();
        sccpStack.removeAllResources();
        RouterImpl router = (RouterImpl) sccpStack.getRouter();
        RouterExtImpl routerExt = (RouterExtImpl) sccpExtModule1.getRouterExt();

        // no rules
        FastMap<Integer, NetworkIdState> map = routerExt.getNetworkIdList(101);
        assertEquals(map.size(), 0);

        // simple case
        GlobalTitle gt1 = new GlobalTitle0100Impl("-", 0, new BCDEvenEncodingScheme(), NumberingPlan.ISDN_TELEPHONY,
                NatureOfAddress.INTERNATIONAL);
        SccpAddress sccpAddress1 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 101, 8);
        SccpAddress sccpAddress2 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 102, 8);
        SccpAddress sccpAddress3 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 103, 8);
        SccpAddress sccpAddress4 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 104, 8);
        routerExt.addRoutingAddress(1, sccpAddress1);
        routerExt.addRoutingAddress(2, sccpAddress2);
        routerExt.addRoutingAddress(3, sccpAddress3);
        routerExt.addRoutingAddress(4, sccpAddress4);

        routerExt.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.ALL, pattern, "K", 1, -1, null, 1, pattern);
        routerExt.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 2, -1, null, 2, pattern);
        routerExt.addRule(3, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.REMOTE, pattern, "K", 3, -1, null, 3, pattern);
        routerExt.addRule(4, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.ALL, pattern, "K", 4, -1, null, 11, pattern);

        map = routerExt.getNetworkIdList(101);
        assertEquals(map.size(), 1);
        NetworkIdState state = map.get(1);
        assertNotNull(state);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        map = routerExt.getNetworkIdList(102);
        assertEquals(map.size(), 1);
        state = map.get(2);
        assertNotNull(state);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        // no routing address
        routerExt.removeRoutingAddress(1);
        routerExt.addRoutingAddress(1, sccpAddress1);

        // isPcLocal()==true
        Mtp3UserPart mtp3UserPart = new M3UAManagementImpl("Test", "Test2", null);
        sccpStack.setMtp3UserPart(1, mtp3UserPart);
        router.addMtp3ServiceAccessPoint(1, 1, 101, 0, 1, null);
        map = routerExt.getNetworkIdList(101);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertNotNull(state);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);
        router.removeMtp3ServiceAccessPoint(1);

        // RSP - available
        sccpStack.getSccpResource().addRemoteSpc(1, 101, 0, 0);
        sccpStack.getSccpResource().addRemoteSpc(2, 102, 0, 0);
        RemoteSignalingPointCodeImpl rspc1 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(1);
        RemoteSignalingPointCodeImpl rspc2 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(2);

        routerExt.removeRule(2);
        routerExt.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 2, -1, null, 1, pattern);
        map = routerExt.getNetworkIdList(101);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertNotNull(state);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        sccpAddress2 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 101, 8);
        routerExt.removeRule(2);
        routerExt.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, -1, null, 1, pattern);
        map = routerExt.getNetworkIdList(101);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertNotNull(state);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        // RSP - unavailable / congested
        sccpStack.removeAllResources();
        sccpAddress1 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 101, 8);
        sccpAddress2 = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, 102, 8);
        routerExt.addRoutingAddress(1, sccpAddress1);
        routerExt.addRoutingAddress(2, sccpAddress2);
        routerExt.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, -1, null, 1, pattern);
        routerExt.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 2, -1, null, 1, pattern);
        sccpStack.getSccpResource().addRemoteSpc(1, 101, 0, 0);
        sccpStack.getSccpResource().addRemoteSpc(2, 102, 0, 0);
        rspc1 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(1);
        rspc2 = (RemoteSignalingPointCodeImpl) sccpStack.getSccpResource().getRemoteSpc(2);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 1);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 1);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, true);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertFalse(state.isAvailable());

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, true);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertFalse(state.isAvailable());

        // Dominant
        routerExt.removeRule(1);
        routerExt.removeRule(2);
        routerExt.addRule(1, RuleType.DOMINANT, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, 2, null, 1, pattern);
        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 2);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, true);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 1);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, true);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, true);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertFalse(state.isAvailable());

        // Loadsharing
        routerExt.removeRule(1);
        routerExt.addRule(1, RuleType.LOADSHARED, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, 2, null, 1, pattern);
        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 0);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 0);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 2);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 2);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, true);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 1);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, true);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 2);

        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, true);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, true);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 1);
        state = map.get(1);
        assertFalse(state.isAvailable());

        // two affected networkIDs 
        routerExt.removeRule(1);
        routerExt.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, -1, null, 1, pattern);
        routerExt.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 2, -1, null, 2, pattern);
        SccpRspProxy.setCurrentRestrictionLevel(rspc1, 4);
        SccpRspProxy.setRemoteSpcProhibited(rspc1, false);
        SccpRspProxy.setCurrentRestrictionLevel(rspc2, 2);
        SccpRspProxy.setRemoteSpcProhibited(rspc2, false);
        map = routerExt.getNetworkIdList(-1);
        assertEquals(map.size(), 2);
        state = map.get(1);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 2);
        state = map.get(2);
        assertTrue(state.isAvailable());
        assertEquals(state.getCongLevel(), 1);
    
    }

}
