
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
RESULT Password
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GetPasswordResponse extends SupplementaryMessage {

    Password getPassword();

}