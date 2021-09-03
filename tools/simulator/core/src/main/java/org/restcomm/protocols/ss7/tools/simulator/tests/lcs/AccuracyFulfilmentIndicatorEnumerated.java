package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class AccuracyFulfilmentIndicatorEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(AccuracyFulfilmentIndicator.requestedAccuracyFulfilled.getIndicator(), AccuracyFulfilmentIndicator.requestedAccuracyFulfilled.toString());
        intMap.put(AccuracyFulfilmentIndicator.requestedAccuracyNotFulfilled.getIndicator(), AccuracyFulfilmentIndicator.requestedAccuracyNotFulfilled.toString());

        stringMap.put(AccuracyFulfilmentIndicator.requestedAccuracyFulfilled.toString(), AccuracyFulfilmentIndicator.requestedAccuracyFulfilled.getIndicator());
        stringMap.put(AccuracyFulfilmentIndicator.requestedAccuracyNotFulfilled.toString(), AccuracyFulfilmentIndicator.requestedAccuracyNotFulfilled.getIndicator());
    }

    public AccuracyFulfilmentIndicatorEnumerated() {
    }

    public AccuracyFulfilmentIndicatorEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public AccuracyFulfilmentIndicatorEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public AccuracyFulfilmentIndicatorEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static AccuracyFulfilmentIndicatorEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new AccuracyFulfilmentIndicatorEnumerated(AccuracyFulfilmentIndicator.requestedAccuracyFulfilled.getIndicator());
        else
            return new AccuracyFulfilmentIndicatorEnumerated(instance);
    }

    @Override
    protected Hashtable<Integer, String> getIntTable() {
        return intMap;
    }

    @Override
    protected Hashtable<String, Integer> getStringTable() {
        return stringMap;
    }


}
