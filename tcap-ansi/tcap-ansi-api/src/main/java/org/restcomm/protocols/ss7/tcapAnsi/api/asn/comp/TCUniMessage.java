
/**
 *
 */

package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface TCUniMessage extends Encodable {

    int _TAG_UNI = 1;

    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dp);

    Component[] getComponent();

    void setComponent(Component[] c);
}
