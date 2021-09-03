package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.smstpdu.Status;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class StatusImpl implements Status {

    private int code;

    public StatusImpl(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("TP-Status [");
        sb.append(this.code);
        sb.append("]");

        return sb.toString();
    }
}
