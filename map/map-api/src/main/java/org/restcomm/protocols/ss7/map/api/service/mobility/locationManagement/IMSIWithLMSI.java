
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;

/**
 *
 IMSI-WithLMSI ::= SEQUENCE { imsi IMSI, lmsi LMSI, -- a special value 00000000 indicates that the LMSI is not in use ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface IMSIWithLMSI extends Serializable {

    IMSI getImsi();

    LMSI getLmsi();

}
