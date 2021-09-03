
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InterCUGRestrictions;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InterCUGRestrictionsValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class InterCUGRestrictionsImpl extends OctetStringLength1Base implements InterCUGRestrictions {

    public InterCUGRestrictionsImpl() {
        super("InterCUGRestrictions");
    }

    public InterCUGRestrictionsImpl(int data) {
        super("InterCUGRestrictions", data);
    }

    public InterCUGRestrictionsImpl(InterCUGRestrictionsValue val) {
        super("InterCUGRestrictions", (val != null ? val.getCode() : 0));
    }

    @Override
    public int getData() {
        return this.data;
    }

    @Override
    public InterCUGRestrictionsValue getInterCUGRestrictionsValue() {
        return InterCUGRestrictionsValue.getInstance(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("InterCUGRestrictions=" + this.getInterCUGRestrictionsValue());

        sb.append("]");

        return sb.toString();
    }

}
