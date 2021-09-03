package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * The routing key is used to associate traffic with the proper application server and ASP.
 *
 * @author amit bhayani
 *
 */
public interface RoutingKey extends Parameter {

    LocalRKIdentifier getLocalRKIdentifier();

    RoutingContext getRoutingContext();

    TrafficModeType getTrafficModeType();

    NetworkAppearance getNetworkAppearance();

    DestinationPointCode[] getDestinationPointCodes();

    ServiceIndicators[] getServiceIndicators();

    OPCList[] getOPCLists();

}
