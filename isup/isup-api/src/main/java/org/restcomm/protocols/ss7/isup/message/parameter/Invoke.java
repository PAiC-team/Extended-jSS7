package org.restcomm.protocols.ss7.isup.message.parameter;

import org.mobicents.protocols.asn.Tag;

/**
 * @author baranowb
 *
 */
public interface Invoke extends RemoteOperation {
    int _TAG = 0x01;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    int _TAG_IID = 0x02;
    boolean _TAG_IID_PC_PRIMITIVE = true;
    int _TAG_IID_CLASS = Tag.CLASS_UNIVERSAL;

    int _TAG_LID = 0x00;
    boolean _TAG_LID_PC_PRIMITIVE = true;
    int _TAG_LID_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    // optional
    void setLinkedId(Long i);

    Long getLinkedId();

    // mandatory
    void setOperationCode(OperationCode i);

    OperationCode getOperationCode();

    // optional
    void setParameter(Parameter p);

    Parameter getParameter();

}
