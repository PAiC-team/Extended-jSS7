
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface TCConversationMessage extends Encodable {

    int _TAG_CONVERSATION_WITH_PERM = 5;
    int _TAG_CONVERSATION_WITHOUT_PERM = 6;

    boolean getDialogTermitationPermission();

    void setDialogTermitationPermission(boolean perm);

    byte[] getOriginatingTransactionId();

    void setOriginatingTransactionId(byte[] t);

    byte[] getDestinationTransactionId();

    void setDestinationTransactionId(byte[] t);

    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dp);

    Component[] getComponent();

    void setComponent(Component[] c);

}
