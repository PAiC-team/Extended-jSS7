package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.Information;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationType;

/**
 * @author baranowb
 *
 */
public abstract class AbstractInformationImpl implements Information {

    protected final InformationType type;
    protected int tag;
    public AbstractInformationImpl(InformationType type) {
        super();
        this.type = type;
    }

    public InformationType getType(){
        return type;
    }
    public int getTag(){
        return tag;
    }

    void setTag(int tag){
        this.tag = tag;
    }

    abstract byte[] encode() throws ParameterException;

    abstract void decode(byte[] b) throws ParameterException;
}
