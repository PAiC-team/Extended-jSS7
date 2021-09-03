package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
*
* @author sergey vetyutnev
*
*/
public class ATIReaction extends EnumeratedBase {

    private static final long serialVersionUID = -4059517194931173303L;

    public static final int VAL_RETURN_SUCCESS = 1;
    public static final int VAL_ERROR_SYSTEM_FAILURE = 3;
    public static final int VAL_DATA_MISSING = 4;
    public static final int VAL_ERROR_UNKNOWN_SUBSCRIBER = 5;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_RETURN_SUCCESS, "Return success");
        intMap.put(VAL_ERROR_SYSTEM_FAILURE, "Return error system failure");
        intMap.put(VAL_DATA_MISSING, "Return error data missing");
        intMap.put(VAL_ERROR_UNKNOWN_SUBSCRIBER, "Return error unknown subscriber");

        stringMap.put("Return success", VAL_RETURN_SUCCESS);
        stringMap.put("Return error system failure", VAL_ERROR_SYSTEM_FAILURE);
        stringMap.put("Return error data missing", VAL_DATA_MISSING);
        stringMap.put("Return error unknown subscriber", VAL_ERROR_UNKNOWN_SUBSCRIBER);
    }

    public ATIReaction() {
    }

    public ATIReaction(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public ATIReaction(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public ATIReaction(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static ATIReaction createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new ATIReaction(VAL_RETURN_SUCCESS);
        else
            return new ATIReaction(i1);
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
