
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.IMEI;

/**
 *
 ADD-Info ::= SEQUENCE { imeisv [0] IMEI, skipSubscriberDataUpdate [1] NULL OPTIONAL, -- The skipSubscriberDataUpdate
 * parameter in the UpdateLocationArg and the ADD-Info -- structures carry the same semantic. ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ADDInfo extends Serializable {

    IMEI getImeisv();

    boolean getSkipSubscriberDataUpdate();

}
