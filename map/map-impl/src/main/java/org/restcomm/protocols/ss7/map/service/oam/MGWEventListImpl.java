
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.MGWEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class MGWEventListImpl extends BitStringBase implements MGWEventList {

    static final int _ID_context = 0;

    public static final String _PrimitiveName = "MGWEventList";

    public MGWEventListImpl() {
        super(1, 8, 1, _PrimitiveName);
    }

    public MGWEventListImpl(boolean context) {
        super(1, 8, 1, _PrimitiveName);

        if (context)
            this.bitString.set(_ID_context);
    }

    @Override
    public boolean getContext() {
        return this.bitString.get(_ID_context);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getContext()) {
            sb.append("context, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
