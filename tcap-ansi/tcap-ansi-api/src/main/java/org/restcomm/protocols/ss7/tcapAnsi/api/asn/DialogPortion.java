
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

/**
 *
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *

DialoguePortion ::= [PRIVATE 25] IMPLICIT SEQUENCE {
    version         ProtocolVersion OPTIONAL,
    ApplicationContext  CHOICE {
        integerApplicationId    IntegerApplicationContext,
        objectApplicationId ObjectIDApplicationContext
    } OPTIONAL,
    userInformation     UserInformation OPTIONAL,
    securityContext     CHOICE {
        integerSecurityId   [0] IMPLICIT INTEGER,
        objectSecurityId    [1] IMPLICIT OBJECT IDENTIFIER
    } OPTIONAL,
    confidentiality     [2] IMPLICIT Confidentiality OPTIONAL
}

 */
public interface DialogPortion extends Encodable {

    int _TAG_DIALOG_PORTION = 25;


    ProtocolVersion getProtocolVersion();

    void setProtocolVersion(ProtocolVersion val);

    ApplicationContext getApplicationContext();

    void setApplicationContext(ApplicationContext val);

    UserInformation getUserInformation();

    void setUserInformation(UserInformation val);

    SecurityContext getSecurityContext();

    void setSecurityContext(SecurityContext val);

    Confidentiality getConfidentiality();

    void setConfidentiality(Confidentiality val);

}
