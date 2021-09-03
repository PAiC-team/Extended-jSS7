
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

/**
 *
 UE-SRVCC-Capability::= ENUMERATED { ue-srvcc-not-supported (0), ue-srvcc-supported (1), ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum UESRVCCCapability {

    ueSrvccNotSupported(0), ueSrvccSupported(1);

    private int code;

    private UESRVCCCapability(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static UESRVCCCapability getInstance(int code) {
        switch (code) {
            case 0:
                return UESRVCCCapability.ueSrvccNotSupported;
            case 1:
                return UESRVCCCapability.ueSrvccSupported;
            default:
                return null;
        }
    }
}
