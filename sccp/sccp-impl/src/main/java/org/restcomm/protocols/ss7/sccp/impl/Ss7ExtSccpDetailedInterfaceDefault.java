
package org.restcomm.protocols.ss7.sccp.impl;

import javolution.util.FastMap;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.RemoteSccpStatus;
import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpAddressedMessageImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
*
* @author sergey vetyutnev
*
*/
public class Ss7ExtSccpDetailedInterfaceDefault implements Ss7ExtSccpDetailedInterface {

    private Logger logger;

    @Override
    public void init(SccpStackImpl sccpStackImpl) {
        this.logger = Logger.getLogger(Ss7ExtSccpDetailedInterfaceDefault.class.getCanonicalName() + "-" + sccpStackImpl.getName());
    }

    @Override
    public void startExtBefore(String persistDir, String name) {
    }

    @Override
    public void startExtAfter(Router router, SccpManagement sccpManagement) {
    }

    @Override
    public void stopExt() {
    }

    @Override
    public void removeAllResources() {
    }

    @Override
    public int findDpsForAddresses(SccpAddress calledPartyAddress, SccpAddress callingPartyAddress, int msgNetworkId) {
        return -1;
    }

    @Override
    public void translationFunction(SccpRoutingCtxInterface ctx, SccpAddressedMessageImpl msg) throws Exception {
        logger.warn("Unexpected call to translationFunction method");
    }

    @Override
    public void onAllowRsp(int affectedPc, RemoteSccpStatus remoteSccpStatus) {
    }

    @Override
    public void onProhibitRsp(int affectedPc, RemoteSccpStatus remoteSccpStatus, RemoteSignalingPointCodeImpl remoteSpc) {
    }

    @Override
    public void onRestrictionLevelChange(int affectedPc, int restrictionLevel, boolean levelEncreased) {
    }

    @Override
    public FastMap<Integer, NetworkIdState> getNetworkIdList(int affectedPc) {
        return new FastMap<Integer, NetworkIdState>();
    }

    @Override
    public RemoteSignalingPointCodeExt createRemoteSignalingPointCodeExt(
            RemoteSignalingPointCodeImpl remoteSignalingPointCodeImpl) {
        return new RemoteSignalingPointCodeExtDefault();
    }

}
