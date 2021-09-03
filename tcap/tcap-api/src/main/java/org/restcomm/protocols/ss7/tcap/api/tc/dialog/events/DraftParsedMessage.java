
package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import org.restcomm.protocols.ss7.tcap.api.MessageType;

/**
 * @author sergey vetyutnev
 *
 */
public interface DraftParsedMessage {

    MessageType getMessageType();

    Long getOriginationDialogId();

    Long getDestinationDialogId();

    String getParsingErrorReason();

}
