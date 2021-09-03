
package org.restcomm.protocols.ss7.oam.common.tcap;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class Client extends EventTestHarness {

    public Client(TCAPStack stack, SccpAddress thisAddress, SccpAddress remoteAddress) {
        super(stack, thisAddress, remoteAddress);

    }

}
