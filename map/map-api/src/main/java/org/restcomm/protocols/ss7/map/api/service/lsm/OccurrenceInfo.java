package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * OccurrenceInfo ::= ENUMERATED { oneTimeEvent (0), multipleTimeEvent (1), ...}
 *
 * @author abhayani
 *
 */
public enum OccurrenceInfo {

    oneTimeEvent(0), multipleTimeEvent(1);

    private int info;

    private OccurrenceInfo(int info) {
        this.info = info;
    }

    public int getInfo() {
        return this.info;
    }

    public static OccurrenceInfo getOccurrenceInfo(int info) {
        switch (info) {
            case 0:
                return oneTimeEvent;
            case 1:
                return multipleTimeEvent;
            default:
                return null;
        }
    }
}
