package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.smstpdu.CommandType;
import org.restcomm.protocols.ss7.map.api.smstpdu.CommandTypeValue;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CommandTypeImpl implements CommandType {

    private int code;

    public CommandTypeImpl(int code) {
        this.code = code;
    }

    public CommandTypeImpl(CommandTypeValue value) {
        this.code = value.getCode();
    }

    public int getCode() {
        return this.code;
    }

    public CommandTypeValue getCommandTypeValue() {
        return CommandTypeValue.getInstance(this.code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("TP-Command-Type [");
        sb.append(this.code);
        sb.append("]");

        return sb.toString();
    }
}
