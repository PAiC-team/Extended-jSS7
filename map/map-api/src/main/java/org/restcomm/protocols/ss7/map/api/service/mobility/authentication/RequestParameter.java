
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

/**
 *
 RequestParameter ::= ENUMERATED { requestIMSI (0), requestAuthenticationSet (1), requestSubscriberData (2), requestKi (4)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RequestParameter {

    requestIMSI(0), requestAuthenticationSet(1), requestSubscriberData(2), requestKi(4);

    private int code;

    private RequestParameter(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static RequestParameter getInstance(int code) {
        switch (code) {
            case 0:
                return RequestParameter.requestIMSI;
            case 1:
                return RequestParameter.requestAuthenticationSet;
            case 2:
                return RequestParameter.requestSubscriberData;
            case 4:
                return RequestParameter.requestKi;
            default:
                return null;
        }
    }
}
