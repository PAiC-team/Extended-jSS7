
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.PGWEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class PGWEventListImpl extends BitStringBase implements PGWEventList {

    static final int _ID_pdnConnectionCreation = 0;
    static final int _ID_pdnConnectionTermination = 1;
    static final int _ID_bearerActivationModificationDeletion = 2;

    public static final String _PrimitiveName = "PGWEventList";

    public PGWEventListImpl() {
        super(3, 8, 3, _PrimitiveName);
    }

    public PGWEventListImpl(boolean pdnConnectionCreation, boolean pdnConnectionTermination, boolean bearerActivationModificationDeletion) {
        super(3, 8, 3, _PrimitiveName);

        if (pdnConnectionCreation)
            this.bitString.set(_ID_pdnConnectionCreation);
        if (pdnConnectionTermination)
            this.bitString.set(_ID_pdnConnectionTermination);
        if (bearerActivationModificationDeletion)
            this.bitString.set(_ID_bearerActivationModificationDeletion);
    }

    @Override
    public boolean getPdnConnectionCreation() {
        return this.bitString.get(_ID_pdnConnectionCreation);
    }

    @Override
    public boolean getPdnConnectionTermination() {
        return this.bitString.get(_ID_pdnConnectionTermination);
    }

    @Override
    public boolean getBearerActivationModificationDeletion() {
        return this.bitString.get(_ID_bearerActivationModificationDeletion);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getPdnConnectionCreation()) {
            sb.append("pdnConnectionCreation, ");
        }
        if (this.getPdnConnectionTermination()) {
            sb.append("pdnConnectionTermination, ");
        }
        if (this.getBearerActivationModificationDeletion()) {
            sb.append("bearerActivationModificationDeletion, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
