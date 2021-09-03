
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.BMSCInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class BMSCInterfaceListImpl extends BitStringBase implements BMSCInterfaceList {
    static final int _ID_gmb = 0;

    public static final String _PrimitiveName = "BMSCInterfaceList";

    public BMSCInterfaceListImpl() {
        super(1, 8, 1, _PrimitiveName);
    }

    public BMSCInterfaceListImpl(boolean gmb) {
        super(1, 8, 1, _PrimitiveName);

        if (gmb)
            this.bitString.set(_ID_gmb);
    }

    @Override
    public boolean getGmb() {
        return this.bitString.get(_ID_gmb);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getGmb()) {
            sb.append("gmb, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
