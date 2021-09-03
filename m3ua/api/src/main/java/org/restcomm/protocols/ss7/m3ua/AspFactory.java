
package org.restcomm.protocols.ss7.m3ua;

import java.util.List;

import org.mobicents.protocols.api.Association;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;

/**
 *
 * @author amit bhayani
 *
 */
public interface AspFactory {

    String getName();

    Association getAssociation();

    List<Asp> getAspList();

    boolean getStatus();

    Functionality getFunctionality();

    IPSPType getIpspType();

    ASPIdentifier getAspId();

    boolean isHeartBeatEnabled();
}
