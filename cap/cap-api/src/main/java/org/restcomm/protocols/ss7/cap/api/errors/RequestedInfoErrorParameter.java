
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 PARAMETER ENUMERATED { unknownRequestedInfo (1), requestedInfoNotAvailable (2) } CODE errcode-requestedInfoError
 *
 * @author sergey vetyutnev
 *
 */
public enum RequestedInfoErrorParameter {

    unknownRequestedInfo(1), requestedInfoNotAvailable(2);

    private int code;

    private RequestedInfoErrorParameter(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static RequestedInfoErrorParameter getInstance(int code) {
        switch (code) {
            case 1:
                return RequestedInfoErrorParameter.unknownRequestedInfo;
            case 2:
                return RequestedInfoErrorParameter.requestedInfoNotAvailable;
            default:
                return null;
        }
    }
}
