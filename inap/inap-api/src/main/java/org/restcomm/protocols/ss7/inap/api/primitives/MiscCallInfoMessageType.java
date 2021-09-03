package org.restcomm.protocols.ss7.inap.api.primitives;

/**
 *
<code>
messageType [0] ENUMERATED {request(0), notification(1)},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MiscCallInfoMessageType {
    request(0), notification(1);

    private int code;

    private MiscCallInfoMessageType(int code) {
        this.code = code;
    }

    public static MiscCallInfoMessageType getInstance(int code) {
        switch (code) {
            case 0:
                return MiscCallInfoMessageType.request;
            case 1:
                return MiscCallInfoMessageType.notification;
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
