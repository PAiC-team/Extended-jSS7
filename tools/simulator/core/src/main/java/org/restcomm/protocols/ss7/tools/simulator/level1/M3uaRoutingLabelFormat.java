
package org.restcomm.protocols.ss7.tools.simulator.level1;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
*
* @author sergey vetyutnev
*
*/
public class M3uaRoutingLabelFormat extends EnumeratedBase {

    private static final long serialVersionUID = 4496910985349923292L;
    public static final int VAL_ITU = 1;
    public static final int VAL_ANSI_Sls8Bit = 2;
    public static final int VAL_ANSI_Sls5Bit = 3;
    public static final int VAL_Japan_TTC_DDI = 4;
    public static final int VAL_China = 5;
    public static final int VAL_Japan_NTT = 6;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_ITU, "ITU");
        intMap.put(VAL_ANSI_Sls8Bit, "ANSI_Sls8Bit");
        intMap.put(VAL_ANSI_Sls5Bit, "ANSI_Sls5Bit");
        intMap.put(VAL_Japan_TTC_DDI, "Japan_TTC_DDI");
        intMap.put(VAL_China, "China");
        intMap.put(VAL_Japan_NTT, "Japan_NTT");

        stringMap.put("ITU", VAL_ITU);
        stringMap.put("ANSI_Sls8Bit", VAL_ANSI_Sls8Bit);
        stringMap.put("ANSI_Sls5Bit", VAL_ANSI_Sls5Bit);
        stringMap.put("Japan_TTC_DDI", VAL_Japan_TTC_DDI);
        stringMap.put("China", VAL_China);
        stringMap.put("Japan_NTT", VAL_Japan_NTT);
    }

    public M3uaRoutingLabelFormat() {
    }

    public M3uaRoutingLabelFormat(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaRoutingLabelFormat(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public M3uaRoutingLabelFormat(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static M3uaRoutingLabelFormat createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return null;
        else
            return new M3uaRoutingLabelFormat(i1);
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
