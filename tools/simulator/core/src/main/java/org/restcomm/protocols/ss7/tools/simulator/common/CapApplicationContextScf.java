
package org.restcomm.protocols.ss7.tools.simulator.common;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CapApplicationContextScf extends EnumeratedBase {

    private static final long serialVersionUID = 363803261830663211L;

    public static final int VAL_CAP_V4_capscf_ssfGeneric = 34;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_CAP_V4_capscf_ssfGeneric, "CAP_V4_capscf_ssfGeneric 23.3.8");

        stringMap.put("CAP_V4_capscf_ssfGeneric 23.3.8", VAL_CAP_V4_capscf_ssfGeneric);
    }

    public CapApplicationContextScf() {
    }

    public CapApplicationContextScf(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public CapApplicationContextScf(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public CapApplicationContextScf(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static CapApplicationContextScf createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new CapApplicationContextScf(VAL_CAP_V4_capscf_ssfGeneric);
        else
            return new CapApplicationContextScf(i1);
    }

    @Override
    protected Hashtable<Integer, String> getIntTable() {
        return intMap;
    }

    @Override
    protected Hashtable<String, Integer> getStringTable() {
        return stringMap;
    }

    public CAPApplicationContext getCAPApplicationContext() {
        switch (this.intValue()) {
            case VAL_CAP_V4_capscf_ssfGeneric:
                return CAPApplicationContext.CapV4_scf_gsmSSFGeneric;
        }
        return CAPApplicationContext.CapV4_scf_gsmSSFGeneric;
    }

}
