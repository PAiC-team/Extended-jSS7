
package org.restcomm.protocols.ss7.map.primitives;

import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PlmnIdImpl extends OctetStringBase implements PlmnId {

    public PlmnIdImpl() {
        super(3, 3, "PlmnId");
    }

    public PlmnIdImpl(byte[] data) {
        super(3, 3, "PlmnId", data);
    }

    public PlmnIdImpl(int mcc, int mnc) {
        super(3, 3, "PlmnId");

        int a1 = mcc / 100;
        int tv = mcc % 100;
        int a2 = tv / 10;
        int a3 = tv % 10;

        int b1 = mnc / 100;
        tv = mnc % 100;
        int b2 = tv / 10;
        int b3 = tv % 10;

        this.data = new byte[3];
        this.data[0] = (byte) ((a2 << 4) + a1);
        if (b1 == 0) {
            this.data[1] = (byte) (0xF0 + a3);
            this.data[2] = (byte) ((b3 << 4) + b2);
        } else {
            this.data[1] = (byte) ((b3 << 4) + a3);
            this.data[2] = (byte) ((b2 << 4) + b1);
        }
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public int getMcc() {
        if (this.data == null || this.data.length != 3)
            return 0;

        int a1 = this.data[0] & 0x0F;
        int a2 = (this.data[0] & 0xF0) >> 4;
        int a3 = this.data[1] & 0x0F;

        return a1 * 100 + a2 * 10 + a3;
    }

    @Override
    public int getMnc() {
        if (this.data == null || this.data.length != 3)
            return 0;

        int a1 = this.data[2] & 0x0F;
        int a2 = (this.data[2] & 0xF0) >> 4;
        int a3 = (this.data[1] & 0xF0) >> 4;

        if (a3 == 15)
            return a1 * 10 + a2;
        else
            return a1 * 100 + a2 * 10 + a3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("MCC=[");
        sb.append(this.getMcc());
        sb.append("]");

        sb.append(", MNC=[");
        sb.append(this.getMnc());
        sb.append("]");

        sb.append("]");

        return sb.toString();
    }
}
