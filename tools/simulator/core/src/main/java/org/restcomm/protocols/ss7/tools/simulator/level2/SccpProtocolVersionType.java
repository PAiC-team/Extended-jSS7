
package org.restcomm.protocols.ss7.tools.simulator.level2;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
*
* @author sergey vetyutnev
*
*/
public class SccpProtocolVersionType extends EnumeratedBase {

    private static final long serialVersionUID = 4677649208328415370L;
    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(SccpProtocolVersion.ITU.getValue(), SccpProtocolVersion.ITU.toString());
        intMap.put(SccpProtocolVersion.ANSI.getValue(), SccpProtocolVersion.ANSI.toString());

        stringMap.put(SccpProtocolVersion.ITU.toString(), SccpProtocolVersion.ITU.getValue());
        stringMap.put(SccpProtocolVersion.ANSI.toString(), SccpProtocolVersion.ANSI.getValue());
    }

    public SccpProtocolVersionType() {
    }

    public SccpProtocolVersionType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public SccpProtocolVersionType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public SccpProtocolVersionType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static SccpProtocolVersionType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new SccpProtocolVersionType(SccpProtocolVersion.ITU.getValue());
        else
            return new SccpProtocolVersionType(i1);
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
