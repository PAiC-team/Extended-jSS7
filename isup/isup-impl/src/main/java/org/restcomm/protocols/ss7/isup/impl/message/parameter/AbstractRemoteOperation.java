package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.message.parameter.RemoteOperation;

/**
 * @author baranowb
 *
 */
public abstract class AbstractRemoteOperation extends AbstractAsnEncodable implements RemoteOperation{

    private final OperationType operationType;

    public AbstractRemoteOperation(OperationType operationType) {
        super();
        this.operationType = operationType;
    }

    @Override
    public OperationType getType() {
        return this.operationType;
    }
}
