package org.restcomm.protocols.ss7.isup.message.parameter;

import org.mobicents.protocols.asn.Tag;
/**
 * @author baranowb
 *
 */
public interface ReturnError extends RemoteOperation {
    int _TAG = 0x03;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    int _TAG_IID = 0x02;
    boolean _TAG_IID_PC_PRIMITIVE = true;
    int _TAG_IID_CLASS = Tag.CLASS_UNIVERSAL;

    int _TAG_EC = 0x02;
    boolean _TAG_EC_PC_PRIMITIVE = true;
    int _TAG_EC_CLASS = Tag.CLASS_UNIVERSAL;

    void setErrorCode(ErrorCode ec);

    ErrorCode getErrorCode();

    // paramter, optional
    void setParameter(Parameter p);

    Parameter getParameter();
}
