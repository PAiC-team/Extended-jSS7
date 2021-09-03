
/**
 *
 */

package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 * @author sergey vetyutnev

OperationCode ::= CHOICE {
    national        [PRIVATE 16] IMPLICIT INTEGER -32768..32767,
    private     [PRIVATE 17] IMPLICIT INTEGER
}

 *
 */
public interface OperationCode extends Encodable {

    int _TAG_NATIONAL = 16;
    int _TAG_PRIVATE = 17;

    OperationCodeType getOperationType();

    void setNationalOperationCode(Long nationalOperationCode);

    void setPrivateOperationCode(Long privateOperationCode);

    Long getNationalOperationCode();

    Long getPrivateOperationCode();

}
