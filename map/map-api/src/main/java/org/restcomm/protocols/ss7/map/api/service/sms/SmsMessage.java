package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface SmsMessage extends MAPMessage {

    MAPDialogSms getMAPDialog();

}
