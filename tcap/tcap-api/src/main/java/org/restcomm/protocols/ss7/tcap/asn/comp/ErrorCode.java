
package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * @author baranowb
 * @author sergey netyutnev
 *
 */
public interface ErrorCode extends Encodable {

    // it contains valid params for error....

    int _TAG_LOCAL = Tag.INTEGER;
    int _TAG_GLOBAL = Tag.OBJECT_IDENTIFIER;
    int _TAG_CLASS = Tag.CLASS_UNIVERSAL;
    boolean _TAG_PRIMITIVE = true;

    // public void setErrorType(ErrorCodeType t);
    ErrorCodeType getErrorType();

    void setLocalErrorCode(Long localErrorCode);

    void setGlobalErrorCode(long[] globalErrorCode);

    Long getLocalErrorCode();

    long[] getGlobalErrorCode();

}
