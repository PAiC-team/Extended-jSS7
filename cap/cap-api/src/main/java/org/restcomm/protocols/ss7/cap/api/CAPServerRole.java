
package org.restcomm.protocols.ss7.cap.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum CAPServerRole {

    // GSM Service Switching Function
    gsmSSF(0),
    // GSM Assisting Service Switching Function
    assistingGsmSSF(1),
    // GSM Specialized Resource Function
    gsmSRF(2),
    // Short Message Service Service Switching Function
    smsSSF(3),
    // GPRS Service Switching Function
    gprsSSF(4),
    // GSM Service Control Function
    gsmSCF(10);

    private int code;

    private CAPServerRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
