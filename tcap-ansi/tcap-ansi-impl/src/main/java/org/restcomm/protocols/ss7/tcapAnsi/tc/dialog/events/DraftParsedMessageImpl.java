
package org.restcomm.protocols.ss7.tcapAnsi.tc.dialog.events;

import org.restcomm.protocols.ss7.tcapAnsi.api.MessageType;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.DraftParsedMessage;

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
        return messageType;
    }

    public void setMessageType(MessageType val) {
        messageType = val;
    }

    @Override
    public Long getOriginationDialogId() {
        return originationDialogId;
    }

    public void setOriginationDialogId(Long val) {
        originationDialogId = val;
    }

    @Override
    public Long getDestinationDialogId() {
        return destinationDialogId;
    }

    public void setDestinationDialogId(Long val) {
        destinationDialogId = val;
    }

    @Override
    public String getParsingErrorReason() {
        return parsingErrorReason;
    }

    public void setParsingErrorReason(String val) {
        parsingErrorReason = val;
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
