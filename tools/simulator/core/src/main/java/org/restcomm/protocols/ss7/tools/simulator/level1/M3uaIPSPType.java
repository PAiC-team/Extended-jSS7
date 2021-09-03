
package org.restcomm.protocols.ss7.tools.simulator.level1;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class M3uaIPSPType extends EnumeratedBase {

    private static final long serialVersionUID = 4496910985349923292L;
    public static final int VAL_CLIENT = 1;
    public static final int VAL_SERVER = 2;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_CLIENT, "CLIENT");
        intMap.put(VAL_SERVER, "SERVER");

        stringMap.put("CLIENT", VAL_CLIENT);
        stringMap.put("SERVER", VAL_SERVER);
    }

    public M3uaIPSPType() {
    }

    public M3uaIPSPType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaIPSPType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaIPSPType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static M3uaIPSPType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return null;
        else
            return new M3uaIPSPType(i1);
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
