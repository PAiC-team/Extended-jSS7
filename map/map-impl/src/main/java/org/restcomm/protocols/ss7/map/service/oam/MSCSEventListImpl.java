
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.MSCSEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class MSCSEventListImpl extends BitStringBase implements MSCSEventList {

    static final int _ID_moMtCall = 0;
    static final int _ID_moMtSms = 1;
    static final int _ID_luImsiAttachImsiDetach = 2;
    static final int _ID_handovers = 3;
    static final int _ID_ss = 4;

    public static final String _PrimitiveName = "MSCSEventList";

    public MSCSEventListImpl() {
        super(5, 16, 5, _PrimitiveName);
    }

    public MSCSEventListImpl(boolean moMtCall, boolean moMtSms, boolean luImsiAttachImsiDetach, boolean handovers, boolean ss) {
        super(5, 16, 5, _PrimitiveName);

        if (moMtCall)
            this.bitString.set(_ID_moMtCall);
        if (moMtSms)
            this.bitString.set(_ID_moMtSms);
        if (luImsiAttachImsiDetach)
            this.bitString.set(_ID_luImsiAttachImsiDetach);
        if (handovers)
            this.bitString.set(_ID_handovers);
        if (ss)
            this.bitString.set(_ID_ss);
    }

    @Override
    public boolean getMoMtCall() {
        return this.bitString.get(_ID_moMtCall);
    }

    @Override
    public boolean getMoMtSms() {
        return this.bitString.get(_ID_moMtSms);
    }

    @Override
    public boolean getLuImsiAttachImsiDetach() {
        return this.bitString.get(_ID_luImsiAttachImsiDetach);
    }

    @Override
    public boolean getHandovers() {
        return this.bitString.get(_ID_handovers);
    }

    @Override
    public boolean getSs() {
        return this.bitString.get(_ID_ss);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getMoMtCall()) {
            sb.append("moMtCall, ");
        }
        if (this.getMoMtSms()) {
            sb.append("moMtSms, ");
        }
        if (this.getLuImsiAttachImsiDetach()) {
            sb.append("luImsiAttachImsiDetach, ");
        }
        if (this.getHandovers()) {
            sb.append("handovers, ");
        }
        if (this.getSs()) {
            sb.append("ss, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
