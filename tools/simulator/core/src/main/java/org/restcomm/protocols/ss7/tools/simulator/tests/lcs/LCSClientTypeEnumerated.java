package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientType;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LCSClientTypeEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(LCSClientType.emergencyServices.getType(), LCSClientType.emergencyServices.toString());
        intMap.put(LCSClientType.valueAddedServices.getType(), LCSClientType.valueAddedServices.toString());
        intMap.put(LCSClientType.plmnOperatorServices.getType(), LCSClientType.plmnOperatorServices.toString());
        intMap.put(LCSClientType.lawfulInterceptServices.getType(), LCSClientType.lawfulInterceptServices.toString());

        stringMap.put(LCSClientType.emergencyServices.toString(), LCSClientType.emergencyServices.getType());
        stringMap.put(LCSClientType.valueAddedServices.toString(), LCSClientType.valueAddedServices.getType());
        stringMap.put(LCSClientType.plmnOperatorServices.toString(), LCSClientType.plmnOperatorServices.getType());
        stringMap.put(LCSClientType.lawfulInterceptServices.toString(), LCSClientType.lawfulInterceptServices.getType());
    }

    public LCSClientTypeEnumerated() {
    }

    public LCSClientTypeEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LCSClientTypeEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LCSClientTypeEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static LCSClientTypeEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new LCSClientTypeEnumerated(LCSClientType.emergencyServices.getType());
        else
            return new LCSClientTypeEnumerated(instance);
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
