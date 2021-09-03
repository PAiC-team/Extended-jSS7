package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 *
 Bit 1 Bit 0 Indication Type: 0 0 Voicemail MessageWaiting 0 1 Fax MessageWaiting 1 0 Electronic Mail MessageWaiting 1 1 Other
 * MessageWaiting* Mobile manufacturers may implement the "Other MessageWaiting" indication as an additional indication without
 * specifying the meaning.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum DataCodingSchemaIndicationType {

    VoicemailMessageWaiting(0), FaxMessageWaiting(1), ElectronicMailMessageWaiting(2), OtherMessageWaiting(3);

    private int code;

    private DataCodingSchemaIndicationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static DataCodingSchemaIndicationType getInstance(int code) {
        switch (code) {
            case 0:
                return VoicemailMessageWaiting;
            case 1:
                return FaxMessageWaiting;
            case 2:
                return ElectronicMailMessageWaiting;
            default:
                return OtherMessageWaiting;
        }
    }
}