
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.CAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface GprsMessage extends CAPMessage {

    CAPDialogGprs getCAPDialog();

}