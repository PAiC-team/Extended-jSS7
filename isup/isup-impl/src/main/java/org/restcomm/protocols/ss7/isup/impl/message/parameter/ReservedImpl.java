package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.Reserved;

/**
 * Start time:14:30:39 2009-03-31<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class ReservedImpl extends AbstractISUPParameter implements Reserved {
    // FIXME: XXX
    // there is no info, 3.27 in Q.763 is empty... cmon.
    public ReservedImpl() {
        super();

    }

    public int decode(byte[] b) throws ParameterException {
        // TODO Auto-generated method stub
        return 0;
    }

    public byte[] encode() throws ParameterException {
        // TODO Auto-generated method stub
        return null;
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }
}
