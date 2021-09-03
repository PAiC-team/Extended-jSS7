package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * AreaType ::= ENUMERATED { countryCode (0), plmnId (1), locationAreaId (2), routingAreaId (3), cellGlobalId (4), ... ,
 * utranCellId (5) }
 *
 * @author amit bhayani
 *
 */
public enum AreaType {

    countryCode(0), plmnId(1), locationAreaId(2), routingAreaId(3), cellGlobalId(4), utranCellId(5);

    private final int type;

    private AreaType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static AreaType getAreaType(int type) {
        switch (type) {
            case 0:
                return countryCode;
            case 1:
                return plmnId;
            case 2:
                return locationAreaId;
            case 3:
                return routingAreaId;
            case 4:
                return cellGlobalId;
            case 5:
                return utranCellId;
            default:
                return null;
        }
    }
}
