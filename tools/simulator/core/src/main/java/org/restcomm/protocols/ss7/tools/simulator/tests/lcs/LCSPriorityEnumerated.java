package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPriority;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LCSPriorityEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(LCSPriority.highestPriority.getCode(), LCSPriority.highestPriority.toString());
        intMap.put(LCSPriority.normalPriority.getCode(), LCSPriority.normalPriority.toString());

        stringMap.put(LCSPriority.highestPriority.toString(), LCSPriority.highestPriority.getCode());
        stringMap.put(LCSPriority.normalPriority.toString(), LCSPriority.normalPriority.getCode());
    }

    public LCSPriorityEnumerated() {
    }

    public LCSPriorityEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LCSPriorityEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LCSPriorityEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static LCSPriorityEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new LCSPriorityEnumerated(LCSPriority.highestPriority.getCode());
        else
            return new LCSPriorityEnumerated(instance);
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
