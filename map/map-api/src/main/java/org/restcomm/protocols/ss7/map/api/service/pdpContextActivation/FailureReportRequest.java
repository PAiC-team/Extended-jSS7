
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * failureReport OPERATION ::= { --Timer m ARGUMENT FailureReportArg RESULT FailureReportRes -- optional ERRORS { systemFailure
 * | dataMissing | unexpectedDataValue | unknownSubscriber} CODE local:25 }
 *
 * FailureReportArg ::= SEQUENCE { imsi [0] IMSI, ggsn-Number [1] ISDN-AddressString , ggsn-Address [2] GSN-Address OPTIONAL,
 * extensionContainer [3] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FailureReportRequest extends PdpContextActivationMessage {

    IMSI getImsi();

    ISDNAddressString getGgsnNumber();

    GSNAddress getGgsnAddress();

    MAPExtensionContainer getExtensionContainer();

}
