
package org.restcomm.protocols.ss7.map.service.mobility.imei;

import org.mobicents.protocols.asn.BitSetStrictLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.UESBIIuA;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

public class UESBIIuAImpl extends BitStringBase implements UESBIIuA {

    public static final String _PrimitiveName = "UESBIIuA";

    public UESBIIuAImpl() {
        super(1, 128, 1, _PrimitiveName);
    }

    public UESBIIuAImpl(BitSetStrictLength data) {
        super(1, 128, data.getStrictLength(), _PrimitiveName, data);
    }

    @Override
    public BitSetStrictLength getData() {
        return bitString;
    }

}
