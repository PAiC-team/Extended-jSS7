
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.CAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface SmsMessage extends CAPMessage {

    CAPDialogSms getCAPDialog();

}