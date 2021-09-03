package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface TCUniMessage extends Encodable {

    int _TAG = 0x01;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_APPLICATION;

    // opt FIXME: make this External?
    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dialogPortion);

    // mandatory
    Component[] getComponent();

    void setComponent(Component[] component);
}
