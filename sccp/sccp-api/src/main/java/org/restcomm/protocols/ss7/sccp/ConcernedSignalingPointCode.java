
package org.restcomm.protocols.ss7.sccp;

/**
 * Concerned signaling point codes define a DPC which will be noticed when local SSN is registered (SSA messages) or
 * unregistered (SSP messages)
 *
 * @author amit bhayani
 *
 */
public interface ConcernedSignalingPointCode {

    /**
     * Point code to which SSA or SSP message is to be sent
     *
     * @return
     */
    int getRemoteSpc();
}
