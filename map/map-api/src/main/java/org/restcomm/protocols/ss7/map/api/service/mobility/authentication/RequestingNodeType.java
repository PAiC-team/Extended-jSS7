
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

/**
 *
 RequestingNodeType ::= ENUMERATED { vlr (0), sgsn (1), ..., s-cscf (2), bsf (3), gan-aaa-server (4), wlan-aaa-server (5), mme
 * (16), mme-sgsn (17) } -- the values 2, 3, 4 and 5 shall not be used on the MAP-D or Gr interfaces -- exception handling: --
 * received values in the range (6-15) shall be treated as "vlr" -- received values greater than 17 shall be treated as "sgsn"
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RequestingNodeType {

    vlr(0), sgsn(1), sCscf(2), bsf(3), ganAaaServer(4), wlanAaaaServer(5), mme(16), mmeSgsn(17);

    private int code;

    private RequestingNodeType(int code) {
        this.code = code;
    }

    public static RequestingNodeType getInstance(int code) {
        switch (code) {
            case 0:
                return RequestingNodeType.vlr;
            case 1:
                return RequestingNodeType.sgsn;
            case 2:
                return RequestingNodeType.sCscf;
            case 3:
                return RequestingNodeType.bsf;
            case 4:
                return RequestingNodeType.ganAaaServer;
            case 5:
                return RequestingNodeType.wlanAaaaServer;
            case 16:
                return RequestingNodeType.mme;
            case 17:
                return RequestingNodeType.mmeSgsn;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }

}
