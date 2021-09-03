package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface ParameterIndicator extends Serializable {

    int getCode();

    boolean getTP_UDLPresence();

    boolean getTP_DCSPresence();

    boolean getTP_PIDPresence();;

}