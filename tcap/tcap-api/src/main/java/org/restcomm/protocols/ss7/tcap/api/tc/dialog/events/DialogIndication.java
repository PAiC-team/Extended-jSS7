package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import java.io.Serializable;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;

public interface DialogIndication extends Serializable {

    /**
     * Return dialog for this indication
     *
     * @return
     */
    Dialog getDialog();

    /**
     * get components if present, if there are none, it will return null;
     *
     * @return
     */
    Component[] getComponents();

    EventType getType();

    Byte getQos();
}
