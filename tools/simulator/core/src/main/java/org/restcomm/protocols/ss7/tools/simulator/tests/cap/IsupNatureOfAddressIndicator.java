
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

/**
 *
 * IsupNatureOfAddressIndicator ::= ENUMERATED { subscriberNumber(1),
 * unknown(2), nationalNumber(3), internationalNumber(4),
 * networkSpecificNumber(5), networkRoutingNumberInNationalNumberFormat(6),
 * networkRoutingNumberInNetworkSpecificNumberFormat(7),
 * networkRoutingNumberConcatenatedWithCalledDirectoryNumber(8) }
 *
 * @author mnowa
 *
 */
public enum IsupNatureOfAddressIndicator {
    subscriberNumber(1), unknown(2), nationalNumber(3), internationalNumber(4), networkSpecificNumber(5),
    networkRoutingNumberInNationalNumberFormat(6), networkRoutingNumberInNetworkSpecificNumberFormat(7),
    networkRoutingNumberConcatenatedWithCalledDirectoryNumber(8);

    private int code;

    private IsupNatureOfAddressIndicator(int code) {
        this.code = code;
    }

    public static IsupNatureOfAddressIndicator getInstance(int code) {
        switch (code) {
            case 1:
                return IsupNatureOfAddressIndicator.subscriberNumber;
            case 2:
                return IsupNatureOfAddressIndicator.unknown;
            case 3:
                return IsupNatureOfAddressIndicator.nationalNumber;
            case 4:
                return IsupNatureOfAddressIndicator.internationalNumber;
            case 5:
                return IsupNatureOfAddressIndicator.networkSpecificNumber;
            case 6:
                return IsupNatureOfAddressIndicator.networkRoutingNumberInNationalNumberFormat;
            case 7:
                return IsupNatureOfAddressIndicator.networkRoutingNumberInNetworkSpecificNumberFormat;
            case 8:
                return IsupNatureOfAddressIndicator.networkRoutingNumberConcatenatedWithCalledDirectoryNumber;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
