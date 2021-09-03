package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 * A Super class for SMS-DELIVER, SMS-DELIVER-REPORT, SMS-STATUS-REPORT, SMS-COMMAND, SMS-SUBMIT, SMS-SUBMIT-REPORT pdu's
 *
 * @author sergey vetyutnev
 *
 */
public interface SmsTpdu extends Serializable {

    SmsTpduType getSmsTpduType();

    byte[] encodeData() throws MAPException;

}