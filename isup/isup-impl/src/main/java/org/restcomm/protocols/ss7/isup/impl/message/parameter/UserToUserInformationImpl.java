package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.UserToUserInformation;

/**
 * Start time:13:13:44 2009-04-04<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class UserToUserInformationImpl extends AbstractISUPParameter implements UserToUserInformation {

    // FIXME: add Q.931
    // FIXME: XXX
    // The format of the user-to-user information parameter field is coded identically to the protocol
    // discriminator plus user information field described in ITU-T Recommendation Q.931.
    // This makes no sense...

    private byte[] information;

    public UserToUserInformationImpl() {
        super();

    }

    public UserToUserInformationImpl(byte[] b) throws ParameterException {
        super();
        decode(b);
    }

    public int decode(byte[] b) throws ParameterException {
        this.information = b;
        return b.length;
    }

    public byte[] encode() throws ParameterException {
        return this.information;
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }

    public byte[] getInformation() {
        return this.information;
    }

    public void setInformation(byte[] b) {
        this.information = b;
    }
}
