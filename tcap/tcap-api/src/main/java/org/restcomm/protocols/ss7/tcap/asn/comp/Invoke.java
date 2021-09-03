
package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;

/**
 * @author baranowb
 * @author amit bhayani
 *
 */
public interface Invoke extends Component {
    // FIXME: add dialog field!
    // this is sequence
    int _TAG = 0x01;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    int _TAG_IID = 0x02;
    boolean _TAG_IID_PC_PRIMITIVE = true;
    int _TAG_IID_CLASS = Tag.CLASS_UNIVERSAL;

    int _TAG_LID = 0x00;
    boolean _TAG_LID_PC_PRIMITIVE = true;
    int _TAG_LID_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;

    // local, relevant only for send
    InvokeClass getInvokeClass();

    // /**
    // * @return the invokeTimeout
    // */
    // long getInvokeTimeout();
    //
    // /**
    // * Sets timeout for this invoke operation in miliseconds. If no indication
    // * on operation status is received, before this value passes, operation
    // * timesout.
    // *
    // * @param invokeTimeout
    // * the invokeTimeout to set
    // */
    // void setInvokeTimeout(long invokeTimeout);

    // optional
    void setLinkedId(Long i);

    Long getLinkedId();

    Invoke getLinkedInvoke();

    void setLinkedInvoke(Invoke val);

    // mandatory
    void setOperationCode(OperationCode i);

    OperationCode getOperationCode();

    // optional
    void setParameter(Parameter p);

    Parameter getParameter();

    /**
     * @return the current invokeTimeout value
     */
    long getTimeout();

    /**
     * Setting the Invoke timeout in milliseconds Must be invoked before sendComponent() invoking
     *
     * @param invokeTimeout
     */
    void setTimeout(long invokeTimeout);
}
