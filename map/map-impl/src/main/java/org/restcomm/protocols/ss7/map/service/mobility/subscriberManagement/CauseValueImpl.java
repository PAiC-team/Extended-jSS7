
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CauseValue;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CauseValueCodeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CauseValueImpl extends OctetStringLength1Base implements CauseValue {

    public CauseValueImpl() {
        super("CauseValue");
    }

    public CauseValueImpl(int data) {
        super("CauseValue", data);
    }

    public CauseValueImpl(CauseValueCodeValue value) {
        super("CauseValue", value != null ? value.getCode() : 0);
    }

    @Override
    public CauseValueCodeValue getCauseValueCodeValue() {
        return CauseValueCodeValue.getInstance(this.data);
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        sb.append(this.getCauseValueCodeValue());

        sb.append(", Data=");
        sb.append(this.data);

        sb.append("]");

        return sb.toString();
    }

}
