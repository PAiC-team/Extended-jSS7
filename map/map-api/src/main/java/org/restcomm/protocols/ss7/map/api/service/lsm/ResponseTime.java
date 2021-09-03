package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
 * ResponseTime ::= SEQUENCE { responseTimeCategory ResponseTimeCategory, ...} -- note: an expandable SEQUENCE simplifies later
 * addition of a numeric response time.
 *
 * @author amit bhayani
 *
 */
public interface ResponseTime extends Serializable {

    ResponseTimeCategory getResponseTimeCategory();

}
