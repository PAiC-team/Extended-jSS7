package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * @author baranowb
 *
 */
public interface InvokingRedirectReason extends Information {
    void setReason(RedirectReason... reasons);

    RedirectReason[] getReason();
}
