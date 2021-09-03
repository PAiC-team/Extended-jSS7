
package org.restcomm.protocols.ss7.map.api;

import java.io.Serializable;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageFactory;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandling;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobility;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPServiceOam;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSms;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementary;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;

/**
 *
 * @author amit bhayani
 *
 */
public interface MAPProvider extends Serializable {

    // int NETWORK_UNSTRUCTURED_SS_CONTEXT_V2 = 1;

    /**
     * Add MAP Dialog listener to the Stack
     *
     * @param mapDialogListener
     */
    void addMAPDialogListener(MAPDialogListener mapDialogListener);

    /**
     * Remove MAP DIalog Listener from the stack
     *
     * @param mapDialogListener
     */
    void removeMAPDialogListener(MAPDialogListener mapDialogListener);

    /**
     * Get the {@link MAPParameterFactory}
     *
     * @return
     */
    MAPParameterFactory getMAPParameterFactory();

    /**
     * Get the {@link MAPErrorMessageFactory}
     *
     * @return
     */
    MAPErrorMessageFactory getMAPErrorMessageFactory();

    /**
     * Get {@link MAPDialog} corresponding to passed dialogId
     *
     * @param dialogId
     * @return
     */
    MAPDialog getMAPDialog(Long dialogId);

    /**
     *
     * @return
     */
    MAPSmsTpduParameterFactory getMAPSmsTpduParameterFactory();

    MAPServiceMobility getMAPServiceMobility();

    MAPServiceCallHandling getMAPServiceCallHandling();

    MAPServiceOam getMAPServiceOam();

    MAPServicePdpContextActivation getMAPServicePdpContextActivation();

    MAPServiceSupplementary getMAPServiceSupplementary();

    MAPServiceSms getMAPServiceSms();

    MAPServiceLsm getMAPServiceLsm();

    /**
     * The collection of netwokIds that are marked as prohibited or congested.
     *
     * @return The collection of pairs: networkId value - NetworkIdState (prohibited / congested state)
     */
    FastMap<Integer, NetworkIdState> getNetworkIdStateList();

    /**
     * Returns the state of availability / congestion for a networkId subnetwork. Returns null if there is no info (we need to
     * treat it as available)
     *
     * @param networkId
     * @return
     */
    NetworkIdState getNetworkIdState(int networkId);

    /**
     * Setting of a congestion level for a TCAP user "congObject"
     *
     * @param congestionObject a String with the name of an object
     * @param level a congestion level for this object
     */
    void setUserPartCongestionLevel(String congestionObject, int level);

    /**
     * Returns a congestion level of a Memory congestion monitor
     *
     * @return
     */
    int getMemoryCongestionLevel();

    /**
     * Returns a congestion level of thread Executors for processing of incoming messages
     *
     * @return
     */
    int getExecutorCongestionLevel();

    /**
     * Returns a max congestion level for UserPartCongestion, MemoryCongestion and ExecutorCongestionLevel
     *
     * @return
     */
    int getCumulativeCongestionLevel();

    /**
     * @return current count of active TCAP dialogs
     */
    int getCurrentDialogsCount();

}
