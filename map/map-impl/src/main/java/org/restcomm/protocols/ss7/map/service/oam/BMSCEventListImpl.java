
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.BMSCEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class BMSCEventListImpl extends BitStringBase implements BMSCEventList {

    static final int _ID_mbmsMulticastServiceActivation = 0;

    public static final String _PrimitiveName = "BMSCEventList";

    public BMSCEventListImpl() {
        super(1, 8, 1, _PrimitiveName);
    }

    public BMSCEventListImpl(boolean mbmsMulticastServiceActivation) {
        super(1, 8, 1, _PrimitiveName);

        if (mbmsMulticastServiceActivation)
            this.bitString.set(_ID_mbmsMulticastServiceActivation);
    }

    @Override
    public boolean getMbmsMulticastServiceActivation() {
        return this.bitString.get(_ID_mbmsMulticastServiceActivation);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getMbmsMulticastServiceActivation()) {
            sb.append("mbmsMulticastServiceActivation, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
