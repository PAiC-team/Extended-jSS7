
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 * CUG-Interlock ::= OCTET STRING (SIZE (4))
 *
 * CUG interlock code: this is a means of identifying closed user group membership within the network. At the calling side, if a
 * closed user group match exists, the CUG index identifying a closed user group maps to the closed user group interlock code
 * for that closed user group. If a closed user group match exists at the called side the closed user group interlock code
 * identifying a closed user group maps to the CUG index representing that closed user group. Closed user group interlock code
 * is not an access concept, but is used for clarity during the descriptions of signalling procedures and flows.
 *
 *
 * @author cristian veliscu
 *
 */
public interface CUGInterlock extends Serializable {
    byte[] getData();
}