
package org.restcomm.protocols.ss7.tools.simulator.level1;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class M3uaExchangeType extends EnumeratedBase {

    private static final long serialVersionUID = -839485980221248964L;
    public static final int VAL_SE = 1;
    public static final int VAL_DE = 2;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_SE, "SE");
        intMap.put(VAL_DE, "DE");

        stringMap.put("SE", VAL_SE);
        stringMap.put("DE", VAL_DE);
    }

    public M3uaExchangeType() {
    }

    public M3uaExchangeType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaExchangeType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaExchangeType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static M3uaExchangeType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return null;
        else
            return new M3uaExchangeType(i1);
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
