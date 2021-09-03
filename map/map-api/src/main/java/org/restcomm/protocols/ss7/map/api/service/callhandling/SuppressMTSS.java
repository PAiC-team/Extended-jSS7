
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
 * SuppressMTSS ::= BIT STRING { suppressCUG (0), suppressCCBS (1) } (SIZE (2..16)) -- Other bits than listed above shall be
 * discarded
 *
 * @author cristian veliscu
 *
 */
public interface SuppressMTSS extends Serializable {

    boolean getSuppressCUG();

    boolean getSuppressCCBS();

}