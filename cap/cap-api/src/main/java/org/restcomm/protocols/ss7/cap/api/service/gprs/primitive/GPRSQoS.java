
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtQoSSubscribed;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.QoSSubscribed;

/**
 *
 GPRS-QoS ::= CHOICE { short-QoS-format [0] QoS-Subscribed, long-QoS-format [1] Ext-QoS-Subscribed } -- Short-QoS-format shall
 * be sent for QoS in pre GSM release 99 format. -- Long-QoS-format shall be sent for QoS in GSM release 99 (and beyond) format.
 * -- Which of the two QoS formats shall be sent is determined by which QoS -- format is available in the SGSN at the time of
 * sending. -- Refer to 3GPP TS 29.002 [11] for encoding details of QoS-Subscribed and -- Ext-QoS-Subscribed.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSQoS extends Serializable {

    QoSSubscribed getShortQoSFormat();

    ExtQoSSubscribed getLongQoSFormat();

}