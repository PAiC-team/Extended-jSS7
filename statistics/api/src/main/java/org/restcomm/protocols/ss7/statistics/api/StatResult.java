
package org.restcomm.protocols.ss7.statistics.api;

import javolution.util.FastMap;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface StatResult {

    long getLongValue();

    FastMap<String, LongValue> getStringLongValue();

}
