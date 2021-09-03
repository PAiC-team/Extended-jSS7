
package org.restcomm.protocols.ss7.map.api;

import java.io.Serializable;


/**
 * This is super interface for all service message in MAP
 *
 * @author amit bhayani
 *
 */
public interface MAPMessage extends Serializable {

    long getInvokeId();

    void setInvokeId(long invokeId);

    MAPDialog getMAPDialog();

    void setMAPDialog(MAPDialog mapDialog);

    MAPMessageType getMessageType();

    int getOperationCode();

    boolean isReturnResultNotLast();

}
