package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 * SMS-DELIVER-REPORT pdu
 *
 * @author sergey vetyutnev
 *
 */
public interface SmsDeliverReportTpdu extends SmsTpdu {

    /**
     * @return TP-UDHI field
     */
    boolean getUserDataHeaderIndicator();

    /**
     * @return TP-FCS field Returns null if no failure occurred and the result is ACK
     */
    FailureCause getFailureCause();

    /**
     * @return TP-PI field
     */
    ParameterIndicator getParameterIndicator();

    /**
     * @return TP-PID field
     */
    ProtocolIdentifier getProtocolIdentifier();

    /**
     * @return TP-DCS field
     */
    DataCodingScheme getDataCodingScheme();

    /**
     * @return TP-UDL field
     */
    int getUserDataLength();

    /**
     * @return TP-UD field
     */
    UserData getUserData();

}