package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.smstpdu.ProtocolIdentifier;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ProtocolIdentifierImpl implements ProtocolIdentifier {

    private int code;

    public ProtocolIdentifierImpl(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("TP-Protocol-Identifier [");

        sb.append("Code=");
        sb.append(this.code);
        sb.append("]");

        return sb.toString();
    }
}
