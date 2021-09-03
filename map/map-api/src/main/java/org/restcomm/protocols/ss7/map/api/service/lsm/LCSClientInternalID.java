
package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
<code>
LCSClientInternalID ::= ENUMERATED {
  broadcastService (0),
  o-andM-HPLMN (1),
  o-andM-VPLMN (2),
  anonymousLocation (3),
  targetMSsubscribedService (4),
  ...
}
-- for a CAMEL phase 3 PLMN operator client, the value targetMSsubscribedService shall be used
</code>
 *
 * @author amit bhayani
 *
 */
public enum LCSClientInternalID {

    broadcastService(0), oandMHPLMN(1), oandMVPLMN(2), anonymousLocation(3), targetMSsubscribedService(4);

    private final int id;

    private LCSClientInternalID(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static LCSClientInternalID getLCSClientInternalID(int type) {
        switch (type) {
            case 0:
                return broadcastService;
            case 1:
                return oandMHPLMN;
            case 2:
                return oandMVPLMN;
            case 3:
                return anonymousLocation;
            case 4:
                return targetMSsubscribedService;
            default:
                return null;
        }
    }

}
