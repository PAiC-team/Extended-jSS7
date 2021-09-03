
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPTypeNumber;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPTypeNumberValue;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDPTypeNumberImpl extends OctetStringLength1Base implements PDPTypeNumber {

    public PDPTypeNumberImpl() {
        super("PDPTypeNumber");
    }

    public PDPTypeNumberImpl(int data) {
        super("PDPTypeNumber", data);
    }

    public PDPTypeNumberImpl(PDPTypeNumberValue value) {
        super("PDPTypeNumber", value != null ? value.getCode() : 0);
    }

    @Override
    public PDPTypeNumberValue getPDPTypeNumberValue() {
        return PDPTypeNumberValue.getInstance(data);
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.getPDPTypeNumberValue() != null) {
            sb.append("PDPTypeNumberValue=");
            sb.append(this.getPDPTypeNumberValue());
        }

        sb.append("]");

        return sb.toString();
    }

}
