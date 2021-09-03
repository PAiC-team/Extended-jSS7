package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 * SMS-COMMAND pdu
 *
 * @author sergey vetyutnev
 *
 */
public interface SmsCommandTpdu extends SmsTpdu {

    /**
     * @return TP-UDHI field
     */
    boolean getUserDataHeaderIndicator();

    /**
     * @return TP-SRR field
     */
    boolean getStatusReportRequest();

    /**
     * @return TP-MR field
     */
    int getMessageReference();

    /**
     * @return TP-PID field
     */
    ProtocolIdentifier getProtocolIdentifier();

    /**
     * @return TP-CT field
     */
    CommandType getCommandType();

    /**
     * @return TP-MN field
     */
    int getMessageNumber();

    /**
     * @return TP-DA field
     */
    AddressField getDestinationAddress();

    /**
     * @return TP-CDL field
     */
    int getCommandDataLength();

    /**
     * @return TP-CD field
     */
    CommandData getCommandData();

}