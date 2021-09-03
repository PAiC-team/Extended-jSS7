
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
 RESULT SS-Info
 -- optional
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DeactivateSSResponse extends SupplementaryMessage {

    SSInfo getSsInfo();

}
