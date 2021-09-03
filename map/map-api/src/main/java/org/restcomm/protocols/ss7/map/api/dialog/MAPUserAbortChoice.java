package org.restcomm.protocols.ss7.map.api.dialog;

import java.io.Serializable;

/**
 * MAP-UserAbortChoice ::= CHOICE { userSpecificReason [0] NULL, userResourceLimitation [1] NULL, resourceUnavailable [2]
 * ResourceUnavailableReason, applicationProcedureCancellation [3] ProcedureCancellationReason}
 *
 * @author amit bhayani
 *
 */
public interface MAPUserAbortChoice extends Serializable {

    void setUserSpecificReason();

    void setUserResourceLimitation();

    void setResourceUnavailableReason(ResourceUnavailableReason resourceUnavailableReason);

    void setProcedureCancellationReason(ProcedureCancellationReason procedureCancellationReason);

    ProcedureCancellationReason getProcedureCancellationReason();

    ResourceUnavailableReason getResourceUnavailableReason();

    boolean isUserSpecificReason();

    boolean isUserResourceLimitation();

    boolean isResourceUnavailableReason();

    boolean isProcedureCancellationReason();

}
