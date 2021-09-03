
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 GMLC-Restriction ::= ENUMERATED { gmlc-List (0), home-Country (1) , ... } -- exception handling: -- At reception of any other
 * value than the ones listed the receiver shall ignore -- GMLC-Restriction.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum GMLCRestriction {
    gmlcList(0), homeCountry(1);

    private int code;

    private GMLCRestriction(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static GMLCRestriction getInstance(int code) {
        switch (code) {
            case 0:
                return GMLCRestriction.gmlcList;
            case 1:
                return GMLCRestriction.homeCountry;
            default:
                return null;
        }
    }
}
