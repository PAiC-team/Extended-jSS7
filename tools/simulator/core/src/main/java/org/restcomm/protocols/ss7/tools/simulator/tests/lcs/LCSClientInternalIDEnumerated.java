package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientInternalID;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LCSClientInternalIDEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(LCSClientInternalID.broadcastService.getId(), LCSClientInternalID.broadcastService.toString());
        intMap.put(LCSClientInternalID.oandMHPLMN.getId(), LCSClientInternalID.oandMHPLMN.toString());
        intMap.put(LCSClientInternalID.oandMVPLMN.getId(), LCSClientInternalID.oandMVPLMN.toString());
        intMap.put(LCSClientInternalID.anonymousLocation.getId(), LCSClientInternalID.anonymousLocation.toString());
        intMap.put(LCSClientInternalID.targetMSsubscribedService.getId(), LCSClientInternalID.targetMSsubscribedService.toString());

        stringMap.put(LCSClientInternalID.broadcastService.toString(), LCSClientInternalID.broadcastService.getId());
        stringMap.put(LCSClientInternalID.oandMHPLMN.toString(), LCSClientInternalID.oandMHPLMN.getId());
        stringMap.put(LCSClientInternalID.oandMVPLMN.toString(), LCSClientInternalID.oandMVPLMN.getId());
        stringMap.put(LCSClientInternalID.anonymousLocation.toString(), LCSClientInternalID.anonymousLocation.getId());
        stringMap.put(LCSClientInternalID.targetMSsubscribedService.toString(), LCSClientInternalID.targetMSsubscribedService.getId());
    }

    public LCSClientInternalIDEnumerated() {
    }

    public LCSClientInternalIDEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LCSClientInternalIDEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public LCSClientInternalIDEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static LCSClientInternalIDEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new LCSClientInternalIDEnumerated(LCSClientInternalID.broadcastService.getId());
        else
            return new LCSClientInternalIDEnumerated(instance);
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
