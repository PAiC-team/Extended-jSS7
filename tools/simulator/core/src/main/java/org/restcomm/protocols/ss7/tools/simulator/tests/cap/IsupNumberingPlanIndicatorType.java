
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author mnowa
 *
 */
public class IsupNumberingPlanIndicatorType extends EnumeratedBase {

    private static final long serialVersionUID = 5573666813197137562L;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(IsupNumberingPlanIndicator.spare_0.getCode(), IsupNumberingPlanIndicator.spare_0.toString());
        intMap.put(IsupNumberingPlanIndicator.ISDN.getCode(), IsupNumberingPlanIndicator.ISDN.toString());
        intMap.put(IsupNumberingPlanIndicator.spare_2.getCode(), IsupNumberingPlanIndicator.spare_2.toString());
        intMap.put(IsupNumberingPlanIndicator.data.getCode(), IsupNumberingPlanIndicator.data.toString());
        intMap.put(IsupNumberingPlanIndicator.telex.getCode(), IsupNumberingPlanIndicator.telex.toString());
        intMap.put(IsupNumberingPlanIndicator.reservedForNationalUse_5.getCode(), IsupNumberingPlanIndicator.reservedForNationalUse_5.toString());
        intMap.put(IsupNumberingPlanIndicator.reservedForNationalUse_6.getCode(), IsupNumberingPlanIndicator.reservedForNationalUse_6.toString());
        intMap.put(IsupNumberingPlanIndicator.spare_7.getCode(), IsupNumberingPlanIndicator.spare_7.toString());


        stringMap.put(IsupNumberingPlanIndicator.spare_0.toString(), IsupNumberingPlanIndicator.spare_0.getCode());
        stringMap.put(IsupNumberingPlanIndicator.ISDN.toString(), IsupNumberingPlanIndicator.ISDN.getCode());
        stringMap.put(IsupNumberingPlanIndicator.spare_2.toString(), IsupNumberingPlanIndicator.spare_2.getCode());
        stringMap.put(IsupNumberingPlanIndicator.data.toString(), IsupNumberingPlanIndicator.data.getCode());
        stringMap.put(IsupNumberingPlanIndicator.telex.toString(), IsupNumberingPlanIndicator.telex.getCode());
        stringMap.put(IsupNumberingPlanIndicator.reservedForNationalUse_5.toString(), IsupNumberingPlanIndicator.reservedForNationalUse_5.getCode());
        stringMap.put(IsupNumberingPlanIndicator.reservedForNationalUse_6.toString(), IsupNumberingPlanIndicator.reservedForNationalUse_6.getCode());
        stringMap.put(IsupNumberingPlanIndicator.spare_7.toString(), IsupNumberingPlanIndicator.spare_7.getCode());

    }

    public IsupNumberingPlanIndicatorType() {
    }

    public IsupNumberingPlanIndicatorType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public IsupNumberingPlanIndicatorType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public IsupNumberingPlanIndicatorType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static IsupNumberingPlanIndicatorType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new IsupNumberingPlanIndicatorType(EquipmentStatus.whiteListed.getCode());
        else
            return new IsupNumberingPlanIndicatorType(i1);
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
