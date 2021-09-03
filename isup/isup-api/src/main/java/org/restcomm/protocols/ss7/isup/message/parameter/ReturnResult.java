
package org.restcomm.protocols.ss7.isup.message.parameter;

import org.mobicents.protocols.asn.Tag;

/**
 * @author baranowb
 *
 */
public interface ReturnResult extends RemoteOperation {


    int _TAG = 0x02;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    int _TAG_IID = 0x02;
    boolean _TAG_IID_PC_PRIMITIVE = true;
    int _TAG_IID_CLASS = Tag.CLASS_UNIVERSAL;

    //FIXME: is this correct?
    int _TAG_SEQ = Tag.SEQUENCE;
    boolean _TAG_SEQ_PC_PRIMITIVE = true;
    int _TAG_SEQ_CLASS = Tag.CLASS_UNIVERSAL;

    // optional
    void setOperationCodes(OperationCode... i);

    OperationCode[] getOperationCodes();

    // optional
    void setParameter(Parameter p);

    Parameter getParameter();
}