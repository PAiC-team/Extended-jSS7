
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.MSCSInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class MSCSInterfaceListImpl extends BitStringBase implements MSCSInterfaceList {

    static final int _ID_a = 0;
    static final int _ID_iu = 1;
    static final int _ID_mc = 2;
    static final int _ID_mapG = 3;
    static final int _ID_mapB = 4;
    static final int _ID_mapE = 5;
    static final int _ID_mapF = 6;
    static final int _ID_cap = 7;
    static final int _ID_mapD = 8;
    static final int _ID_mapC = 9;

    public static final String _PrimitiveName = "MSCSInterfaceList";

    public MSCSInterfaceListImpl() {
        super(10, 16, 10, _PrimitiveName);
    }

    public MSCSInterfaceListImpl(boolean a, boolean iu, boolean mc, boolean mapG, boolean mapB, boolean mapE, boolean mapF, boolean cap, boolean mapD,
            boolean mapC) {
        super(10, 16, 10, _PrimitiveName);

        if (a)
            this.bitString.set(_ID_a);
        if (iu)
            this.bitString.set(_ID_iu);
        if (mc)
            this.bitString.set(_ID_mc);
        if (mapG)
            this.bitString.set(_ID_mapG);
        if (mapB)
            this.bitString.set(_ID_mapB);
        if (mapE)
            this.bitString.set(_ID_mapE);
        if (mapF)
            this.bitString.set(_ID_mapF);
        if (cap)
            this.bitString.set(_ID_cap);
        if (mapD)
            this.bitString.set(_ID_mapD);
        if (mapC)
            this.bitString.set(_ID_mapC);
    }

    @Override
    public boolean getA() {
        return this.bitString.get(_ID_a);
    }

    @Override
    public boolean getIu() {
        return this.bitString.get(_ID_iu);
    }

    @Override
    public boolean getMc() {
        return this.bitString.get(_ID_mc);
    }

    @Override
    public boolean getMapG() {
        return this.bitString.get(_ID_mapG);
    }

    @Override
    public boolean getMapB() {
        return this.bitString.get(_ID_mapB);
    }

    @Override
    public boolean getMapE() {
        return this.bitString.get(_ID_mapE);
    }

    @Override
    public boolean getMapF() {
        return this.bitString.get(_ID_mapF);
    }

    @Override
    public boolean getCap() {
        return this.bitString.get(_ID_cap);
    }

    @Override
    public boolean getMapD() {
        return this.bitString.get(_ID_mapD);
    }

    @Override
    public boolean getMapC() {
        return this.bitString.get(_ID_mapC);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getA()) {
            sb.append("a, ");
        }
        if (this.getIu()) {
            sb.append("iu, ");
        }
        if (this.getMc()) {
            sb.append("mc, ");
        }
        if (this.getMapG()) {
            sb.append("mapG, ");
        }
        if (this.getMapB()) {
            sb.append("mapB, ");
        }
        if (this.getMapE()) {
            sb.append("mapE, ");
        }
        if (this.getMapF()) {
            sb.append("mapF, ");
        }
        if (this.getCap()) {
            sb.append("cap, ");
        }
        if (this.getMapD()) {
            sb.append("mapD, ");
        }
        if (this.getMapC()) {
            sb.append("mapC, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
