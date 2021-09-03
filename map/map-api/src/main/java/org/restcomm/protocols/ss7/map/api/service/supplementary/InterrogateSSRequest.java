
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
MAP V2:

interrogateSS OPERATION ::= {
  --Timer m
  ARGUMENT SS-ForBS-Code
  RESULT InterrogateSS-Res
  ERRORS { systemFailure | dataMissing | unexpectedDataValue | bearerServiceNotProvisioned | teleserviceNotProvisioned |
           callBarred | illegalSS-Operation | ss-NotAvailable}
  CODE local:14
}

ARGUMENT SS-ForBS-Code
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InterrogateSSRequest extends SupplementaryMessage {

    SSForBSCode getSsForBSCode();

}