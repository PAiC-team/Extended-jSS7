
package org.restcomm.protocols.ss7.mtp;

import java.io.IOException;

import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface Mtp3UserPart {

    void start() throws Exception;

    void stop() throws Exception;

    /**
     * Add {@link Mtp3UserPartListener}
     *
     * @param listener
     */
    void addMtp3UserPartListener(Mtp3UserPartListener listener);

    /**
     * Remove {@link Mtp3UserPartListener}
     *
     * @param listener
     */
    void removeMtp3UserPartListener(Mtp3UserPartListener listener);

    /**
     * Get the Mtp3TransferPrimitiveFactory
     *
     * @return
     */
    Mtp3TransferPrimitiveFactory getMtp3TransferPrimitiveFactory();

    /**
     * Return the maximum data field length of the MTP-TRANSFER message to the DPC
     *
     * @param dpc
     * @return
     */
    int getMaxUserDataLength(int dpc);

    /**
     * If message delivering failed: MTP-PAUSE or MTR-STATUS indication will be sent
     *
     * @param msg
     *
     */
    void sendMessage(Mtp3TransferPrimitive msg) throws IOException;


    /**
     * return PointCodeFormat
     *
     * @return
     */
    RoutingLabelFormat getRoutingLabelFormat();

    /**
     * Set PointCodeFormat
     *
     * @param length
     */
    void setRoutingLabelFormat(RoutingLabelFormat routingLabelFormat) throws Exception;

    /**
     * Returns true if lowest bit of SLS is used for loadbalancing between Linkset else returns false
     *
     * @return
     */
    boolean isUseLsbForLinksetSelection();

    /**
     * If set to true, lowest bit of SLS is used for loadbalancing between Linkset else highest bit of SLS is used.
     *
     * @param useLsbForLinksetSelection
     */
    void setUseLsbForLinksetSelection(boolean useLsbForLinksetSelection) throws Exception;

    /**
     * @return
     */
    int getDeliveryMessageThreadCount();

    /**
     * @param deliveryMessageThreadCount
     */
    void setDeliveryMessageThreadCount(int deliveryMessageThreadCount) throws Exception;

    /**
     * @return ExecutorCongestionMonitor that is responsible for measuring of congestion of the thread Executor that processes
     *         incoming messages (may be null if mtp3 is not started)
     */
    ExecutorCongestionMonitor getExecutorCongestionMonitor();

}
