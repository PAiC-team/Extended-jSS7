
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
 TraceRecordingSessionReference ::= OCTET STRING (SIZE (2))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceRecordingSessionReference extends Serializable {

    byte[] getData();

}
