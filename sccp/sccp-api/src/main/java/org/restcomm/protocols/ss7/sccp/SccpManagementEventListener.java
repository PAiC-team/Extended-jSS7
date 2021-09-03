
package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface SccpManagementEventListener {

    void onServiceStarted();

    void onServiceStopped();

    void onRemoveAllResources();

    void onRemoteSubSystemUp(RemoteSubSystem remoteSubSystem);

    void onRemoteSubSystemDown(RemoteSubSystem remoteSubSystem);

    void onRemoteSpcUp(RemoteSignalingPointCode remoteSpc);

    void onRemoteSpcDown(RemoteSignalingPointCode remoteSpc);

    void onRemoteSccpUp(RemoteSignalingPointCode remoteSpc);

    void onRemoteSccpDown(RemoteSignalingPointCode remoteSpc);

}
