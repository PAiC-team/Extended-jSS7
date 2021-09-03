
package org.restcomm.protocols.ss7.cap.api.EsiGprs;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.InitiatingEntity;

/**
 *
 disconnectSpecificInformation [3] SEQUENCE { initiatingEntity [0] InitiatingEntity OPTIONAL, ..., routeingAreaUpdate [1] NULL
 * OPTIONAL },
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DisconnectSpecificInformation extends Serializable {

    InitiatingEntity getInitiatingEntity();

    boolean getRouteingAreaUpdate();

}