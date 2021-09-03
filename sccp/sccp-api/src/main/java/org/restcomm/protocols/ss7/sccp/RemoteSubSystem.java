
package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface RemoteSubSystem {

    boolean isRemoteSsnProhibited();

    int getRemoteSpc();

    int getRemoteSsn();

    int getRemoteSsnFlag();

    boolean getMarkProhibitedWhenSpcResuming();
}
