
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 QualityOfService ::= SEQUENCE { requested-QoS [0] GPRS-QoS OPTIONAL, subscribed-QoS [1] GPRS-QoS OPTIONAL, negotiated-QoS [2]
 * GPRS-QoS OPTIONAL, ..., requested-QoS-Extension [3] GPRS-QoS-Extension OPTIONAL, subscribed-QoS-Extension [4]
 * GPRS-QoS-Extension OPTIONAL, negotiated-QoS-Extension [5] GPRS-QoS-Extension OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface QualityOfService extends Serializable {

    GPRSQoS getRequestedQoS();

    GPRSQoS getSubscribedQoS();

    GPRSQoS getNegotiatedQoS();

    GPRSQoSExtension getRequestedQoSExtension();

    GPRSQoSExtension getSubscribedQoSExtension();

    GPRSQoSExtension getNegotiatedQoSExtension();

}