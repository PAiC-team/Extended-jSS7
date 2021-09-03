
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayInputStream;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.OriginalCalledNumber;

/**
 * Start time:17:30:47 2009-03-29<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class OriginalCalledNumberImpl extends CalledNumberImpl implements OriginalCalledNumber {

    public OriginalCalledNumberImpl(byte[] representation) throws ParameterException {
        super(representation);

    }

    public OriginalCalledNumberImpl(ByteArrayInputStream bis) throws ParameterException {
        super(bis);

    }

    public OriginalCalledNumberImpl(int natureOfAddresIndicator, String address, int numberingPlanIndicator,
            int addressRepresentationRestrictedIndicator) {
        super(natureOfAddresIndicator, address, numberingPlanIndicator, addressRepresentationRestrictedIndicator);

    }

    public OriginalCalledNumberImpl() {
        super();

    }

    protected String getPrimitiveName() {
        return "OriginalCalledNumber";
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }
}
