
package org.restcomm.protocols.ss7.map.service.callhandling;

import org.restcomm.protocols.ss7.map.api.service.callhandling.CallDiversionTreatmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallDiversionTreatmentIndicatorValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CallDiversionTreatmentIndicatorImpl extends OctetStringLength1Base implements CallDiversionTreatmentIndicator {

    public CallDiversionTreatmentIndicatorImpl() {
        super("CallDiversionTreatmentIndicator");
    }

    public CallDiversionTreatmentIndicatorImpl(int data) {
        super("CallDiversionTreatmentIndicator", data);
    }

    public CallDiversionTreatmentIndicatorImpl(CallDiversionTreatmentIndicatorValue value) {
        super("CallDiversionTreatmentIndicator", value != null ? value.getCode() : 0);
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public CallDiversionTreatmentIndicatorValue getCallDiversionTreatmentIndicatorValue() {
        return CallDiversionTreatmentIndicatorValue.getInstance(this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        sb.append(this.getCallDiversionTreatmentIndicatorValue());

        sb.append(", Data=");
        sb.append(this.data);

        sb.append("]");

        return sb.toString();
    }

}
