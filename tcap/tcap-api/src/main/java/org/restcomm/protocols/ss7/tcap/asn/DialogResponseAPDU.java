package org.restcomm.protocols.ss7.tcap.asn;

/**
 * @author baranowb
 *
 */
public interface DialogResponseAPDU extends DialogAPDU {

    void setDoNotSendProtocolVersion(boolean val);

    ProtocolVersion getProtocolVersion();

    // mandatory
    ApplicationContextName getApplicationContextName();

    void setApplicationContextName(ApplicationContextName applicationContextName);

    Result getResult();

    void setResult(Result acn);

    ResultSourceDiagnostic getResultSourceDiagnostic();

    void setResultSourceDiagnostic(ResultSourceDiagnostic resultSourceDiagnostic);

    // opt
    UserInformation getUserInformation();

    void setUserInformation(UserInformation userInformation);

}
