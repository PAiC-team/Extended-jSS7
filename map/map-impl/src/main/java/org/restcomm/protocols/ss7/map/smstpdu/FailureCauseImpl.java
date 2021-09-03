package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.smstpdu.FailureCause;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class FailureCauseImpl implements FailureCause {

    private int code;

    public FailureCauseImpl(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("TP-Failure-Cause [");
        sb.append(this.code);
        sb.append("]");

        return sb.toString();
    }
}
