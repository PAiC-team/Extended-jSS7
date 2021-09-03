
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.UserServiceInformation;

/**
 * Start time:12:36:18 2009-04-04<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski</a>
 * @author <a href="mailto:oifa.yulian@gmail.com"> Yulian Oifa</a>
 * @author sergey vetyutnev
 */
public class UserServiceInformationImpl extends UserServiceInformationBaseImpl implements UserServiceInformation {

    public UserServiceInformationImpl() {
        super();

    }

    public UserServiceInformationImpl(byte[] b) throws ParameterException {
        super(b);
    }

    protected String getPrimitiveName() {
        return "UserServiceInformation";
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }
}
