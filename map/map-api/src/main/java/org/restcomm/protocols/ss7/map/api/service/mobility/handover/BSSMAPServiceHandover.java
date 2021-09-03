
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 BSSMAP-ServiceHandover ::= OCTET STRING (SIZE (1)) -- Octets are coded according the Service Handover information element in
 * -- 3GPP TS 48.008.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BSSMAPServiceHandover extends Serializable {

    int getData();

}
