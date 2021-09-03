
package org.restcomm.protocols.ss7.sccp.impl;

import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;

/**
 * @author Sergey Vetyutnev
 *
 */
public class SccpRspProxy {

    public static void setRemoteSpcProhibited(RemoteSignalingPointCodeImpl rspc, boolean val) {
        rspc.setRemoteSpcProhibited(val);
    }

    public static void setCurrentRestrictionLevel(RemoteSignalingPointCodeImpl rspc, int val) {
        rspc.setCurrentRestrictionLevel(val);
    }

}
