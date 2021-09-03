package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;

/**
 * The MAP ReturnError message: SystemFailure
 *
 * systemFailure ERROR ::= { PARAMETER SystemFailureParam -- optional CODE local:34 }
 *
 *
 * SystemFailureParam ::= CHOICE { networkResource NetworkResource, -- networkResource must not be used in version 3
 * extensibleSystemFailureParam ExtensibleSystemFailureParam -- extensibleSystemFailureParam must not be used in version <3 }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageSystemFailure extends MAPErrorMessage {

    NetworkResource getNetworkResource();

    AdditionalNetworkResource getAdditionalNetworkResource();

    MAPExtensionContainer getExtensionContainer();

    long getMapProtocolVersion();

    void setNetworkResource(NetworkResource networkResource);

    void setAdditionalNetworkResource(AdditionalNetworkResource additionalNetworkResource);

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

    void setMapProtocolVersion(long mapProtocolVersion);
}
