
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;

/**
 *
 SentParameter ::= CHOICE { imsi [0] IMSI, authenticationSet [1] AuthenticationSet, subscriberData [2] SubscriberData, ki [4]
 * Ki}
 *
 * Ki ::= octet STRING (SIZE (16))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SentParameter extends Serializable {

    IMSI getImsi();

    AuthenticationSet getAuthenticationSet();

    SubscriberData getSubscriberData();

    byte[] getKi();

}
