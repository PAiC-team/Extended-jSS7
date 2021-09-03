
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.ChargedPartyIdentification;

/**
 *
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class ChargedPartyIdentificationImpl extends AbstractISUPParameter implements ChargedPartyIdentification {

  //FIXME: XXX
    @Override
    public int getCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int decode(byte[] b) throws ParameterException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public byte[] encode() throws ParameterException {
        // TODO Auto-generated method stub
        return null;
    }

//    3.75
//    Charged party identification (national use)
//   The format of the charged party identification parameter is national network specific. The format is
//   similar to the format of the corresponding INAP parameter in the "FurnishChargingInformation"
//   operation (see ITU-T Recommendations Q.1218 [11] and Q.1228 [12]).

}
