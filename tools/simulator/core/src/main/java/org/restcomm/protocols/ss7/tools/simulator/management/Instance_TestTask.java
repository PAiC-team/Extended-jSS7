
package org.restcomm.protocols.ss7.tools.simulator.management;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Instance_TestTask extends EnumeratedBase {

    private static final long serialVersionUID = 5744729092059179670L;
    public static final int VAL_NO = 0;
    public static final int VAL_USSD_TEST_CLIENT = 1;
    public static final int VAL_USSD_TEST_SERVER = 2;
    public static final int VAL_SMS_TEST_CLIENT = 3;
    public static final int VAL_SMS_TEST_SERVER = 4;
    public static final int VAL_CAP_TEST_SCF = 5;
    public static final int VAL_CAP_TEST_SSF = 6;
    public static final int VAL_ATI_TEST_CLIENT = 7;
    public static final int VAL_ATI_TEST_SERVER = 8;
    public static final int VAL_CHECK_IMEI_TEST_CLIENT = 9;
    public static final int VAL_CHECK_IMEI_TEST_SERVER = 10;
    public static final int VAL_MAP_LCS_TEST_CLIENT = 11;
    public static final int VAL_MAP_LCS_TEST_SERVER = 12;
    public static final int VAL_PSI_TEST_SERVER = 13;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_NO, "NO");
        intMap.put(VAL_USSD_TEST_CLIENT, "USSD_TEST_CLIENT");
        intMap.put(VAL_USSD_TEST_SERVER, "USSD_TEST_SERVER");
        intMap.put(VAL_SMS_TEST_CLIENT, "SMS_TEST_CLIENT");
        intMap.put(VAL_SMS_TEST_SERVER, "SMS_TEST_SERVER");
        intMap.put(VAL_CAP_TEST_SCF, "CAP_TEST_SCF");
        intMap.put(VAL_CAP_TEST_SSF, "CAP_TEST_SSF");
        intMap.put(VAL_ATI_TEST_CLIENT, "ATI_TEST_CLIENT");
        intMap.put(VAL_ATI_TEST_SERVER, "ATI_TEST_SERVER");
        intMap.put(VAL_CHECK_IMEI_TEST_CLIENT, "CHECK_IMEI_TEST_CLIENT");
        intMap.put(VAL_CHECK_IMEI_TEST_SERVER, "CHECK_IMEI_TEST_SERVER");
        intMap.put(VAL_MAP_LCS_TEST_CLIENT, "MAP_LCS_TEST_CLIENT");
        intMap.put(VAL_MAP_LCS_TEST_SERVER, "MAP_LCS_TEST_SERVER");
        intMap.put(VAL_PSI_TEST_SERVER, "MAP_PSI_TEST_SERVER");

        stringMap.put("NO", VAL_NO);
        stringMap.put("USSD_TEST_CLIENT", VAL_USSD_TEST_CLIENT);
        stringMap.put("USSD_TEST_SERVER", VAL_USSD_TEST_SERVER);
        stringMap.put("SMS_TEST_CLIENT", VAL_SMS_TEST_CLIENT);
        stringMap.put("SMS_TEST_SERVER", VAL_SMS_TEST_SERVER);
        stringMap.put("CAP_TEST_SCF", VAL_CAP_TEST_SCF);
        stringMap.put("CAP_TEST_SSF", VAL_CAP_TEST_SSF);
        stringMap.put("ATI_TEST_CLIENT", VAL_ATI_TEST_CLIENT);
        stringMap.put("ATI_TEST_SERVER", VAL_ATI_TEST_SERVER);
        stringMap.put("CHECK_IMEI_TEST_CLIENT", VAL_CHECK_IMEI_TEST_CLIENT);
        stringMap.put("CHECK_IMEI_TEST_SERVER", VAL_CHECK_IMEI_TEST_SERVER);
        stringMap.put("MAP_LCS_TEST_CLIENT",VAL_MAP_LCS_TEST_CLIENT);
        stringMap.put("MAP_LCS_TEST_SERVER",VAL_MAP_LCS_TEST_SERVER);
        stringMap.put("MAP_PSI_TEST_SERVER",VAL_PSI_TEST_SERVER);
    }

    public Instance_TestTask() {
    }

    public Instance_TestTask(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public Instance_TestTask(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public Instance_TestTask(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static Instance_TestTask createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new Instance_TestTask(VAL_NO);
        else
            return new Instance_TestTask(i1);
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
