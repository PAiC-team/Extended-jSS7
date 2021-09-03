package org.restcomm.protocols.ss7.tcap.asn;

/**
 * @author baranowb
 *
 */
public interface DialogUniAPDU extends DialogAPDU {

    void setDoNotSendProtocolVersion(boolean val);

    // opt, default is 1(no other defined)
    int getProtocolVersion();

    // mandatory
    ApplicationContextName getApplicationContextName();

    void setApplicationContextName(ApplicationContextName applicationContextName);

    // opt
    UserInformation getUserInformation();

    void setUserInformation(UserInformation userInformation);

}
