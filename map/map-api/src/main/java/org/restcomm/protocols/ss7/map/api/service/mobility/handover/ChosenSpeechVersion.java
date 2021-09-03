
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 ChosenSpeechVersion ::= OCTET STRING (SIZE (1)) -- Octets are coded according the Speech Version (chosen) information element
 * in 3GPP TS -- 48.008
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChosenSpeechVersion extends Serializable {

    int getData();

}
