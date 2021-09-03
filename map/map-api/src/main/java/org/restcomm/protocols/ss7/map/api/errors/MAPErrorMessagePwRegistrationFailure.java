
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 pw-RegistrationFailure ERROR ::= { PARAMETER PW-RegistrationFailureCause CODE local:37 }
 *
 * PW-RegistrationFailureCause ::= ENUMERATED { undetermined (0), invalidFormat (1), newPasswordsMismatch (2)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessagePwRegistrationFailure extends MAPErrorMessage {

    PWRegistrationFailureCause getPWRegistrationFailureCause();

    void setPWRegistrationFailureCause(PWRegistrationFailureCause val);

}
