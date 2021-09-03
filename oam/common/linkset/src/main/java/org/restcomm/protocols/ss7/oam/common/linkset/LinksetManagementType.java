
package org.restcomm.protocols.ss7.oam.common.linkset;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanType;

/**
 * @author Lasith Waruna Perera
 *
 */
public enum LinksetManagementType implements MBeanType {
    MANAGEMENT("Management"), FACTORY("Factory");

    private final String name;

    private LinksetManagementType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static LinksetManagementType getInstance(String name) {

        return null;
    }
}
