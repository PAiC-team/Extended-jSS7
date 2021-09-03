
package org.restcomm.protocols.ss7.map.service.mobility.imei;

import org.restcomm.protocols.ss7.map.api.service.mobility.imei.RequestedEquipmentInfo;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 * @author normandes
 *
 */
public class RequestedEquipmentInfoImpl extends BitStringBase implements RequestedEquipmentInfo {

    public static final String _PrimitiveName = "RequestedEquipmentInfo";

    private static final int _INDEX_EQUIPMENT_STATUS = 0;
    private static final int _INDEX_BMUEF = 1;

    public RequestedEquipmentInfoImpl() {
        super(2, 8, 2, _PrimitiveName);
    }

    public RequestedEquipmentInfoImpl(boolean equipmentStatus, boolean bmuef) {
        super(2, 8, 2, _PrimitiveName);

        if (equipmentStatus)
            this.bitString.set(_INDEX_EQUIPMENT_STATUS);

        if (bmuef)
            this.bitString.set(_INDEX_BMUEF);
    }

    @Override
    public boolean getEquipmentStatus() {
        return this.bitString.get(_INDEX_EQUIPMENT_STATUS);
    }

    @Override
    public boolean getBmuef() {
        return this.bitString.get(_INDEX_BMUEF);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (getEquipmentStatus()) {
            sb.append("EquipmentStatus, ");
        }
        if (getBmuef()) {
            sb.append("bmuef, ");
        }
        sb.append("]");
        return sb.toString();
    }
}
