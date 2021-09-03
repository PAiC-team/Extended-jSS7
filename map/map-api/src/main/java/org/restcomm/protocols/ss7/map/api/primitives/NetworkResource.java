package org.restcomm.protocols.ss7.map.api.primitives;

/**
 *
 * NetworkResource ::= ENUMERATED { plmn (0), hlr (1), vlr (2), pvlr (3), controllingMSC (4), vmsc (5), eir (6), rss (7)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum NetworkResource {
    plmn(0), hlr(1), vlr(2), pvlr(3), controllingMSC(4), vmsc(5), eir(6), rss(7);

    private int code;

    private NetworkResource(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static NetworkResource getInstance(int code) {
        switch (code) {
            case 0:
                return plmn;
            case 1:
                return hlr;
            case 2:
                return vlr;
            case 3:
                return pvlr;
            case 4:
                return controllingMSC;
            case 5:
                return vmsc;
            case 6:
                return eir;
            case 7:
                return rss;
            default:
                return null;
        }
    }

}
