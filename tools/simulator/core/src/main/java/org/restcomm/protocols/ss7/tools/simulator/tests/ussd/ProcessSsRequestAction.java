
package org.restcomm.protocols.ss7.tools.simulator.tests.ussd;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ProcessSsRequestAction extends EnumeratedBase {

    private static final long serialVersionUID = -117458553688586736L;
    public static final int VAL_MANUAL_RESPONSE = 1;
    public static final int VAL_AUTO_ProcessUnstructuredSSResponse = 2;
    public static final int VAL_AUTO_Unstructured_SS_Request_Then_ProcessUnstructuredSSResponse = 3;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_MANUAL_RESPONSE, "Manual response");
        intMap.put(VAL_AUTO_ProcessUnstructuredSSResponse, "Auto sending ProcessUnstructuredSSResponse");
        intMap.put(VAL_AUTO_Unstructured_SS_Request_Then_ProcessUnstructuredSSResponse,
                "Auto sending Send Unstructured_SS_Request then after response sending ProcessUnstructuredSSResponse");

        stringMap.put("Manual response", VAL_MANUAL_RESPONSE);
        stringMap.put("Auto sending ProcessUnstructuredSSResponse", VAL_AUTO_ProcessUnstructuredSSResponse);
        stringMap.put("Auto sending Send Unstructured_SS_Request then after response sending ProcessUnstructuredSSResponse",
                VAL_AUTO_Unstructured_SS_Request_Then_ProcessUnstructuredSSResponse);
    }

    public ProcessSsRequestAction() {
    }

    public ProcessSsRequestAction(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public ProcessSsRequestAction(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public ProcessSsRequestAction(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static ProcessSsRequestAction createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new ProcessSsRequestAction(VAL_MANUAL_RESPONSE);
        else
            return new ProcessSsRequestAction(i1);
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
