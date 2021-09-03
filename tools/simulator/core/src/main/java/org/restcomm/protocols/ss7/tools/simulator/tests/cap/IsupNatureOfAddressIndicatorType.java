
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

import java.util.Hashtable;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author mnowa
 *
 */
public class IsupNatureOfAddressIndicatorType extends EnumeratedBase {

    private static final long serialVersionUID = 2273666813197137562L;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(IsupNatureOfAddressIndicator.subscriberNumber.getCode(), IsupNatureOfAddressIndicator.subscriberNumber.toString());
        intMap.put(IsupNatureOfAddressIndicator.unknown.getCode(), IsupNatureOfAddressIndicator.unknown.toString());
        intMap.put(IsupNatureOfAddressIndicator.nationalNumber.getCode(), IsupNatureOfAddressIndicator.nationalNumber.toString());
        intMap.put(IsupNatureOfAddressIndicator.internationalNumber.getCode(), IsupNatureOfAddressIndicator.internationalNumber.toString());
        intMap.put(IsupNatureOfAddressIndicator.networkSpecificNumber.getCode(), IsupNatureOfAddressIndicator.networkSpecificNumber.toString());
        intMap.put(IsupNatureOfAddressIndicator.networkRoutingNumberInNationalNumberFormat.getCode(),
                IsupNatureOfAddressIndicator.networkRoutingNumberInNationalNumberFormat.toString());
        intMap.put(IsupNatureOfAddressIndicator.networkRoutingNumberInNetworkSpecificNumberFormat.getCode(),
                IsupNatureOfAddressIndicator.networkRoutingNumberInNetworkSpecificNumberFormat.toString());
        intMap.put(IsupNatureOfAddressIndicator.networkRoutingNumberConcatenatedWithCalledDirectoryNumber.getCode(),
                IsupNatureOfAddressIndicator.networkRoutingNumberConcatenatedWithCalledDirectoryNumber.toString());

        stringMap.put(IsupNatureOfAddressIndicator.subscriberNumber.toString(), IsupNatureOfAddressIndicator.subscriberNumber.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.unknown.toString(), IsupNatureOfAddressIndicator.unknown.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.nationalNumber.toString(), IsupNatureOfAddressIndicator.nationalNumber.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.internationalNumber.toString(), IsupNatureOfAddressIndicator.internationalNumber.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.networkSpecificNumber.toString(), IsupNatureOfAddressIndicator.networkSpecificNumber.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.networkRoutingNumberInNationalNumberFormat.toString(),
                IsupNatureOfAddressIndicator.networkRoutingNumberInNationalNumberFormat.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.networkRoutingNumberInNetworkSpecificNumberFormat.toString(),
                IsupNatureOfAddressIndicator.networkRoutingNumberInNetworkSpecificNumberFormat.getCode());
        stringMap.put(IsupNatureOfAddressIndicator.networkRoutingNumberConcatenatedWithCalledDirectoryNumber.toString(),
                IsupNatureOfAddressIndicator.networkRoutingNumberConcatenatedWithCalledDirectoryNumber.getCode());
    }

    public IsupNatureOfAddressIndicatorType() {
    }

    public IsupNatureOfAddressIndicatorType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public IsupNatureOfAddressIndicatorType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public IsupNatureOfAddressIndicatorType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static IsupNatureOfAddressIndicatorType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new IsupNatureOfAddressIndicatorType(IsupNatureOfAddressIndicator.internationalNumber.getCode());
        else
            return new IsupNatureOfAddressIndicatorType(i1);
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
