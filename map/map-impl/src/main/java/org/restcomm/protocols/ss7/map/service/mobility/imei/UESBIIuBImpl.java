
package org.restcomm.protocols.ss7.map.service.mobility.imei;

import org.mobicents.protocols.asn.BitSetStrictLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.UESBIIuB;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 *
 * @author normandes
 *
 */
public class UESBIIuBImpl extends BitStringBase implements UESBIIuB {

    public static final String _PrimitiveName = "UESBIIuB";

    public UESBIIuBImpl() {
        super(1, 128, 1, _PrimitiveName);
    }

    public UESBIIuBImpl(BitSetStrictLength data) {
        super(1, 128, data.getStrictLength(), _PrimitiveName, data);
    }

    @Override
    public BitSetStrictLength getData() {
        return bitString;
    }

}
