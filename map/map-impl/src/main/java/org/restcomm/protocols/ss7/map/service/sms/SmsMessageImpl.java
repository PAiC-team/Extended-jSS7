package org.restcomm.protocols.ss7.map.service.sms;

import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.restcomm.protocols.ss7.map.api.service.sms.SmsMessage;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class SmsMessageImpl extends MessageImpl implements SmsMessage, MAPAsnPrimitive {

    @Override
    public MAPDialogSms getMAPDialog() {
        return (MAPDialogSms) super.getMAPDialog();
    }

}
