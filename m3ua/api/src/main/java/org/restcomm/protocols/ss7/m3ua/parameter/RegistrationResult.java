package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * The Registration Result parameter contains the registration result for a single Routing Key in an REG REQ message.
 *
 * @author amit bhayani
 *
 */
public interface RegistrationResult extends Parameter {

    LocalRKIdentifier getLocalRKIdentifier();

    RegistrationStatus getRegistrationStatus();

    RoutingContext getRoutingContext();

}
