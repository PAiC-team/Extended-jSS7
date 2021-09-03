
package org.restcomm.protocols.ss7.oam.common.tcap;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;

/**
 * @author baranowb
 *
 */
public class Server extends EventTestHarness {

    public Server(TCAPStack stack, SccpAddress thisAddress, SccpAddress remoteAddress) {
        super(stack, thisAddress, remoteAddress);
        // TODO Auto-generated constructor stub
    }

}
