
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.GGSNInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class GGSNInterfaceListImpl extends BitStringBase implements GGSNInterfaceList {

    static final int _ID_gn = 0;
    static final int _ID_gi = 1;
    static final int _ID_gmb = 2;

    public static final String _PrimitiveName = "GGSNInterfaceList";

    public GGSNInterfaceListImpl() {
        super(3, 8, 3, _PrimitiveName);
    }

    public GGSNInterfaceListImpl(boolean gn, boolean gi, boolean gmb) {
        super(3, 8, 3, _PrimitiveName);

        if (gn)
            this.bitString.set(_ID_gn);
        if (gi)
            this.bitString.set(_ID_gi);
        if (gmb)
            this.bitString.set(_ID_gmb);
    }

    @Override
    public boolean getGn() {
        return this.bitString.get(_ID_gn);
    }

    @Override
    public boolean getGi() {
        return this.bitString.get(_ID_gi);
    }

    @Override
    public boolean getGmb() {
        return this.bitString.get(_ID_gmb);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getGn()) {
            sb.append("gn, ");
        }
        if (this.getGi()) {
            sb.append("gi, ");
        }
        if (this.getGmb()) {
            sb.append("gmb, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
