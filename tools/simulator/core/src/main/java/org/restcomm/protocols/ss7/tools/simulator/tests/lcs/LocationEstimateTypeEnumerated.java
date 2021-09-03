package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.map.api.service.lsm.LocationEstimateType;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LocationEstimateTypeEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(LocationEstimateType.currentLocation.getType(), LocationEstimateType.currentLocation.toString());
        intMap.put(LocationEstimateType.currentOrLastKnownLocation.getType(), LocationEstimateType.currentOrLastKnownLocation.toString());
        intMap.put(LocationEstimateType.initialLocation.getType(), LocationEstimateType.initialLocation.toString());
        intMap.put(LocationEstimateType.activateDeferredLocation.getType(), LocationEstimateType.activateDeferredLocation.toString());
        intMap.put(LocationEstimateType.cancelDeferredLocation.getType(), LocationEstimateType.cancelDeferredLocation.toString());
        intMap.put(LocationEstimateType.cancelDeferredLocation.getType(), LocationEstimateType.notificationVerificationOnly.toString());

        stringMap.put(LocationEstimateType.currentLocation.toString(), LocationEstimateType.currentLocation.getType());
        stringMap.put(LocationEstimateType.currentOrLastKnownLocation.toString(), LocationEstimateType.currentOrLastKnownLocation.getType());
        stringMap.put(LocationEstimateType.initialLocation.toString(), LocationEstimateType.initialLocation.getType());
        stringMap.put(LocationEstimateType.activateDeferredLocation.toString(), LocationEstimateType.activateDeferredLocation.getType());
        stringMap.put(LocationEstimateType.cancelDeferredLocation.toString(), LocationEstimateType.cancelDeferredLocation.getType());
        stringMap.put(LocationEstimateType.cancelDeferredLocation.toString(), LocationEstimateType.notificationVerificationOnly.getType());
    }

    public LocationEstimateTypeEnumerated() {
    }

    public LocationEstimateTypeEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LocationEstimateTypeEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LocationEstimateTypeEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static LocationEstimateTypeEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new LocationEstimateTypeEnumerated(LocationEstimateType.currentLocation.getType());
        else
            return new LocationEstimateTypeEnumerated(instance);
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
