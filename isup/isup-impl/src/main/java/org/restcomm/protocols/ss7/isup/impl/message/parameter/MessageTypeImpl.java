package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageName;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageType;

/**
 * Start time:14:56:41 2009-04-20<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author sergey vetyutnev
 *
 */
public class MessageTypeImpl extends AbstractISUPParameter implements MessageType {

    // we even cant use -1, since it may be avlid value, ech, those binary protocols.
    private MessageName messageName;

//    public MessageTypeImpl(byte[] code) throws ParameterException {
//        super();
//        this.decode(code);
//    }
//
//    public MessageTypeImpl(int code) {
//        super();
//        this.code = code;
//    }

    public MessageTypeImpl(MessageName messageName) {
        super();
        this.messageName = messageName;
    }

    public int decode(byte[] b) throws ParameterException {
        if (b == null || b.length != 1)
            throw new ParameterException();
        return 1;
    }

    public byte[] encode() throws ParameterException {
        return new byte[] { (byte) this.messageName.getCode() };
    }

    public int encode(ByteArrayOutputStream bos) throws ParameterException {
        bos.write(this.messageName.getCode());
        return 1;
    }

    public int getCode() {

        return messageName.getCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    public String toString() {

        return super.toString() + "-" + this.messageName + "-" + this.messageName.getCode();
    }

    @Override
    public MessageName getMessageName() {
        return this.messageName;
    }

}
