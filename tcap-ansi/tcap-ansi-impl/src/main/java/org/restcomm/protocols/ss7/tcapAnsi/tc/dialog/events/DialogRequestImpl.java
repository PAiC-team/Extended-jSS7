
package org.restcomm.protocols.ss7.tcapAnsi.tc.dialog.events;

import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.DialogRequest;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.EventType;

/**
 * @author baranowb
 *
 */
public abstract class DialogRequestImpl implements DialogRequest {

    private Dialog dialog;
    protected EventType type;
    protected boolean returnMessageOnError;

    protected DialogRequestImpl(EventType type) {
        super();
        this.type = type;
    }

    /**
     * @return the dialog
     */
    public Dialog getDialog() {
        return dialog;
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
        return type;
    }

    public boolean getReturnMessageOnError() {
        return returnMessageOnError;
    }

    public void setReturnMessageOnError(boolean val) {
        returnMessageOnError = val;
    }

}
