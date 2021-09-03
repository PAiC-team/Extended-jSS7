
package org.restcomm.protocols.ss7.tools.traceparser;

import java.io.IOException;

import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitiveFactory;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPartListener;
import org.restcomm.protocols.ss7.mtp.RoutingLabelFormat;
import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;

/**
*
* @author sergey vetyutnev
*
*/
public class Mtp3UserPartProxy implements Mtp3UserPart {

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getMaxUserDataLength(int dpc) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void sendMessage(Mtp3TransferPrimitive msg) throws IOException {
        // TODO Auto-generated method stub

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
