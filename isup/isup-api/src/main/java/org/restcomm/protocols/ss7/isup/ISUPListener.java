package org.restcomm.protocols.ss7.isup;

/**
 *
 * @author kulikov
 * @author baranowb
 */
public interface ISUPListener {

    void onEvent(ISUPEvent event);

    void onTimeout(ISUPTimeoutEvent event);

}
