
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
MAP V2:

eraseSS OPERATION ::= {
  --Timer m
  ARGUMENT  SS-ForBS-Code
  RESULT    SS-Info
  -- optional
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | bearerServiceNotProvisioned | teleserviceNotProvisioned |
           callBarred | illegalSS-Operation | ss-ErrorStatus }
  CODE local:11
}

ARGUMENT SS-ForBS-Code
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EraseSSRequest extends SupplementaryMessage {

    SSForBSCode getSsForBSCode();

}
