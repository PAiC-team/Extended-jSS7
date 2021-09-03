package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.Serializable;

import org.restcomm.protocols.ss7.isup.ParameterException;

/**
 * @author baranowb
 *
 */
public interface Encodable extends Serializable {
    /**
     * Decodes this element from passed byte[] array. This array must contain only element data. however in case of constructor
     * elements it may contain more information elements that consist of tag, length and contents elements, this has to be
     * handled by specific implementation of this method.
     *
     * @param b - array containing body of parameter.
     * @return
     */
    int decode(byte[] b) throws ParameterException;

    /**
     * Encodes elements as byte[].It contains body, tag and length should be added by enclosing element. ( See B.4/Q.763 - page
     * 119)
     *
     * @return byte[] with encoded element.
     * @throws IOException
     */
    byte[] encode() throws ParameterException;
}