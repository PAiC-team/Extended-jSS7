
package org.restcomm.protocols.ss7.m3ua;

import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;

/**
 *
 * @author amit bhayani
 *
 */
public interface Asp {

    String getName();

    boolean isStarted();

    boolean isConnected();

    boolean isUp();

    As getAs();

    AspFactory getAspFactory();

    ASPIdentifier getASPIdentifier();

    State getState();
}
