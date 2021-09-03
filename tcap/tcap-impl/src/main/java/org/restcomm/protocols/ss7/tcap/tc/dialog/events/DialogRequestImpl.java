package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.DialogRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.EventType;

/**
 * @author baranowb
 *
 */
public abstract class DialogRequestImpl implements DialogRequest {

    private Dialog dialog;
    private EventType type;

    protected DialogRequestImpl(EventType type) {
        super();
        this.type = type;
    }

    /**
     * @return the dialog
     */
    public Dialog getDialog() {
        return this.dialog;
    }

    /**
     * @param dialog the dialog to set
     */
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    /**
     * @return the type
     */
    public EventType getType() {
        return this.type;
    }

}
