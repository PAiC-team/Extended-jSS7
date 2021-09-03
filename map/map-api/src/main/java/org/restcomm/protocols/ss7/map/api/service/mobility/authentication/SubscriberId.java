
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.TMSI;

/**
 *
 SubscriberId ::= CHOICE { imsi [0] IMSI, tmsi [1] TMSI}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SubscriberId extends Serializable {

    IMSI getImsi();

    TMSI getTmsi();

}
