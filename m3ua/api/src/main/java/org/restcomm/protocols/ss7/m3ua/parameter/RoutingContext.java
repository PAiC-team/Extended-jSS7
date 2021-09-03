package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * The routing context is used for routing of a message and is employed by either the signaling gateway or the ASP when
 * determining to which association the message is to be sent. Think of this as an index to registered routing keys.
 *
 * @author amit bhayani
 * @author kulikov
 */
public interface RoutingContext extends Parameter {

    long[] getRoutingContexts();

}
