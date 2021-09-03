
package org.restcomm.protocols.ss7.sccp;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public enum RuleType {

    SOLITARY("Solitary"), DOMINANT("Dominant"), LOADSHARED("Loadshared"), BROADCAST("Broadcast");

    private final String type;

    private RuleType(String type) {
        this.type = type;
    }

    public static RuleType getInstance(String type) {
        if (SOLITARY.getValue().equalsIgnoreCase(type)) {
            return SOLITARY;
        } else if (DOMINANT.getValue().equalsIgnoreCase(type)) {
            return DOMINANT;
        } else if (LOADSHARED.getValue().equalsIgnoreCase(type)) {
            return LOADSHARED;
        } else if (BROADCAST.getValue().equalsIgnoreCase(type)) {
            return BROADCAST;
        }
        throw new IllegalArgumentException("Wrong value '"+type+"'");
    }

    public String getValue() {
        return this.type;
    }
}
