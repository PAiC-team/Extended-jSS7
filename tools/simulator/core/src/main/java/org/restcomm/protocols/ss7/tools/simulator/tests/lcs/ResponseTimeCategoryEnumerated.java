package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.map.api.service.lsm.ResponseTimeCategory;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ResponseTimeCategoryEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(ResponseTimeCategory.lowdelay.getCategory(), ResponseTimeCategory.lowdelay.toString());
        intMap.put(ResponseTimeCategory.delaytolerant.getCategory(), ResponseTimeCategory.delaytolerant.toString());

        stringMap.put(ResponseTimeCategory.lowdelay.toString(), ResponseTimeCategory.lowdelay.getCategory());
        stringMap.put(ResponseTimeCategory.delaytolerant.toString(), ResponseTimeCategory.delaytolerant.getCategory());
    }

    public ResponseTimeCategoryEnumerated() {
    }

    public ResponseTimeCategoryEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public ResponseTimeCategoryEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public ResponseTimeCategoryEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static ResponseTimeCategoryEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new ResponseTimeCategoryEnumerated(ResponseTimeCategory.lowdelay.getCategory());
        else
            return new ResponseTimeCategoryEnumerated(instance);
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
