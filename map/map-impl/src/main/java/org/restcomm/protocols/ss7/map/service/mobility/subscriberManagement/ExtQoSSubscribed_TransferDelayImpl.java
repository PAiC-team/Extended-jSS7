
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtQoSSubscribed_TransferDelay;

/**
*
* @author sergey vetyutnev
*
*/
public class ExtQoSSubscribed_TransferDelayImpl implements ExtQoSSubscribed_TransferDelay {

    private int data;

    public ExtQoSSubscribed_TransferDelayImpl(int data, boolean isSourceData) {
        if (isSourceData)
            this.data = data;
        else
            this.setData(data);
    }

    protected void setData(int val) {
        if (val <= 0) {
            this.data = 0;
        } else if (val > 0 && val < 200) {
            this.data = val / 10;
        } else if (val >= 200 && val < 1000) {
            this.data = (val - 200) / 50 + 16;
        } else {
            this.data = (val - 1000) / 100 + 32;
            if (this.data > 62)
                this.data = 0;
        }
    }

    @Override
    public int getSourceData() {
        return data;
    }

    @Override
    public int getTransferDelay() {
        if (this.data > 0 && this.data < 16) {
            return this.data * 10;
        } else if (this.data >= 16 && this.data < 32) {
            return 200 + (this.data - 16) * 50;
        } else if (this.data >= 32 && this.data < 63) {
            return 1000 + (this.data - 32) * 100;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransferDelay=");
        int v = this.getTransferDelay();
        if (this.data == 63)
            sb.append("reserved");
        else if (v == 0)
            sb.append("Subscribed transfer delay / reserved");
        else {
            sb.append(v);
        }
        return sb.toString();
    }

}
