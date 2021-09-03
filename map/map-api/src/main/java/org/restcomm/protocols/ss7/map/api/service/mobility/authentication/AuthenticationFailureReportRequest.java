
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
MAP V3:
authenticationFailureReport OPERATION ::= {
  --Timer m
  ARGUMENT AuthenticationFailureReportArg
  RESULT AuthenticationFailureReportRes
  -- optional
  ERRORS { systemFailure | unexpectedDataValue | unknownSubscriber }
  CODE local:15
}

AuthenticationFailureReportArg ::= SEQUENCE {
  imsi                IMSI,
  failureCause        FailureCause,
  extensionContainer  ExtensionContainer OPTIONAL,
  ... ,
  re-attempt          BOOLEAN OPTIONAL,
  accessType          AccessType OPTIONAL,
  rand                RAND OPTIONAL,
  vlr-Number          [0] ISDN-AddressString OPTIONAL,
  sgsn-Number         [1] ISDN-AddressString OPTIONAL
}

RAND ::= OCTET STRING (SIZE (16))
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AuthenticationFailureReportRequest extends MobilityMessage {

    IMSI getImsi();

    FailureCause getFailureCause();

    MAPExtensionContainer getExtensionContainer();

    Boolean getReAttempt();

    AccessType getAccessType();

    byte[] getRand();

    ISDNAddressString getVlrNumber();

    ISDNAddressString getSgsnNumber();

}
