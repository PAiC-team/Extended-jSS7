
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayInputStream;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectingNumber;

/**
 * Start time:14:54:53 2009-04-02<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author sergey vetyutnev
 */
public class RedirectingNumberImpl extends CalledNumberImpl implements RedirectingNumber {

    public RedirectingNumberImpl(byte[] representation) throws ParameterException {
        super(representation);

    }

    public RedirectingNumberImpl(ByteArrayInputStream bis) throws ParameterException {
        super(bis);

    }

    public RedirectingNumberImpl(int natureOfAddresIndicator, String address, int numberingPlanIndicator,
            int addressRepresentationRestrictedIndicator) {
        super(natureOfAddresIndicator, address, numberingPlanIndicator, addressRepresentationRestrictedIndicator);

    }

    public RedirectingNumberImpl() {
        super();

    }

    protected String getPrimitiveName() {
        return "RedirectingNumber";
    }

    public int getCode() {

        return _PARAMETER_CODE;
    }

    /**
     * <pre>
     * a) Odd/even indicator: as for 3.9 a).
     * b) Nature of address indicator: as for 3.10 b).
     * c) Numbering plan indicator: as for 3.9 d).
     * d) Address presentation restricted indicator: as for 3.10 e).
     * e) Address signal: as for 3.10 g).
     * f) Filler: as for 3.9 f).
     * </pre>
     */
}
