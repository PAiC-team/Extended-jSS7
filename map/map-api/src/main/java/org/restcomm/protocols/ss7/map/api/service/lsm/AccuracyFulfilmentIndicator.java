package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * AccuracyFulfilmentIndicator ::= ENUMERATED { requestedAccuracyFulfilled (0), requestedAccuracyNotFulfilled (1), ... }
 *
 * @author amit bhayani
 *
 */
public enum AccuracyFulfilmentIndicator {

    requestedAccuracyFulfilled(0), requestedAccuracyNotFulfilled(1);

    private int indicator;

    private AccuracyFulfilmentIndicator(int indicator) {
        this.indicator = indicator;
    }

    public int getIndicator() {
        return this.indicator;
    }

    public static AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator(int indicator) {
        switch (indicator) {
            case 0:
                return requestedAccuracyFulfilled;
            case 1:
                return requestedAccuracyNotFulfilled;
            default:
                return null;
        }
    }
}
