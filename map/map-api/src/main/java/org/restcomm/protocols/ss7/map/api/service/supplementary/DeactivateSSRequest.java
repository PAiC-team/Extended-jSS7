
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
MAP V2:

deactivateSS OPERATION ::= {
  --Timer m
  ARGUMENT SS-ForBS-Code
  RESULT SS-Info
  -- optional
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | bearerServiceNotProvisioned | teleserviceNotProvisioned | callBarred | illegalSS-Operation |
           ss-ErrorStatus | ss-SubscriptionViolation | negativePW-Check | numberOfPW-AttemptsViolation} CODE local:13 }

ARGUMENT SS-ForBS-Code
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DeactivateSSRequest extends SupplementaryMessage {

    SSForBSCode getSsForBSCode();

}
