
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

/**
 * ForwardingOptions ::= OCTET STRING (SIZE (1)) -- bit 8: notification to forwarding party -- 0 no notification -- 1
 * notification -- bit 7: redirecting presentation -- 0 no presentation -- 1 presentation -- bit 6: notification to calling
 * party -- 0 no notification -- 1 notification -- bit 5: 0 (unused) -- bits 43: forwarding reason -- 00 ms not reachable -- 01
 * ms busy -- 10 no reply -- 11 unconditional when used in a SRI Result, -- or call deflection when used in a RCH Argument --
 * bits 21: 00 (unused)
 *
 * @author cristian veliscu
 *
 */
public interface ForwardingOptions extends Serializable {

    int getData();

    boolean isNotificationToForwardingParty();

    boolean isRedirectingPresentation();

    boolean isNotificationToCallingParty();

    ForwardingReason getForwardingReason();
}
