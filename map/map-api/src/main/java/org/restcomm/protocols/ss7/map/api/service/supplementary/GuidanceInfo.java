
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
<code>
GuidanceInfo ::= ENUMERATED { enterPW (0), enterNewPW (1), enterNewPW-Again (2)}
-- How this information is really delivered to the subscriber
-- (display, announcement, ...) is not part of this
-- specification.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum GuidanceInfo {

    enterPW(0), enterNewPW(1), enterNewPWAgain(2);

    private int code;

    private GuidanceInfo(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static GuidanceInfo getInstance(int code) {
        switch (code) {
            case 0:
                return GuidanceInfo.enterPW;
            case 1:
                return GuidanceInfo.enterNewPW;
            case 2:
                return GuidanceInfo.enterNewPWAgain;
            default:
                return null;
        }
    }
}
