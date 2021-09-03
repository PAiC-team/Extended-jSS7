
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
 * AllowedServices ::= BIT STRING { firstServiceAllowed (0), secondServiceAllowed (1) } (SIZE (2..8)) -- firstService is the
 * service indicated in the networkSignalInfo -- secondService is the service indicated in the networkSignalInfo2 -- Other bits
 * than listed above shall be discarded
 *
 * @author cristian veliscu
 *
 */
public interface AllowedServices extends Serializable {

    boolean getFirstServiceAllowed();

    boolean getSecondServiceAllowed();

}