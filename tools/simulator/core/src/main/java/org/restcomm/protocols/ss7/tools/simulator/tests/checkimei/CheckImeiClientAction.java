
package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 * @author mnowa
 *
 */
public class CheckImeiClientAction extends EnumeratedBase {

    private static final long serialVersionUID = -6385525152361290635L;
    public static final int VAL_MANUAL_OPERATION = 1;
    public static final int VAL_AUTO_SendCheckImeiRequest = 2;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_MANUAL_OPERATION, "Manual operation");
        intMap.put(VAL_AUTO_SendCheckImeiRequest, "Auto sending CheckImeiRequest");

        stringMap.put("Manual operation", VAL_MANUAL_OPERATION);
        stringMap.put("Auto sending CheckImeiRequest", VAL_AUTO_SendCheckImeiRequest);
    }

    public CheckImeiClientAction() {
    }

    public CheckImeiClientAction(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public CheckImeiClientAction(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public CheckImeiClientAction(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static CheckImeiClientAction createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new CheckImeiClientAction(VAL_MANUAL_OPERATION);
        else
            return new CheckImeiClientAction(i1);
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

