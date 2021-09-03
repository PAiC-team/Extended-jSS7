
package org.restcomm.protocols.ss7.isup.impl.message;

import java.io.ByteArrayOutputStream;

import org.restcomm.protocols.ss7.isup.ISUPMessageFactory;
import org.restcomm.protocols.ss7.isup.ISUPParameterFactory;
import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;

/**
 * Base class for implementation of ISUP Messages.
 *
 * @author baranowb
 *
 */
public abstract class AbstractISUPMessage implements ISUPMessage {

    /**
     * Decodes this element from passed byte[] array. This array must contain only element data. however in case of constructor
     * elements it may contain more information elements that consist of tag, length and contents elements, this has to be
     * handled by specific implementation of this method.
     *
     * @param b - array containing body of parameter.
     * @param isupMessageFactory
     * @param parameterFactory - factory which will be used to create specific params.
     * @return
     */
    public abstract int decode(byte[] b, ISUPMessageFactory isupMessageFactory, ISUPParameterFactory parameterFactory) throws ParameterException;

    /**
     * Encodes message as byte[]. See B.4/Q.763 - page 119)
     *
     * @return byte[] with encoded element.
     * @throws ParameterException
     */
    public abstract byte[] encode() throws ParameterException;

    /**
     * Encodes message as byte[]. See B.4/Q.763 - page 119)
     *
     * @return number of bytes encoded
     * @throws ParameterException
     */
    public abstract int encode(ByteArrayOutputStream bos) throws ParameterException;

}
