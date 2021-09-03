package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.map.api.service.lsm.TerminationCause;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TerminationCauseEnumerated extends EnumeratedBase {

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(TerminationCause.normal.getCause(), TerminationCause.normal.toString());
        intMap.put(TerminationCause.errorUndefined.getCause(), TerminationCause.errorUndefined.toString());
        intMap.put(TerminationCause.internalTimeout.getCause(), TerminationCause.internalTimeout.toString());
        intMap.put(TerminationCause.congestion.getCause(), TerminationCause.congestion.toString());
        intMap.put(TerminationCause.mtlrRestart.getCause(), TerminationCause.mtlrRestart.toString());
        intMap.put(TerminationCause.privacyViolation.getCause(), TerminationCause.privacyViolation.toString());

        stringMap.put(TerminationCause.normal.toString(), TerminationCause.normal.getCause());
        stringMap.put(TerminationCause.errorUndefined.toString(), TerminationCause.errorUndefined.getCause());
        stringMap.put(TerminationCause.internalTimeout.toString(), TerminationCause.internalTimeout.getCause());
        stringMap.put(TerminationCause.congestion.toString(), TerminationCause.congestion.getCause());
        stringMap.put(TerminationCause.mtlrRestart.toString(), TerminationCause.mtlrRestart.getCause());
        stringMap.put(TerminationCause.privacyViolation.toString(), TerminationCause.privacyViolation.getCause());
    }

    public TerminationCauseEnumerated() {
    }

    public TerminationCauseEnumerated(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public TerminationCauseEnumerated(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public TerminationCauseEnumerated(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static TerminationCauseEnumerated createInstance(String s) {
        Integer instance = doCreateInstance(s, stringMap, intMap);
        if (instance == null)
            return new TerminationCauseEnumerated(TerminationCause.normal.getCause());
        else
            return new TerminationCauseEnumerated(instance);
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
