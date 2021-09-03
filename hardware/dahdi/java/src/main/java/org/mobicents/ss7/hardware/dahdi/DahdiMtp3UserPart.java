package org.mobicents.ss7.hardware.dahdi;

import java.io.IOException;

import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitiveFactory;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPartListener;
import org.restcomm.protocols.ss7.mtp.RoutingLabelFormat;
import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;

/**
 *
 * @author amit bhayani
 *
 */
public class DahdiMtp3UserPart implements Mtp3UserPart {

    @Override
    public void addMtp3UserPartListener(Mtp3UserPartListener arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getMaxUserDataLength(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void removeMtp3UserPartListener(Mtp3UserPartListener arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessage(Mtp3TransferPrimitive arg0) throws IOException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.mtp.Mtp3UserPart#getMtp3TransferPrimitiveFactory()
     */
    @Override
    public Mtp3TransferPrimitiveFactory getMtp3TransferPrimitiveFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.mtp.Mtp3UserPart#getRoutingLabelFormat()
     */
    @Override
    public RoutingLabelFormat getRoutingLabelFormat() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.mobicents.protocols.ss7.mtp.Mtp3UserPart#setRoutingLabelFormat(org.mobicents.protocols.ss7.mtp.RoutingLabelFormat)
     */
    @Override
    public void setRoutingLabelFormat(RoutingLabelFormat arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isUseLsbForLinksetSelection() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setUseLsbForLinksetSelection(boolean arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getDeliveryMessageThreadCount() {
        return 0;
    }

    @Override
    public void setDeliveryMessageThreadCount(int deliveryMessageThreadCount) throws Exception {

    }

    @Override
    public ExecutorCongestionMonitor getExecutorCongestionMonitor() {
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
