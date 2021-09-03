package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;

import org.mobicents.protocols.asn.Tag;

/**
 * Base interface for operations carried in {@link RemoteOperations}
 * @author baranowb
 *
 */
public interface RemoteOperation extends Serializable{
    //I wonder why this is different then regular ops from TCAP...
    int _COMPONENT_TAG = 0x0C;
    boolean _COMPONENT_TAG_PC_PRIMITIVE = false;
    int _COMPONENT_TAG_CLASS = Tag.CLASS_APPLICATION;

    void setInvokeId(Long i);

    Long getInvokeId();

    OperationType getType();

    public enum OperationType {

        Invoke, ReturnResult, Reject, ReturnError;
    }
}
