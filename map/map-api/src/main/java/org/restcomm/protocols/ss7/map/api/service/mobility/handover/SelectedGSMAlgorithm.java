
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 SelectedGSM-Algorithm ::= OCTET STRING (SIZE (1)) -- internal structure is coded as Algorithm identifier octet from Chosen
 * Encryption -- Algorithm defined in 3GPP TS 48.008 -- A node shall mark only the selected GSM algorithm
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SelectedGSMAlgorithm extends Serializable {

    int getData();

}
