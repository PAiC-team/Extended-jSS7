package org.restcomm.protocols.ss7.tcap.api.tc.dialog.events;

import java.io.Serializable;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;

/**
 * @author baranowb
 *
 */
public interface DialogRequest extends Serializable {
    /**
     * Return dialog for this indication
     *
     * @return
     */
    Dialog getDialog();

    EventType getType();

}
