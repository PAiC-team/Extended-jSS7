
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.mobicents.protocols.asn.BitSetStrictLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalInfo;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class AdditionalInfoImpl extends BitStringBase implements AdditionalInfo {

    public static final String _PrimitiveName = "AdditionalInfo";

    public AdditionalInfoImpl() {
        super(1, 136, 1, _PrimitiveName);
    }

    public AdditionalInfoImpl(BitSetStrictLength data) {
        super(1, 136, data.getStrictLength(), _PrimitiveName, data);
    }

    @Override
    public BitSetStrictLength getData() {
        return bitString;
    }

}
