
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ISRInformation;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ISRInformationImpl extends BitStringBase implements ISRInformation {

    private static final int _INDEX_updateMME = 0;
    private static final int _INDEX_cancelSGSN = 1;
    private static final int _INDEX_initialAttachIndicator = 2;

    public ISRInformationImpl(boolean updateMME, boolean cancelSGSN, boolean initialAttachIndicator) {
        super(3, 8, 3, "ISRInformation");
        if (updateMME)
            this.bitString.set(_INDEX_updateMME);
        if (cancelSGSN)
            this.bitString.set(_INDEX_cancelSGSN);
        if (initialAttachIndicator)
            this.bitString.set(_INDEX_initialAttachIndicator);
    }

    public ISRInformationImpl() {
        super(3, 8, 3, "ISRInformation");
    }

    @Override
    public boolean getUpdateMME() {
        return this.bitString.get(_INDEX_updateMME);
    }

    @Override
    public boolean getCancelSGSN() {
        return this.bitString.get(_INDEX_cancelSGSN);
    }

    @Override
    public boolean getInitialAttachIndicator() {
        return this.bitString.get(_INDEX_initialAttachIndicator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.getUpdateMME())
            sb.append("UpdateMME, ");
        if (this.getCancelSGSN())
            sb.append("CancelSGSN, ");
        if (this.getInitialAttachIndicator())
            sb.append("InitialAttachIndicator ");
        sb.append("]");
        return sb.toString();
    }

}
