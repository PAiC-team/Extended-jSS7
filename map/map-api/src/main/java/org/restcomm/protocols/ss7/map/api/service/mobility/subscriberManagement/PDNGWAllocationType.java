
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 PDN-GW-AllocationType ::= ENUMERATED { static (0), dynamic (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum PDNGWAllocationType {
    _static(0), _dynamic(1);

    private int code;

    private PDNGWAllocationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PDNGWAllocationType getInstance(int code) {
        switch (code) {
            case 0:
                return PDNGWAllocationType._static;
            case 1:
                return PDNGWAllocationType._dynamic;
            default:
                return null;
        }
    }
}
