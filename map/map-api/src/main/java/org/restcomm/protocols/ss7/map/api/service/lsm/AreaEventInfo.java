package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * AreaEventInfo ::= SEQUENCE { areaDefinition [0] AreaDefinition, occurrenceInfo [1] OccurrenceInfo OPTIONAL, intervalTime [2]
 * IntervalTime OPTIONAL, ...}
 *
 * @author amit bhayani
 *
 */
public interface AreaEventInfo extends Serializable {

    AreaDefinition getAreaDefinition();

    OccurrenceInfo getOccurrenceInfo();

    /**
     * IntervalTime ::= INTEGER (1..32767) -- minimum interval time between area reports in seconds
     *
     * @return
     */
    Integer getIntervalTime();
}
