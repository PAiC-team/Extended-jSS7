
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author daniel bichara
 * @author sergey vetyutnev
 *
 */
public class ExtSSStatusImpl extends OctetStringBase implements ExtSSStatus {

    /**
     * SSStatus bits TS 3GPP TS 23.011
     */
    public static final byte sssBitQ = 8; // bit 4
    public static final byte sssBitP = 4; // bit 3
    public static final byte sssBitR = 2; // bit 2
    public static final byte sssBitA = 1; // bit 1

    public ExtSSStatusImpl() {
        super(1, 5, "ExtSSStatus");
    }

    public ExtSSStatusImpl(byte[] data) {
        super(1, 5, "ExtSSStatus", data);
    }

    public ExtSSStatusImpl(boolean bitQ, boolean bitP, boolean bitR, boolean bitA) {
        super(1, 5, "ExtSSStatus");

        this.data = new byte[1];

        if (bitQ)
            this.data[0] |= sssBitQ;
        if (bitP)
            this.data[0] |= sssBitP;
        if (bitR)
            this.data[0] |= sssBitR;
        if (bitA)
            this.data[0] |= sssBitA;
    }

    public byte[] getData() {
        return data;
    }

    public boolean getBitQ() {
        if (this.data == null || this.data.length < 1)
            return false;

        return ((this.data[0] & sssBitQ) > 0 ? true : false);
    }

    public boolean getBitP() {
        if (this.data == null || this.data.length < 1)
            return false;

        return ((this.data[0] & sssBitP) > 0 ? true : false);
    }

    public boolean getBitR() {
        if (this.data == null || this.data.length < 1)
            return false;

        return ((this.data[0] & sssBitR) > 0 ? true : false);
    }

    public boolean getBitA() {
        if (this.data == null || this.data.length < 1)
            return false;

        return ((this.data[0] & sssBitA) > 0 ? true : false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.getBitQ()) {
            sb.append("bitQ, ");
        }
        if (this.getBitP()) {
            sb.append("bitP, ");
        }
        if (this.getBitR()) {
            sb.append("bitR, ");
        }
        if (this.getBitA()) {
            sb.append("bitA, ");
        }

        sb.append("]");

        return sb.toString();
    }
}
