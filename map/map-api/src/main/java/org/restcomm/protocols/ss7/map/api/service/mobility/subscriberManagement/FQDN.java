
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 FQDN ::= OCTET STRING (SIZE (9..255))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FQDN extends Serializable {

    byte[] getData();

}
