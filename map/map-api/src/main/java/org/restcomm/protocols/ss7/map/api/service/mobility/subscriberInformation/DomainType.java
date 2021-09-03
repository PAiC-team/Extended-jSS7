package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 *
<code>
DomainType ::= ENUMERATED {
  cs-Domain (0),
  ps-Domain (1),
  ...
}
  -- exception handling:
  -- reception of values > 1 shall be mapped to 'cs-Domain'
</code>
 *
 * @author abhayani
 *
 */
public enum DomainType {

    csDomain(0), psDomain(1);

    private final int type;

    private DomainType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static DomainType getInstance(int type) {
        switch (type) {
            case 0:
                return csDomain;
            case 1:
                return psDomain;
            default:
                return null;
        }
    }

}
