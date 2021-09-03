
package org.restcomm.protocols.ss7.oam.common.jmxss7;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanLayer;

/**
 *
 * @author amit bhayani
 *
 */
public enum Ss7Layer implements MBeanLayer {
    SCTP("SCTP"), M3UA("M3UA"), SCCP("SCCP"), TCAP("TCAP"), LINKSET("LINKSET"), SMSC_GW("SMSC_GW"), USSD_GW("USSD_GW"), CAMEL_GW(
            "CAMEL_GW");

    private final String name;

    public static final String NAME_SCTP = "SCTP";
    public static final String NAME_M3UA = "M3UA";
    public static final String NAME_SCCP = "SCCP";
    public static final String NAME_TCAP = "TCAP";
    public static final String NAME_LINKSET = "LINKSET";

    public static final String NAME_SMSC_GW = "SMSC_GW";
    public static final String NAME_USSD_GW = "USSD_GW";
    public static final String NAME_CAMEL_GW = "CAMEL_GW";

    private Ss7Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static MBeanLayer getInstance(String name) {
        if (NAME_SCTP.equals(name)) {
            return SCTP;
        } else if (NAME_M3UA.equals(name)) {
            return M3UA;
        } else if (NAME_SCCP.equals(name)) {
            return SCCP;
        } else if (NAME_TCAP.equals(name)) {
            return TCAP;
        } else if (NAME_LINKSET.equals(name)) {
            return LINKSET;
        } else if (NAME_SMSC_GW.equals(name)) {
            return SMSC_GW;
        } else if (NAME_USSD_GW.equals(name)) {
            return USSD_GW;
        } else if (NAME_CAMEL_GW.equals(name)) {
            return CAMEL_GW;
        }

        return null;
    }
}
