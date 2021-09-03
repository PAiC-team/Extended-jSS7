
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalSubscriptions;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class AdditionalSubscriptionsImpl extends BitStringBase implements AdditionalSubscriptions {

    private static final int _INDEX_PrivilegedUplinkRequest = 0;
    private static final int _INDEX_EmergencyUplinkRequest = 1;
    private static final int _INDEX_EmergencyReset = 2;

    public AdditionalSubscriptionsImpl() {
        super(3, 8, 3, "AdditionalSubscriptions");
    }

    public AdditionalSubscriptionsImpl(boolean privilegedUplinkRequest, boolean emergencyUplinkRequest, boolean emergencyReset) {
        super(3, 8, 3, "AdditionalSubscriptions");

        if (privilegedUplinkRequest)
            this.bitString.set(_INDEX_PrivilegedUplinkRequest);
        if (emergencyUplinkRequest)
            this.bitString.set(_INDEX_EmergencyUplinkRequest);
        if (emergencyReset)
            this.bitString.set(_INDEX_EmergencyReset);
    }

    @Override
    public boolean getPrivilegedUplinkRequest() {
        return this.bitString.get(_INDEX_PrivilegedUplinkRequest);
    }

    @Override
    public boolean getEmergencyUplinkRequest() {
        return this.bitString.get(_INDEX_EmergencyUplinkRequest);
    }

    @Override
    public boolean getEmergencyReset() {
        return this.bitString.get(_INDEX_EmergencyReset);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.getPrivilegedUplinkRequest())
            sb.append("PrivilegedUplinkRequest, ");
        if (this.getEmergencyUplinkRequest())
            sb.append("EmergencyUplinkRequest, ");
        if (this.getEmergencyReset())
            sb.append("EmergencyReset ");
        sb.append("]");
        return sb.toString();
    }

}
