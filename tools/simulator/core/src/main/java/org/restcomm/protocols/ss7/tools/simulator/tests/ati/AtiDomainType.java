package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
*
* @author sergey vetyutnev
*
*/
public class AtiDomainType extends EnumeratedBase {

    private static final long serialVersionUID = 8544451513960148816L;

    public static final int NO_VALUE = -1;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(NO_VALUE, "No value");
        intMap.put(DomainType.csDomain.getType(), "csDomain");
        intMap.put(DomainType.psDomain.getType(), "psDomain");

        stringMap.put("No value", NO_VALUE);
        stringMap.put("csDomain", DomainType.csDomain.getType());
        stringMap.put("psDomain", DomainType.psDomain.getType());
    }

    public AtiDomainType() {
    }

    public AtiDomainType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public AtiDomainType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public AtiDomainType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static AtiDomainType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new AtiDomainType(DomainType.csDomain.getType());
        else
            return new AtiDomainType(i1);
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
