
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

/**
 *
 * @author sergey vetyutnev

Confidentiality ::= SEQUENCE {
    confidentialityId   CHOICE {
        integerConfidentialityId    [0] IMPLICIT INTEGER,
        objectConfidentialityId     [1] IMPLICIT OBJECT IDENTIFIER
    } OPTIONAL,
    ...
    --The extension marker indicates the possible presence of items
    --in the confidentiality set that are used by the confidentiality
    --algorithm.
}

 *
 */
public interface Confidentiality extends Encodable {

    int _TAG_CONFIDENTIALITY = 2;
    int _TAG_INTEGER_CONFIDENTIALITY_ID = 0;
    int _TAG_OBJECT_CONFIDENTIALITY_ID = 1;

    Long getIntegerConfidentialityId();

    void setIntegerConfidentialityId(Long val);

    long[] getObjectConfidentialityId();

    void setObjectConfidentialityId(long[] val);

}
