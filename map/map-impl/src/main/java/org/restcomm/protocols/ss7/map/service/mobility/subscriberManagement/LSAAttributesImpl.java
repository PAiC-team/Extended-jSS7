
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAAttributes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentificationPriorityValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class LSAAttributesImpl extends OctetStringLength1Base implements LSAAttributes {

    private static int preferentialAccess_mask = 0x10;
    private static int activeModeSupport_mask = 0x20;
    private static int lsaIdentificationPriority_mask = 0x0F;

    public LSAAttributesImpl() {
        super("LSAAttributes");
    }

    public LSAAttributesImpl(int data) {
        super("LSAAttributes", data);
    }

    public LSAAttributesImpl(LSAIdentificationPriorityValue value, boolean preferentialAccessAvailable,
            boolean activeModeSupportAvailable) {
        super("LSAAttributes", value.getCode() | (preferentialAccessAvailable ? preferentialAccess_mask : 0)
                | (activeModeSupportAvailable ? activeModeSupport_mask : 0));
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public LSAIdentificationPriorityValue getLSAIdentificationPriority() {
        return LSAIdentificationPriorityValue.getInstance(data & lsaIdentificationPriority_mask);
    }

    @Override
    public boolean isPreferentialAccessAvailable() {
        return ((data & preferentialAccess_mask) == preferentialAccess_mask);
    }

    @Override
    public boolean isActiveModeSupportAvailable() {
        return ((data & activeModeSupport_mask) == activeModeSupport_mask);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("  LSAIdentificationPriorityValue=");
        sb.append(this.getLSAIdentificationPriority());

        if (this.isPreferentialAccessAvailable()) {
            sb.append(" , PreferentialAccessAvailable ");
        }

        if (this.isActiveModeSupportAvailable()) {
            sb.append(" , ActiveModeSupportAvailable ");
        }

        sb.append(", Data=");
        sb.append(this.data);

        sb.append("]");

        return sb.toString();
    }

}
