
package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public interface TCResponseMessage extends Encodable {

    int _TAG_RESPONSE = 4;

    byte[] getDestinationTransactionId();

    void setDestinationTransactionId(byte[] t);

    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dp);

    Component[] getComponent();

    void setComponent(Component[] c);

}
