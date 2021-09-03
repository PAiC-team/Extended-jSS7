package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * AdditionalNetworkResource ::= ENUMERATED { sgsn (0), ggsn (1), gmlc (2), gsmSCF (3), nplr (4), auc (5), ...} -- if unknown
 * value is received in AdditionalNetworkResource -- it shall be ignored.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AdditionalNetworkResource {

    sgsn(0), ggsn(1), gmlc(2), gsmSCF(3), nplr(4), auc(5);

    private int code;

    private AdditionalNetworkResource(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AdditionalNetworkResource getInstance(int code) {
        switch (code) {
            case 0:
                return sgsn;
            case 1:
                return ggsn;
            case 2:
                return gmlc;
            case 3:
                return gsmSCF;
            case 4:
                return nplr;
            case 5:
                return auc;
            default:
                return null;
        }
    }

}
