
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import java.io.Serializable;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Component;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;

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

}
