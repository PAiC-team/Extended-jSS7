
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;


import javolution.util.FastMap;

/**
* This value is supplied by CounterMediator and consumed by CounterProvider.
* This is a set of counters results for one counter (all objects)
*
* @author sergey vetyutnev
*
*/
public interface SourceValueCounter extends Serializable {

    CounterDef getCounterDef();

    FastMap<String, SourceValueObject> getObjects();

}
