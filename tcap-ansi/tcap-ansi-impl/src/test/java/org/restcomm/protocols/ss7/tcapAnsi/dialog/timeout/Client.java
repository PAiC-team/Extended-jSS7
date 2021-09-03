
package org.restcomm.protocols.ss7.tcapAnsi.dialog.timeout;

import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.EventTestHarness;
import org.restcomm.protocols.ss7.tcapAnsi.api.TCAPStack;

public class Client extends EventTestHarness {

    /**
     * @param stack
     * @param thisAddress
     * @param remoteAddress
     */
    public Client(final TCAPStack stack, final ParameterFactory parameterFactory, final SccpAddress thisAddress, final SccpAddress remoteAddress) {
        super(stack, parameterFactory,thisAddress, remoteAddress);
    }
}
