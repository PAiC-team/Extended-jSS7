
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * noteMsPresentForGprs OPERATION ::= { --Timer m ARGUMENT NoteMsPresentForGprsArg RESULT NoteMsPresentForGprsRes -- optional
 * ERRORS { systemFailure | dataMissing | unexpectedDataValue | unknownSubscriber} CODE local:26 }
 *
 * NoteMsPresentForGprsArg ::= SEQUENCE { imsi [0] IMSI, sgsn-Address [1] GSN-Address, ggsn-Address [2] GSN-Address OPTIONAL,
 * extensionContainer [3] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NoteMsPresentForGprsRequest extends PdpContextActivationMessage {

    IMSI getImsi();

    GSNAddress getSgsnAddress();

    GSNAddress getGgsnAddress();

    MAPExtensionContainer getExtensionContainer();

}
