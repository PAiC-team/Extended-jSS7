package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.SubsequentNumber;

/**
 * Start time:17:32:06 2009-04-02<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class SubsequentNumberImpl extends AbstractNumber implements SubsequentNumber {

    public SubsequentNumberImpl() {
        super();

    }

    public SubsequentNumberImpl(byte[] representation) throws ParameterException {
        super(representation);

    }

    public SubsequentNumberImpl(ByteArrayInputStream bis) throws ParameterException {
        super(bis);

    }

    public int decodeBody(ByteArrayInputStream bis) throws IllegalArgumentException {
        // NOTE: we leave this.

        return 0;
    }

    public int encodeBody(ByteArrayOutputStream bos) {
        // NOTE: we leave this.
        return 0;
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }

}
