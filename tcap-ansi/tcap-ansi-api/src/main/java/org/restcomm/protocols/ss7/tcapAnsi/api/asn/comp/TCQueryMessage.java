
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public interface TCQueryMessage extends Encodable {

    int _TAG_QUERY_WITH_PERM = 2;
    int _TAG_QUERY_WITHOUT_PERM = 3;
    int _TAG_TRANSACTION_ID = 7;
    int _TAG_COMPONENT_SEQUENCE = 8;


    boolean getDialogTermitationPermission();

    void setDialogTermitationPermission(boolean perm);

    byte[] getOriginatingTransactionId();

    void setOriginatingTransactionId(byte[] t);

    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dp);

    Component[] getComponent();

    void setComponent(Component[] c);

}
