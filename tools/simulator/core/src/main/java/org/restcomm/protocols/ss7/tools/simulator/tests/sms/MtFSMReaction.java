
package org.restcomm.protocols.ss7.tools.simulator.tests.sms;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MtFSMReaction extends EnumeratedBase {

    private static final long serialVersionUID = 874420582107689697L;

    public static final int VAL_RETURN_SUCCESS = 1;
    public static final int VAL_ERROR_MEMORY_CAPACITY_EXCEEDED = 2;
    public static final int VAL_ERROR_UNKNOWN_SERVICE_CENTRE = 3;
    public static final int VAL_ERROR_SYSTEM_FAILURE = 4;
    public static final int VAL_ERROR_ABSENT_SUBSCRIBER = 5;
    public static final int VAL_ERROR_SUBSCRIBER_BUSY_FOR_MT_SMS = 6;
    public static final int VAL_ERROR_EQUIPMENT_PROTOCOL_ERROR = 7;
    public static final int VAL_ERROR_EQUIPMENT_PROTOCOL_ERROR_WITH_TPDU = 8;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(VAL_RETURN_SUCCESS, "Return success");
        intMap.put(VAL_ERROR_MEMORY_CAPACITY_EXCEEDED, "SMDeliveryFailure: Return memory capacity exceeded");
        intMap.put(VAL_ERROR_EQUIPMENT_PROTOCOL_ERROR, "SMDeliveryFailure: Equipment protocol error");
        intMap.put(VAL_ERROR_EQUIPMENT_PROTOCOL_ERROR_WITH_TPDU, "SMDeliveryFailure: Equipment protocol error with sms_delivery_report tpdu");
        intMap.put(VAL_ERROR_UNKNOWN_SERVICE_CENTRE, "SMDeliveryFailure: Unknown service centre");
        intMap.put(VAL_ERROR_SYSTEM_FAILURE, "Return error system failure");
        intMap.put(VAL_ERROR_ABSENT_SUBSCRIBER, "Return error absent subscriber");
        intMap.put(VAL_ERROR_SUBSCRIBER_BUSY_FOR_MT_SMS, "Return error subscriber busy for MT SMS");

        stringMap.put("Return success", VAL_RETURN_SUCCESS);
        stringMap.put("SMDeliveryFailure: Return memory capacity exceeded", VAL_ERROR_MEMORY_CAPACITY_EXCEEDED);
        stringMap.put("SMDeliveryFailure: Equipment protocol error", VAL_ERROR_EQUIPMENT_PROTOCOL_ERROR);
        stringMap.put("SMDeliveryFailure: Equipment protocol error with sms_delivery_report tpdu", VAL_ERROR_EQUIPMENT_PROTOCOL_ERROR_WITH_TPDU);
        stringMap.put("SMDeliveryFailure: Unknown service centre", VAL_ERROR_UNKNOWN_SERVICE_CENTRE);
        stringMap.put("Return error system failure", VAL_ERROR_SYSTEM_FAILURE);
        stringMap.put("Return error absent subscriber", VAL_ERROR_ABSENT_SUBSCRIBER);
        stringMap.put("Return error subscriber busy for MT SMS", VAL_ERROR_SUBSCRIBER_BUSY_FOR_MT_SMS);
    }

    public MtFSMReaction() {
    }

    public MtFSMReaction(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public MtFSMReaction(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public MtFSMReaction(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static MtFSMReaction createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new MtFSMReaction(VAL_RETURN_SUCCESS);
        else
            return new MtFSMReaction(i1);
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
