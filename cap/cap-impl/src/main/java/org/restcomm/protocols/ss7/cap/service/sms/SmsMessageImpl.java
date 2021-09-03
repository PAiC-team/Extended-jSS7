package org.restcomm.protocols.ss7.cap.service.sms;

import org.restcomm.protocols.ss7.cap.MessageImpl;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPDialogSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.SmsMessage;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class SmsMessageImpl extends MessageImpl implements SmsMessage, CAPAsnPrimitive {

    public CAPDialogSms getCAPDialog() {
        return (CAPDialogSms) super.getCAPDialog();
    }

}
