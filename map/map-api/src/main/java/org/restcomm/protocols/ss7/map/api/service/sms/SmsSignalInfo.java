package org.restcomm.protocols.ss7.map.api.service.sms;

import java.io.Serializable;
import java.nio.charset.Charset;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.smstpdu.SmsTpdu;

/**
 * Sms signal info
 *
 * @author sergey vetyutnev
 *
 */
public interface SmsSignalInfo extends Serializable {

    byte[] getData();

    Charset getGsm8Charset();

    void setGsm8Charset(Charset gsm8Charset);

    SmsTpdu decodeTpdu(boolean mobileOriginatedMessage) throws MAPException;

}
