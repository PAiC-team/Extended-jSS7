
package org.restcomm.protocols.ss7.tools.simulator.level1;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class BIpChannelType extends EnumeratedBase {

    private static final long serialVersionUID = 1309893222268779973L;
    public static final int VAL_TCP = 1;
    public static final int VAL_SCTP = 2;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_TCP, "TCP");
        intMap.put(VAL_SCTP, "SCTP");

        stringMap.put("TCP", VAL_TCP);
        stringMap.put("SCTP", VAL_SCTP);
    }

    public BIpChannelType() {
    }

    public BIpChannelType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public BIpChannelType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public BIpChannelType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static BIpChannelType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return null;
        else
            return new BIpChannelType(i1);
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
