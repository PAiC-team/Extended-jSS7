
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.MMEEventList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class MMEEventListImpl extends BitStringBase implements MMEEventList {

    static final int _ID_ueInitiatedPDNconectivityRequest = 0;
    static final int _ID_serviceRequests = 1;
    static final int _ID_initialAttachTrackingAreaUpdateDetach = 2;
    static final int _ID_ueInitiatedPDNdisconnection = 3;
    static final int _ID_bearerActivationModificationDeletion = 4;
    static final int _ID_handover = 5;

    public static final String _PrimitiveName = "MMEEventList";

    public MMEEventListImpl() {
        super(6, 8, 6, _PrimitiveName);
    }

    public MMEEventListImpl(boolean ueInitiatedPDNconectivityRequest, boolean serviceRequestts, boolean initialAttachTrackingAreaUpdateDetach,
            boolean ueInitiatedPDNdisconnection, boolean bearerActivationModificationDeletion, boolean handover) {
        super(6, 8, 6, _PrimitiveName);

        if (ueInitiatedPDNconectivityRequest)
            this.bitString.set(_ID_ueInitiatedPDNconectivityRequest);
        if (serviceRequestts)
            this.bitString.set(_ID_serviceRequests);
        if (initialAttachTrackingAreaUpdateDetach)
            this.bitString.set(_ID_initialAttachTrackingAreaUpdateDetach);
        if (ueInitiatedPDNdisconnection)
            this.bitString.set(_ID_ueInitiatedPDNdisconnection);
        if (bearerActivationModificationDeletion)
            this.bitString.set(_ID_bearerActivationModificationDeletion);
        if (handover)
            this.bitString.set(_ID_handover);
    }

    @Override
    public boolean getUeInitiatedPDNconectivityRequest() {
        return this.bitString.get(_ID_ueInitiatedPDNconectivityRequest);
    }

    @Override
    public boolean getServiceRequests() {
        return this.bitString.get(_ID_serviceRequests);
    }

    @Override
    public boolean getInitialAttachTrackingAreaUpdateDetach() {
        return this.bitString.get(_ID_initialAttachTrackingAreaUpdateDetach);
    }

    @Override
    public boolean getUeInitiatedPDNdisconnection() {
        return this.bitString.get(_ID_ueInitiatedPDNdisconnection);
    }

    @Override
    public boolean getBearerActivationModificationDeletion() {
        return this.bitString.get(_ID_bearerActivationModificationDeletion);
    }

    @Override
    public boolean getHandover() {
        return this.bitString.get(_ID_handover);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getUeInitiatedPDNconectivityRequest()) {
            sb.append("ueInitiatedPDNconectivityRequest, ");
        }
        if (this.getServiceRequests()) {
            sb.append("serviceRequests, ");
        }
        if (this.getInitialAttachTrackingAreaUpdateDetach()) {
            sb.append("initialAttachTrackingAreaUpdateDetach, ");
        }
        if (this.getUeInitiatedPDNdisconnection()) {
            sb.append("ueInitiatedPDNdisconnection, ");
        }
        if (this.getBearerActivationModificationDeletion()) {
            sb.append("bearerActivationModificationDeletion, ");
        }
        if (this.getHandover()) {
            sb.append("handover, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
