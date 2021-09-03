package org.restcomm.protocols.ss7.map.dialog;

import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.dialog.ProcedureCancellationReason;
import org.restcomm.protocols.ss7.map.api.dialog.ResourceUnavailableReason;

/**
 * MAP-UserAbortChoice ::= CHOICE { userSpecificReason [0] NULL, userResourceLimitation [1] NULL, resourceUnavailable [2]
 * ResourceUnavailableReason, applicationProcedureCancellation [3] ProcedureCancellationReason}
 *
 * @author amit bhayani
 *
 */
public class MAPUserAbortChoiceImpl implements MAPUserAbortChoice {

    protected static final int USER_SPECIFIC_REASON_TAG = 0;
    protected static final int USER_RESOURCE_LIMITATION_TAG = 1;
    protected static final int RESOURCE_UNAVAILABLE = 2;
    protected static final int APPLICATION_PROCEDURE_CANCELLATION = 3;

    private ProcedureCancellationReason procedureCancellationReason = null;
    private boolean isProcedureCancellationReason = false;

    private ResourceUnavailableReason resourceUnavailableReason = null;
    private boolean isResourceUnavailableReason = false;

    private boolean isUserResourceLimitation = false;

    private boolean isUserSpecificReason = false;

    public ProcedureCancellationReason getProcedureCancellationReason() {
        return this.procedureCancellationReason;
    }

    public ResourceUnavailableReason getResourceUnavailableReason() {
        return this.resourceUnavailableReason;
    }

    public boolean isProcedureCancellationReason() {
        return this.isProcedureCancellationReason;
    }

    public boolean isResourceUnavailableReason() {
        return this.isResourceUnavailableReason;
    }

    public boolean isUserResourceLimitation() {
        return this.isUserResourceLimitation;
    }

    public boolean isUserSpecificReason() {
        return this.isUserSpecificReason;
    }

    public void setProcedureCancellationReason(ProcedureCancellationReason procedureCancellationReason) {
        this.procedureCancellationReason = procedureCancellationReason;
        this.isProcedureCancellationReason = true;
    }

    public void setResourceUnavailableReason(ResourceUnavailableReason resourceUnavailableReason) {
        this.resourceUnavailableReason = resourceUnavailableReason;
        this.isResourceUnavailableReason = true;
    }

    public void setUserResourceLimitation() {
        this.isUserResourceLimitation = true;
    }

    public void setUserSpecificReason() {
        this.isUserSpecificReason = true;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("MAPUserAbortChoice [");
        if (this.isUserSpecificReason)
            sb.append(" UserSpecificReason");
        if (this.isUserResourceLimitation)
            sb.append(" UserResourceLimitation");
        if (this.isResourceUnavailableReason) {
            sb.append(" ResourceUnavailableReason=");
            if (this.resourceUnavailableReason != null)
                sb.append(this.resourceUnavailableReason.toString());
        }
        if (this.isProcedureCancellationReason) {
            sb.append(" ProcedureCancellationReason=");
            if (this.procedureCancellationReason != null)
                sb.append(this.procedureCancellationReason.toString());
        }
        sb.append("]");

        return sb.toString();
    }

}
