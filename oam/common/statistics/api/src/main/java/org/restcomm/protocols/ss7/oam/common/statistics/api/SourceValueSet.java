
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;
import java.util.UUID;


import javolution.util.FastMap;

/**
*
* This value is supplied by CounterMediator and consumed by CounterProvider.
* This is a set of counters results for the whole counterSet (all counters and objects)
*
* @author sergey vetyutnev
*
*/
public interface SourceValueSet extends Serializable {

    /**
     * This value means Id of session of the source why counters were collected.
     * Before counters calculating CounterProvider must compare SessionId of current
     * and previous values. If they are not equal, CounterProvider will not process
     * data.
     *
     * @return
     */
    UUID getSessionId();

    FastMap<String, SourceValueCounter> getCounters();

}
