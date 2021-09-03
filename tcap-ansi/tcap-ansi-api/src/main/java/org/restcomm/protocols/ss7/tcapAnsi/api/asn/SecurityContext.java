
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

/**
*
* @author sergey vetyutnev
*
*
    securityContext     CHOICE {
        integerSecurityId   [0] IMPLICIT INTEGER,
        objectSecurityId    [1] IMPLICIT OBJECT IDENTIFIER
    } OPTIONAL,

*/
public interface SecurityContext extends Encodable {

    int _TAG_SECURITY_CONTEXT_INTEGER = 0;
    int _TAG_SECURITY_CONTEXT_OID = 1;

    Long getIntegerSecurityId();

    void setIntegerSecurityId(Long val);

    long[] getObjectSecurityId();

    void setObjectSecurityId(long[] val);

}
