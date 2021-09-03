
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
MAP V2:

registerPassword OPERATION ::= {
  --Timer ml
  ARGUMENT SS-Code
  RESULT Password
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | callBarred | ss-SubscriptionViolation |
           pw-RegistrationFailure | negativePW-Check | numberOfPW-AttemptsViolation}
  LINKED { getPassword }
  CODE local:17
}

RESULT Password
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RegisterPasswordRequest extends SupplementaryMessage {

    SSCode getSsCode();

}
