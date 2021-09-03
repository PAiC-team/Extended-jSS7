
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 BSSMAP-ServiceHandoverInfo ::= SEQUENCE { bssmap-ServiceHandover BSSMAP-ServiceHandover, rab-Id RAB-Id, -- RAB Identity is
 * needed to relate the service handovers with the radio access bearers. ...}
 *
 * RAB-Id ::= INTEGER (1..255)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BSSMAPServiceHandoverInfo extends Serializable {

    BSSMAPServiceHandover getBSSMAPServiceHandover();

    Integer getRABId();

}
