package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;

/**
 * Interface which defines basic contract for elements carried in {@link RedirectBackwardInformation},
 * {@link PivotRoutingBackwardInformation} or {@link PivotRoutingForwardInformation}. For each type of info, there is specific
 * subinterface and implementation.
 *
 * @author baranowb
 */
public interface Information extends Serializable{

    InformationType getType();
    int getTag();

}
