
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.MGWInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class MGWInterfaceListImpl extends BitStringBase implements MGWInterfaceList {

    static final int _ID_mc = 0;
    static final int _ID_nbUp = 1;
    static final int _ID_iuUp = 2;

    public static final String _PrimitiveName = "MGWInterfaceList";

    public MGWInterfaceListImpl() {
        super(3, 8, 3, _PrimitiveName);
    }

    public MGWInterfaceListImpl(boolean mc, boolean nbUp, boolean iuUp) {
        super(3, 8, 3, _PrimitiveName);

        if (mc)
            this.bitString.set(_ID_mc);
        if (nbUp)
            this.bitString.set(_ID_nbUp);
        if (iuUp)
            this.bitString.set(_ID_iuUp);
    }

    @Override
    public boolean getMc() {
        return this.bitString.get(_ID_mc);
    }

    @Override
    public boolean getNbUp() {
        return this.bitString.get(_ID_nbUp);
    }

    @Override
    public boolean getIuUp() {
        return this.bitString.get(_ID_iuUp);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getMc()) {
            sb.append("mc, ");
        }
        if (this.getNbUp()) {
            sb.append("nbUp, ");
        }
        if (this.getIuUp()) {
            sb.append("iuUp, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
