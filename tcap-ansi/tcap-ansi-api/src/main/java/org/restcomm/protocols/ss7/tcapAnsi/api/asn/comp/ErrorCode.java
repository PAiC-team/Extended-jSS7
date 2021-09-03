
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 * @author sergey netyutnev

ErrorCode ::= CHOICE {
    national        [PRIVATE 19] INTEGER -128..127,
    private     [PRIVATE 20] INTEGER
}
 *
 */
public interface ErrorCode extends Encodable {

    int _TAG_NATIONAL = 19;
    int _TAG_PRIVATE = 20;

    ErrorCodeType getErrorType();

    void setNationalErrorCode(Long nationalErrorCode);

    void setPrivateErrorCode(Long privateErrorCode);

    Long getNationalErrorCode();

    Long getPrivateErrorCode();

}
