
package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum OriginationType {

    LOCAL("LocalOriginated"), REMOTE("RemoteOriginated"), ALL("All");

    private final String type;

    private OriginationType(String type) {
        this.type = type;
    }

    public static OriginationType getInstance(String type) {
        if (LOCAL.getValue().equalsIgnoreCase(type)) {
            return LOCAL;
        } else if (REMOTE.getValue().equalsIgnoreCase(type)) {
            return REMOTE;
        } else if (ALL.getValue().equalsIgnoreCase(type)) {
            return ALL;
        }

        throw new IllegalArgumentException("Wrong value: '"+type+"'");
    }

    public String getValue() {
        return this.type;
    }

}
