
package org.restcomm.protocols.ss7.oam.common.sctp;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanType;

/**
 * @author Amit Bhayani
 *
 */
public enum SctpManagementType implements MBeanType {
    MANAGEMENT("Management"), SERVER("Server"), ASSOCIATION("Association");

    private final String name;

    public static final String NAME_MANAGEMENT = "Management";
    public static final String NAME_SERVER = "Server";
    public static final String NAME_ASSOCIATION = "Association";

    private SctpManagementType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static SctpManagementType getInstance(String name) {
        if (NAME_MANAGEMENT.equals(name)) {
            return MANAGEMENT;
        } else if (NAME_SERVER.equals(name)) {
            return SERVER;
        } else if (NAME_ASSOCIATION.equals(name)) {
            return ASSOCIATION;
        }

        return null;
    }
}
