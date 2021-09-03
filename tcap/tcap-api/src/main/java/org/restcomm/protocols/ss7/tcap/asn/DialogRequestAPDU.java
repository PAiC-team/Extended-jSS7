package org.restcomm.protocols.ss7.tcap.asn;

/**
 * @author baranowb
 *
 */
public interface DialogRequestAPDU extends DialogAPDU {

    void setDoNotSendProtocolVersion(boolean val);

    ProtocolVersion getProtocolVersion();

    // mandatory
    ApplicationContextName getApplicationContextName();

    void setApplicationContextName(ApplicationContextName applicationContextName);

    // opt
    UserInformation getUserInformation();

    void setUserInformation(UserInformation userInformation);

    /**
     * Return true if the decoded request contained malformed User Information element
     *
     * @return true if the decoded request contained malformed User Information element
     */
    boolean isMalformedUserInformation();

}
