
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
MAP V2:

activateSS OPERATION ::= {
  --Timer m
  ARGUMENT SS-ForBS-Code
  RESULT SS-Info
  -- optional
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | bearerServiceNotProvisioned | teleserviceNotProvisioned | callBarred |
           illegalSS-Operation | ss-ErrorStatus | ss-SubscriptionViolation | ss-Incompatibility | negativePW-Check | numberOfPW-AttemptsViolation}
  CODE local:12 }

ARGUMENT SS-ForBS-Code
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ActivateSSRequest extends SupplementaryMessage {

    SSForBSCode getSsForBSCode();

}
