
package org.restcomm.protocols.ss7.tcapAnsi.api;

import java.io.Serializable;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.DraftParsedMessage;

/**
 *
 * @author baranowb
 *
 */
public interface TCAPProvider extends Serializable {

    /**
     * Create new structured dialog.
     *
     * @param localAddress - desired local address
     * @param remoteAddress - initial remote address, it can change after first TCContinue.
     * @return
     */
    Dialog getNewDialog(SccpAddress localAddress, SccpAddress remoteAddress) throws TCAPException;

    /**
     * Create new structured dialog with predefined local TransactionId.
     * We do not normally invoke this method. Use it only when you need this and only this local TransactionId
     * (for example if we need of recreating a Dialog for which a peer already has in memory)
     * If a Dialog with local TransactionId is already present there will be TCAPException
     *
     * @param localAddress - desired local address
     * @param remoteAddress - initial remote address, it can change after first TCContinue.
     * @param localTrId - predefined local TransactionId
     * @return
     */
    Dialog getNewDialog(SccpAddress localAddress, SccpAddress remoteAddress, Long localTrId) throws TCAPException;

    /**
     * Create new unstructured dialog.
     *
     * @param localAddress
     * @param remoteAddress
     * @return
     * @throws TCAPException
     */
    Dialog getNewUnstructuredDialog(SccpAddress localAddress, SccpAddress remoteAddress) throws TCAPException;

    // /////////////
    // Factories //
    // /////////////

    DialogPrimitiveFactory getDialogPrimitiveFactory();

    ComponentPrimitiveFactory getComponentPrimitiveFactory();

    // /////////////
    // Listeners //
    // /////////////

    void addTCListener(TCListener lst);

    void removeTCListener(TCListener lst);

    boolean getPreviewMode();

    /**
     * The collection of netwokIds that are marked as prohibited or congested.
     *
     * @return The collection of pairs: netwokId value - NetworkIdState (prohibited / congested state)
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

    /**
     * Parsing of encoded TCAP-ANSI message for getting only message type, origination/destination dialogId
     *
     * @param data
     * @return
     */
    DraftParsedMessage parseMessageDraft(byte[] data);

}
