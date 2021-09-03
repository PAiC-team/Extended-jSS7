package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredLocationEventType;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class DeferredLocationEventTypeImpl extends BitStringBase implements DeferredLocationEventType {

    private static final int _INDEX_MS_AVAILABLE = 0;
    private static final int _INDEX__ENTERING_INTO_AREA = 1;
    private static final int _INDEX_LEAVING_FROM_AREA = 2;
    private static final int _INDEX_BEING_INSIDE_AREA = 3;
    private static final int _INDEX_PERIODIC_LDR = 4;

    public DeferredLocationEventTypeImpl() {
        super(1, 16, 4, "DeferredLocationEventType");
    }

    public DeferredLocationEventTypeImpl(boolean msAvailable, boolean enteringIntoArea, boolean leavingFromArea,
                                         boolean beingInsideArea, boolean periodicLDR) {
        super(1, 16, 4, "DeferredLocationEventType");

        if (msAvailable)
            this.bitString.set(_INDEX_MS_AVAILABLE);
        if (enteringIntoArea)
            this.bitString.set(_INDEX__ENTERING_INTO_AREA);
        if (leavingFromArea)
            this.bitString.set(_INDEX_LEAVING_FROM_AREA);
        if (beingInsideArea)
            this.bitString.set(_INDEX_BEING_INSIDE_AREA);
        if (periodicLDR)
            this.bitString.set(_INDEX_PERIODIC_LDR);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.lsm.DeferredLocationEventType#getMsAvailable()
     */
    public boolean getMsAvailable() {
        return this.bitString.get(_INDEX_MS_AVAILABLE);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.lsm.DeferredLocationEventType#getEnteringIntoArea()
     */
    public boolean getEnteringIntoArea() {
        return this.bitString.get(_INDEX__ENTERING_INTO_AREA);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.lsm.DeferredLocationEventType#getLeavingFromArea()
     */
    public boolean getLeavingFromArea() {
        return this.bitString.get(_INDEX_LEAVING_FROM_AREA);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.lsm.DeferredLocationEventType#beingInsideArea()
     */
    public boolean getBeingInsideArea() {
        return this.bitString.get(_INDEX_BEING_INSIDE_AREA);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.lsm.DeferredLocationEventType#getPeriodicLDR()
     */
    public boolean getPeriodicLDR() {
        return  this.bitString.get((_INDEX_PERIODIC_LDR));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (getMsAvailable()) {
            sb.append("MsAvailable, ");
        }
        if (getEnteringIntoArea()) {
            sb.append("EnteringIntoArea, ");
        }
        if (getLeavingFromArea()) {
            sb.append("LeavingFromArea, ");
        }
        if (getBeingInsideArea()) {
            sb.append("BeingInsideArea, ");
        }
        if (getPeriodicLDR()) {
            sb.append("PeriodicLDR, ");
        }

        sb.append("]");

        return sb.toString();
    }
}
