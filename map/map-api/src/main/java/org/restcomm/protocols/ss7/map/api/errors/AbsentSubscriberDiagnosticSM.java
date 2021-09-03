
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 Table 1a: Assignment of values to reasons for absence (values must be in the range of 0 to 255, see 3GPP TS 29.002 [15])
 * Values Reason for absence 0 - no paging response via the MSC 1 - IMSI detached 2 - roaming restriction 3 - deregistered in
 * the HLR for non GPRS 4 - MS purged for non GPRS 5 - no paging response via the SGSN 6 - GPRS detached 7 - deregistered in the
 * HLR for GPRS 8 - MS purged for GPRS 9 - Unidentified subscriber via the MSC 10 - Unidentified subscriber via the SGSN 11 -
 * deregistered in the HSS/HLR for IMS 12 - no response via the IP-SM-GW All 'non GPRS' reasons (except for roaming restriction)
 * can be combined with all 'GPRS' reasons and vice-versa All other integer values are reserved.
 *
 * @author sergey vetyutnev
 *
 */
public enum AbsentSubscriberDiagnosticSM {

    NoPagingResponseViaTheMSC(0), IMSIDetached(1), RoamingRestriction(2), DeregisteredInTheHLRForNonGPRS(3),
    MSPurgedForNonGPRS(4), NoPagingResponseViaTheSGSN(5), GPRSDetached(6), DeregisteredInTheHLRForGPRS(7),
    MSPurgedForGPRS(8), UnidentifiedSubscriberViaTheMSC(9), UnidentifiedSubscriberViaTheSGSN(10),
    DeregisteredInTheHSS_HLRForIMS(11), NoResponseViaTheIP_SM_GW(12);

    private int code;

    private AbsentSubscriberDiagnosticSM(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AbsentSubscriberDiagnosticSM getInstance(int code) {
        switch (code) {
            case 0:
                return NoPagingResponseViaTheMSC;
            case 1:
                return IMSIDetached;
            case 2:
                return RoamingRestriction;
            case 3:
                return DeregisteredInTheHLRForNonGPRS;
            case 4:
                return MSPurgedForNonGPRS;
            case 5:
                return NoPagingResponseViaTheSGSN;
            case 6:
                return GPRSDetached;
            case 7:
                return DeregisteredInTheHLRForGPRS;
            case 8:
                return MSPurgedForGPRS;
            case 9:
                return UnidentifiedSubscriberViaTheMSC;
            case 10:
                return UnidentifiedSubscriberViaTheSGSN;
            case 11:
                return DeregisteredInTheHSS_HLRForIMS;
            case 12:
                return NoResponseViaTheIP_SM_GW;
            default:
                return null;
        }
    }

}
