
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.SGSNCapabilities;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SGSNCapabilitiesImpl extends OctetStringLength1Base implements SGSNCapabilities {

    public SGSNCapabilitiesImpl() {
        super("SGSNCapabilities");
    }

    public SGSNCapabilitiesImpl(int data) {
        super("SGSNCapabilities", data);
    }

    public SGSNCapabilitiesImpl(boolean aoCSupportedBySGSN) {
        super("SGSNCapabilities", (aoCSupportedBySGSN ? 0x01 : 0x00));
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public boolean getAoCSupportedBySGSN() {
        return ((data & 0x01) == 0x01);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.getAoCSupportedBySGSN()) {
            sb.append("AoCSupportedBySGSN ");
        }

        sb.append("]");

        return sb.toString();
    }

}
