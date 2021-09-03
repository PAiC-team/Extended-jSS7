
package org.restcomm.protocols.ss7.tcapAnsi.dialog.timeout;

import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.EventTestHarness;
import org.restcomm.protocols.ss7.tcapAnsi.api.TCAPStack;

/**
 * @author baranowb
 *
 */
public class Server extends EventTestHarness {

    /**
     * @param stack
     * @param thisAddress
     * @param remoteAddress
     */
    public Server(final TCAPStack stack, final ParameterFactory parameterFactory, final SccpAddress thisAddress, final SccpAddress remoteAddress) {
        super(stack, parameterFactory,thisAddress, remoteAddress);
    }

}
