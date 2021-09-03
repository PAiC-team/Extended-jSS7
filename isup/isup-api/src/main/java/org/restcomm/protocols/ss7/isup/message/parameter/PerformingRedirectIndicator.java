package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * @author baranowb
 *
 */
public interface PerformingRedirectIndicator extends Information {
    void setReason(RedirectReason... reasons);

    RedirectReason[] getReason();
}
