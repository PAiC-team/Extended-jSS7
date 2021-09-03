package org.restcomm.protocols.ss7.isup.message.parameter;

import org.mobicents.protocols.asn.Tag;


/**
 * @author baranowb
 *
 */
public interface Reject extends RemoteOperation {
    int _TAG = 0x04;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    int _TAG_IID = 0x02;
    boolean _TAG_IID_PC_PRIMITIVE = true;
    int _TAG_IID_CLASS = Tag.CLASS_UNIVERSAL;

    Problem getProblem();

    void setProblem(Problem p);
}
