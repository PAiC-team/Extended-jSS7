
package org.restcomm.protocols.ss7.tcap.api;

import java.io.Serializable;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.DraftParsedMessage;

/**
 *
 * @author baranowb
 *
 */
public interface TCAPProvider extends Serializable {

    /**
     * Create new structured dialog.
     *
     * @param sccpCallingPartyAddress - desired local address
     * @param sccpCalledPartyAddress - initial remote address, it can change after first TCContinue.
     * @return
     */
    Dialog getNewDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) throws TCAPException;

    /**
     * Create new structured dialog with predefined local TransactionId.
     * We do not normally invoke this method. Use it only when you need this and only this local TransactionId
     * (for example if we need of recreating a Dialog for which a peer already has in memory)
     * If a Dialog with local TransactionId is already present there will be TCAPException
     *
     * @param sccpCallingPartyAddress - desired local address
     * @param sccpCalledPartyAddress - initial remote address, it can change after first TCContinue.
     * @param localTransactionId - predefined local TransactionId
     * @return
     */
    Dialog getNewDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, Long localTransactionId) throws TCAPException;

    /**
     * Create new unstructured dialog.
     *
     * @param sccpCallingPartyAddress
     * @param sccpCalledPartyAddress
     * @return
     * @throws TCAPException
     */
    Dialog getNewUnstructuredDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress) throws TCAPException;

    // /////////////
    // Factories //
    // /////////////

    DialogPrimitiveFactory getDialogPrimitiveFactory();

    ComponentPrimitiveFactory getComponentPrimitiveFactory();

    // /////////////
    // Listeners //
    // /////////////

    void addTCListener(TCListener tcListener);

    void removeTCListener(TCListener tcListener);

    boolean getPreviewMode();

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

    /**
     * Parsing of encoded TCAP message for getting only message type, origination/destination dialogId
     *
     * @param data
     * @return
     */
    DraftParsedMessage parseMessageDraft(byte[] data);

    /**
     * @return TCAP Stack
     */

    TCAPStack getStack();

}
