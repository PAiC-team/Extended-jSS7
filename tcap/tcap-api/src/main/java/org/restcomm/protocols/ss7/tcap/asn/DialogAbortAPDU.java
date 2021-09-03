package org.restcomm.protocols.ss7.tcap.asn;

/**
 * @author baranowb
 *
 */
public interface DialogAbortAPDU extends DialogAPDU {

    // mandatory
    void setAbortSource(AbortSource abortSource);

    AbortSource getAbortSource();

    // opt
    UserInformation getUserInformation();

    void setUserInformation(UserInformation userInformation);
}
