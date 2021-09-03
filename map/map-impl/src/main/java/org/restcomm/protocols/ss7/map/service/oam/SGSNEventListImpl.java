
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.SGSNEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class SGSNEventListImpl extends BitStringBase implements SGSNEventList {

    static final int _ID_pdpContext = 0;
    static final int _ID_moMtSms = 1;
    static final int _ID_rauGprsAttachGprsDetach = 2;
    static final int _ID_mbmsContext = 3;

    public static final String _PrimitiveName = "SGSNEventList";

    public SGSNEventListImpl() {
        super(4, 16, 4, _PrimitiveName);
    }

    public SGSNEventListImpl(boolean pdpContext, boolean moMtSms, boolean rauGprsAttachGprsDetach, boolean mbmsContext) {
        super(4, 16, 4, _PrimitiveName);

        if (pdpContext)
            this.bitString.set(_ID_pdpContext);
        if (moMtSms)
            this.bitString.set(_ID_moMtSms);
        if (rauGprsAttachGprsDetach)
            this.bitString.set(_ID_rauGprsAttachGprsDetach);
        if (mbmsContext)
            this.bitString.set(_ID_mbmsContext);
    }

    @Override
    public boolean getPdpContext() {
        return this.bitString.get(_ID_pdpContext);
    }

    @Override
    public boolean getMoMtSms() {
        return this.bitString.get(_ID_moMtSms);
    }

    @Override
    public boolean getRauGprsAttachGprsDetach() {
        return this.bitString.get(_ID_rauGprsAttachGprsDetach);
    }

    @Override
    public boolean getMbmsContext() {
        return this.bitString.get(_ID_mbmsContext);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getPdpContext()) {
            sb.append("pdpContext, ");
        }
        if (this.getMoMtSms()) {
            sb.append("moMtSms, ");
        }
        if (this.getRauGprsAttachGprsDetach()) {
            sb.append("rauGprsAttachGprsDetach, ");
        }
        if (this.getMbmsContext()) {
            sb.append("mbmsContext, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
