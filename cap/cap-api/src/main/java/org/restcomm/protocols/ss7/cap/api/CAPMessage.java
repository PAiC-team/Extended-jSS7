
package org.restcomm.protocols.ss7.cap.api;

import java.io.Serializable;


/**
 * This is super interface for all service message in CAP
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface CAPMessage extends Serializable {

     long getInvokeId();

     void setInvokeId(long invokeId);

     CAPDialog getCAPDialog();

     void setCAPDialog(CAPDialog capDialog);

     CAPMessageType getMessageType();

     int getOperationCode();

}
