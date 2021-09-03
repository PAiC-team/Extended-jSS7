
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AccessRestrictionData;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class AccessRestrictionDataImpl extends BitStringBase implements AccessRestrictionData {

    private static final int _INDEX_UtranNotAllowed = 0;
    private static final int _INDEX_GeranNotAllowed = 1;
    private static final int _INDEX_GanNotAllowed = 2;
    private static final int _INDEX_IHspaEvolutionNotAllowed = 3;
    private static final int _INDEX_EUtranNotAllowed = 4;
    private static final int _INDEX_HoToNon3GPPAccessNotAllowed = 5;

    public AccessRestrictionDataImpl() {
        super(2, 8, 6, "AccessRestrictionData");
    }

    public AccessRestrictionDataImpl(boolean utranNotAllowed, boolean geranNotAllowed, boolean ganNotAllowed,
            boolean iHspaEvolutionNotAllowed, boolean eUtranNotAllowed, boolean hoToNon3GPPAccessNotAllowed) {
        super(2, 8, 6, "AccessRestrictionData");
        if (utranNotAllowed)
            this.bitString.set(_INDEX_UtranNotAllowed);
        if (geranNotAllowed)
            this.bitString.set(_INDEX_GeranNotAllowed);
        if (ganNotAllowed)
            this.bitString.set(_INDEX_GanNotAllowed);
        if (iHspaEvolutionNotAllowed)
            this.bitString.set(_INDEX_IHspaEvolutionNotAllowed);
        if (eUtranNotAllowed)
            this.bitString.set(_INDEX_EUtranNotAllowed);
        if (hoToNon3GPPAccessNotAllowed)
            this.bitString.set(_INDEX_HoToNon3GPPAccessNotAllowed);

    }

    @Override
    public boolean getUtranNotAllowed() {
        return this.bitString.get(_INDEX_UtranNotAllowed);
    }

    @Override
    public boolean getGeranNotAllowed() {
        return this.bitString.get(_INDEX_GeranNotAllowed);
    }

    @Override
    public boolean getGanNotAllowed() {
        return this.bitString.get(_INDEX_GanNotAllowed);
    }

    @Override
    public boolean getIHspaEvolutionNotAllowed() {
        return this.bitString.get(_INDEX_IHspaEvolutionNotAllowed);
    }

    @Override
    public boolean getEUtranNotAllowed() {
        return this.bitString.get(_INDEX_EUtranNotAllowed);
    }

    @Override
    public boolean getHoToNon3GPPAccessNotAllowed() {
        return this.bitString.get(_INDEX_HoToNon3GPPAccessNotAllowed);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.getUtranNotAllowed())
            sb.append("UtranNotAllowed, ");
        if (this.getGeranNotAllowed())
            sb.append("GeranNotAllowed, ");
        if (this.getGanNotAllowed())
            sb.append("GanNotAllowed, ");
        if (this.getIHspaEvolutionNotAllowed())
            sb.append("IHspaEvolutionNotAllowed, ");
        if (this.getEUtranNotAllowed())
            sb.append("EUtranNotAllowed, ");
        if (this.getHoToNon3GPPAccessNotAllowed())
            sb.append("HoToNon3GPPAccessNotAllowed");
        sb.append("]");
        return sb.toString();
    }

}
