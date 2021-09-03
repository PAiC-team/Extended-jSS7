
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.RNCInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class RNCInterfaceListImpl extends BitStringBase implements RNCInterfaceList {

    static final int _ID_iu = 0;
    static final int _ID_iur = 1;
    static final int _ID_iub = 2;
    static final int _ID_uu = 3;

    public static final String _PrimitiveName = "RNCInterfaceList";

    public RNCInterfaceListImpl() {
        super(4, 8, 4, _PrimitiveName);
    }

    public RNCInterfaceListImpl(boolean iu, boolean iur, boolean iub, boolean uu) {
        super(4, 8, 4, _PrimitiveName);

        if (iu)
            this.bitString.set(_ID_iu);
        if (iur)
            this.bitString.set(_ID_iur);
        if (iub)
            this.bitString.set(_ID_iub);
        if (uu)
            this.bitString.set(_ID_uu);
    }

    @Override
    public boolean getIu() {
        return this.bitString.get(_ID_iu);
    }

    @Override
    public boolean getIur() {
        return this.bitString.get(_ID_iur);
    }

    @Override
    public boolean getIub() {
        return this.bitString.get(_ID_iub);
    }

    @Override
    public boolean getUu() {
        return this.bitString.get(_ID_uu);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getIu()) {
            sb.append("iu, ");
        }
        if (this.getIur()) {
            sb.append("iur, ");
        }
        if (this.getIub()) {
            sb.append("iub, ");
        }
        if (this.getUu()) {
            sb.append("uu, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
