
package org.restcomm.protocols.ss7.oam.common.sccp;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanType;

/**
 * @author Amit Bhayani
 *
 */
public enum SccpManagementType implements MBeanType {
    MANAGEMENT("Management"), ROUTER("Router"), RESOURCE("Resource");

    private final String name;

    private SccpManagementType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static SccpManagementType getInstance(String name) {

        return null;
    }
}
