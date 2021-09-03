
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;

/**
 *
 ReportingPLMN::= SEQUENCE { plmn-Id [0] PLMN-Id, ran-Technology [1] RAN-Technology OPTIONAL, ran-PeriodicLocationSupport [2]
 * NULL OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReportingPLMN extends Serializable {

    PlmnId getPlmnId();

    RANTechnology getRanTechnology();

    boolean getRanPeriodicLocationSupport();

}
