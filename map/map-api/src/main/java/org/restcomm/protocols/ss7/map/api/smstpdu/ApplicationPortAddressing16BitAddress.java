
package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 * This facility allows short messages to be routed to one of multiple
 * applications, using a method similar to TCP/UDP ports in a TCP/IP network. An
 * application entity is uniquely identified by the pair of TP-DA/TP-OA and the
 * port address. The port addressing is transparent to the transport, and also
 * useful in Status Reports.
 *
 * @author sergey vetyutnev
 *
 */
public interface ApplicationPortAddressing16BitAddress extends UserDataHeaderElement {

    int getDestinationPort();

    int getOriginatorPort();

}
