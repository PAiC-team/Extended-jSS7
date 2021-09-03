
package org.restcomm.protocols.ss7.tools.simulator.level3;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MapProtocolVersion extends EnumeratedBase {

    private static final long serialVersionUID = -521918125232920704L;

    public static final int VAL_MAP_V1 = 1;
    public static final int VAL_MAP_V2 = 2;
    public static final int VAL_MAP_V3 = 3;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_MAP_V1, "MAP protocol version 1");
        intMap.put(VAL_MAP_V2, "MAP protocol version 2");
        intMap.put(VAL_MAP_V3, "MAP protocol version 3");

        stringMap.put("MAP protocol version 1", VAL_MAP_V1);
        stringMap.put("MAP protocol version 2", VAL_MAP_V2);
        stringMap.put("MAP protocol version 3", VAL_MAP_V3);
    }

    public MapProtocolVersion() {
    }

    public MapProtocolVersion(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public MapProtocolVersion(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public MapProtocolVersion(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static MapProtocolVersion createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new MapProtocolVersion(VAL_MAP_V3);
        else
            return new MapProtocolVersion(i1);
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
