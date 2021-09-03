
package org.restcomm.protocols.ss7.tools.simulator.level1;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
*
* @author sergey vetyutnev
*
*/
public class M3uaTrafficModeType extends EnumeratedBase {

    private static final long serialVersionUID = 2921729783676278118L;
    public static final int VAL_OVERRIDE = 1;
    public static final int VAL_LOADSHARE = 2;
    public static final int VAL_BROADCAST = 3;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_OVERRIDE, "OVERRIDE");
        intMap.put(VAL_LOADSHARE, "LOADSHARE");
        intMap.put(VAL_BROADCAST, "BROADCAST");

        stringMap.put("OVERRIDE", VAL_OVERRIDE);
        stringMap.put("LOADSHARE", VAL_LOADSHARE);
        stringMap.put("BROADCAST", VAL_BROADCAST);
    }

    public M3uaTrafficModeType() {
    }

    public M3uaTrafficModeType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaTrafficModeType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaTrafficModeType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static M3uaTrafficModeType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return null;
        else
            return new M3uaTrafficModeType(i1);
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
