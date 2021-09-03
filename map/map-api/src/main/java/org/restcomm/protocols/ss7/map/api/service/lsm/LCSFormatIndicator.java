package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * LCS-FormatIndicator ::= ENUMERATED { logicalName (0), e-mailAddress (1), msisdn (2), url (3), sipUrl (4), ... }
 *
 * @author amit bhayani
 *
 */
public enum LCSFormatIndicator {

    logicalName(0), emailAddress(1), msisdn(2), url(3), sipUrl(4);

    private final int indicator;

    private LCSFormatIndicator(int indicator) {
        this.indicator = indicator;
    }

    public int getIndicator() {
        return this.indicator;
    }

    public static LCSFormatIndicator getLCSFormatIndicator(int indicator) {
        switch (indicator) {
            case 0:
                return logicalName;
            case 1:
                return emailAddress;
            case 2:
                return msisdn;
            case 3:
                return url;
            case 4:
                return sipUrl;
            default:
                return null;
        }
    }
}
