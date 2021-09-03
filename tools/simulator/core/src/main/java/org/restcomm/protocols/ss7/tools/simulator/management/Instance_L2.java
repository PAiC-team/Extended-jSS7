
package org.restcomm.protocols.ss7.tools.simulator.management;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Instance_L2 extends EnumeratedBase {

    private static final long serialVersionUID = 8126538008993686969L;
    public static final int VAL_NO = 0;
    public static final int VAL_SCCP = 1;
    public static final int VAL_ISUP = 2;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_NO, "NO");
        intMap.put(VAL_SCCP, "SCCP");
        intMap.put(VAL_ISUP, "ISUP");

        stringMap.put("NO", VAL_NO);
        stringMap.put("SCCP", VAL_SCCP);
        stringMap.put("ISUP", VAL_ISUP);
    }

    public Instance_L2() {
    }

    public Instance_L2(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public Instance_L2(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public Instance_L2(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static Instance_L2 createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new Instance_L2(VAL_NO);
        else
            return new Instance_L2(i1);
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
