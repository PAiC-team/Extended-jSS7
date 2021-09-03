
package org.restcomm.protocols.ss7.oam.common.m3ua;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanType;

/**
 * @author amit bhayani
 *
 */
public enum M3UAManagementType implements MBeanType {
    MANAGEMENT("Management"), AS("As"), ASP("Asp"), ASPFACTORY("AspFactory");

    private final String name;

    public static final String NAME_AS = "As";
    public static final String NAME_ASP = "Asp";
    public static final String NAME_ASPFACTORY = "AspFactory";

    private M3UAManagementType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static M3UAManagementType getInstance(String name) {
        if (NAME_AS.equals(name)) {
            return AS;
        } else if (NAME_ASP.equals(name)) {
            return ASP;
        } else if (NAME_ASPFACTORY.equals(name)) {
            return ASPFACTORY;
        }

        return null;
    }

}
