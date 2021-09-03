
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;


/**
*
* Description of a set of counters.
* CounterMediator can supply one ore more CounterDefSet.
*
* @author sergey vetyutnev
*
*/
public interface CounterDefSet extends Serializable {

    /**
     * The name of CounterDefSet
     * @return
     */
    String getName();

    /**
     * Returns a list of CounterDef which are contained in CounterDefSet
     * @return
     */
    CounterDef[] getCounterDefs();

}
