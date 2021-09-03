
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtQoSSubscribed_BitRate;

/**
*
* @author sergey vetyutnev
*
*/
public class ExtQoSSubscribed_BitRateImpl implements ExtQoSSubscribed_BitRate {

    private int data;

    public ExtQoSSubscribed_BitRateImpl(int data, boolean isSourceData) {
        if (isSourceData)
            this.data = data;
        else
            this.setData(data);
    }

    protected void setData(int val) {
        if (val <= 0) {
            this.data = 0;
        } else if (val > 0 && val < 64) {
            this.data = val;
        } else if (val >= 64 && val <= 576) {
            this.data = (val - 64) / 8 + 64;
        } else {
            this.data = (val - 576) / 64 + 128;
            if (this.data > 254)
                this.data = 0;
        }
    }

    @Override
    public int getSourceData() {
        return data;
    }

    @Override
    public int getBitRate() {
        if (this.data > 0 && this.data < 64) {
            return this.data;
        } else if (this.data >= 64 && this.data < 128) {
            return 64 + (this.data - 64) * 8;
        } else if (this.data >= 128 && this.data < 255) {
            return 576 + (this.data - 128) * 64;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BitRate(kbit/s)=");
        int v = this.getBitRate();
        if (data == 255)
            sb.append("reserved");
        else if (v == 0)
            sb.append("Subscribed maximum bit rate for uplink / reserved");
        else {
            sb.append(v);
        }
        return sb.toString();
    }

}
