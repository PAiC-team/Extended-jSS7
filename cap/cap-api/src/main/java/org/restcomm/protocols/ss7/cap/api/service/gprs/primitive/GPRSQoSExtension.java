
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Ext2QoSSubscribed;

/**
 *
 GPRS-QoS-Extension ::= SEQUENCE { supplement-to-long-QoS-format [0] Ext2-QoS-Subscribed, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSQoSExtension extends Serializable {

    Ext2QoSSubscribed getSupplementToLongQoSFormat();

}