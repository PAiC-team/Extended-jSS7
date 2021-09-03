
package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.tcap.api.MessageType;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.DraftParsedMessage;

/**
 * @author sergey vetyutnev
 *
 */
public class DraftParsedMessageImpl implements DraftParsedMessage {

    private MessageType messageType = MessageType.Unknown;
    private Long originationDialogId;
    private Long destinationDialogId;
    private String parsingErrorReason;

    @Override
    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public Long getOriginationDialogId() {
        return this.originationDialogId;
    }

    public void setOriginationDialogId(Long originationDialogId) {
        this.originationDialogId = originationDialogId;
    }

    @Override
    public Long getDestinationDialogId() {
        return this.destinationDialogId;
    }

    public void setDestinationDialogId(Long destinationDialogId) {
        this.destinationDialogId = destinationDialogId;
    }

    @Override
    public String getParsingErrorReason() {
        return this.parsingErrorReason;
    }

    public void setParsingErrorReason(String parsingErrorReason) {
        this.parsingErrorReason = parsingErrorReason;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("DraftParsedMessage [");
        sb.append(messageType);
        if (originationDialogId != null) {
            sb.append(", originationDialogId=");
            sb.append(originationDialogId);
        }
        if (destinationDialogId != null) {
            sb.append(", destinationDialogId=");
            sb.append(destinationDialogId);
        }
        if (parsingErrorReason != null) {
            sb.append(", parsingErrorReason=");
            sb.append(parsingErrorReason);
        }
        sb.append("]");

        return sb.toString();
    }

}
