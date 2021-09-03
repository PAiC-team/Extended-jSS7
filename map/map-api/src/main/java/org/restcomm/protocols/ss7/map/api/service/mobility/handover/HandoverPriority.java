
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 HandoverPriority ::= octet STRING (SIZE (1)) -- The internal structure is defined in TS GSM 08.08.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface HandoverPriority extends Serializable {

    int getData();

}
