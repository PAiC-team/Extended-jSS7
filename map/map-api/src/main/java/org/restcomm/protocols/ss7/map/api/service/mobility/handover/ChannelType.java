
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 ChannelType ::= octet STRING (SIZE (1..10)) -- concatenation of -- speech data indicator -- channel rate -- speech encoding
 * algorithm/ data rate -- and transparency indicator -- as defined in TS GSM 08.08
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChannelType extends Serializable {

    byte[] getData();

}
