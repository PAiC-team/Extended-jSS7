
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 IntraCUG-Options ::= ENUMERATED { noCUG-Restrictions (0), cugIC-CallBarred (1), cugOG-CallBarred (2)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum IntraCUGOptions {
    noCUGRestrictions(0), cugICCallBarred(1), cugOGCallBarred(2);

    private int code;

    private IntraCUGOptions(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static IntraCUGOptions getInstance(int code) {
        switch (code) {
            case 0:
                return IntraCUGOptions.noCUGRestrictions;
            case 1:
                return IntraCUGOptions.cugICCallBarred;
            case 2:
                return IntraCUGOptions.cugOGCallBarred;
            default:
                return null;
        }
    }
}
