
package org.restcomm.protocols.ss7.sccp.parameter;

import org.restcomm.protocols.ss7.indicator.GlobalTitleIndicator;

/**
 * @author baranowb
 *
 */
public interface GlobalTitle extends Parameter {

    /**
     * Defines fields included into the global title.
     *
     * @return
     */
    GlobalTitleIndicator getGlobalTitleIndicator();

    /**
     * Address string.
     *
     * @return
     */
    String getDigits();
}
