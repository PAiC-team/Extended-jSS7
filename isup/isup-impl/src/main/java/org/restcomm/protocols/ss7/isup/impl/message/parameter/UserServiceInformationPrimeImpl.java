
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.UserServiceInformationPrime;

/**
 * Start time:12:36:18 2009-04-04<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author sergey vetyutnev
 */
public class UserServiceInformationPrimeImpl extends UserServiceInformationBaseImpl implements UserServiceInformationPrime {

    public UserServiceInformationPrimeImpl() {
        super();

    }

    public UserServiceInformationPrimeImpl(byte[] b) throws ParameterException {
        super(b);
    }

    protected String getPrimitiveName() {
        return "UserServiceInformationPrime";
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }

}
