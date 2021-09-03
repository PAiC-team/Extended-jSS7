package org.restcomm.protocols.ss7.mtp;

public interface Mtp2Listener {

    void linkFailed();

    void linkInService();

    void linkUp();

}
