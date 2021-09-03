
package org.restcomm.protocols.ss7.cap.api;

import java.io.Serializable;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessageFactory;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPServiceCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPServiceGprs;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPServiceSms;
import org.restcomm.protocols.ss7.inap.api.INAPParameterFactory;
import org.restcomm.protocols.ss7.isup.ISUPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPProvider extends Serializable {

    /**
     * Add CAP Dialog listener to the Stack
     *
     * @param capDialogListener
     */
    void addCAPDialogListener(CAPDialogListener capDialogListener);

    /**
     * Remove CAP Dialog Listener from the stack
     *
     * @param capDialogListener
     */
    void removeCAPDialogListener(CAPDialogListener capDialogListener);

    /**
     * Get the {@link CAPParameterFactory}
     *
     * @return
     */
    CAPParameterFactory getCAPParameterFactory();

    /**
     * Get the {@link MAPParameterFactory}
     *
     * @return
     */
    MAPParameterFactory getMAPParameterFactory();

    /**
     * Get the {@link ISUPParameterFactory}
     *
     * @return
     */
    ISUPParameterFactory getISUPParameterFactory();

    /**
     * Get the {@link INAPParameterFactory}
     *
     * @return
     */
    INAPParameterFactory getINAPParameterFactory();

    /**
     * Get the {@link CAPErrorMessageFactory}
     *
     * @return
     */
    CAPErrorMessageFactory getCAPErrorMessageFactory();

    /**
     * Get {@link CAPDialog} corresponding to passed dialogId
     *
     * @param dialogId
     * @return CAPDialog
     */
    CAPDialog getCAPDialog(Long dialogId);

    CAPServiceCircuitSwitchedCall getCAPServiceCircuitSwitchedCall();

    CAPServiceGprs getCAPServiceGprs();

    CAPServiceSms getCAPServiceSms();

    /**
     * The collection of networkIds that are marked as prohibited or congested.
     *
     * @return The collection of pairs: networkId value - NetworkIdState (prohibited / congested state)
     */
    FastMap<Integer, NetworkIdState> getNetworkIdStateList();

    /**
     * Returns the state of availability / congestion for a networkId subnetwork. Returns null if there is no info (we need to
     * treat it as available)
     *
     * @param networkId
     * @return NetworkIdState
     */
    NetworkIdState getNetworkIdState(int networkId);

    /**
     * Setting of a congestion level for a TCAP user "congObject"
     *
     * @param congObject a String with the name of an object
     * @param level a congestion level for this object
     */
    void setUserPartCongestionLevel(String congObject, int level);

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
