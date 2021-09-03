
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import org.restcomm.protocols.ss7.tcapAnsi.api.MessageType;

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
