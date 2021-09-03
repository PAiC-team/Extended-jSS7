
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.GGSNEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class GGSNEventListImpl extends BitStringBase implements GGSNEventList {

    static final int _ID_pdpContext = 0;
    static final int _ID_mbmsContext = 1;

    public static final String _PrimitiveName = "GGSNEventList";

    public GGSNEventListImpl() {
        super(2, 8, 2, _PrimitiveName);
    }

    public GGSNEventListImpl(boolean pdpContext, boolean mbmsContext) {
        super(2, 8, 2, _PrimitiveName);

        if (pdpContext)
            this.bitString.set(_ID_pdpContext);
        if (mbmsContext)
            this.bitString.set(_ID_mbmsContext);
    }

    @Override
    public boolean getPdpContext() {
        return this.bitString.get(_ID_pdpContext);
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
        if (this.getMbmsContext()) {
            sb.append("mbmsContext, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
