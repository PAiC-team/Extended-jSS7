package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

import java.util.Hashtable;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
*
* @author sergey vetyutnev
*
*/
public class SubscriberIdentityType extends EnumeratedBase {

    private static final long serialVersionUID = 614923576863244465L;

    public static final int VALUE_IMSI = 1;
    public static final int VALUE_ISDN = 2;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VALUE_IMSI, "IMSI");
        intMap.put(VALUE_ISDN, "ISDN");

        stringMap.put("IMSI", VALUE_IMSI);
        stringMap.put("ISDN", VALUE_ISDN);
    }

    public SubscriberIdentityType() {
    }

    public SubscriberIdentityType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public SubscriberIdentityType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public SubscriberIdentityType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static SubscriberIdentityType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new SubscriberIdentityType(VALUE_IMSI);
        else
            return new SubscriberIdentityType(i1);
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
