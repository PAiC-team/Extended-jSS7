
package org.restcomm.protocols.ss7.tcapAnsi.tc.dialog.events;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Component;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.DialogIndication;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.EventType;

/**
 * @author baranowb
 *
 */
public abstract class DialogIndicationImpl implements DialogIndication {

    private Component[] components;
    private Dialog dialog;
    private EventType type;

    protected DialogIndicationImpl(EventType type) {
        super();
        this.type = type;
    }

    /**
     * @return the components
     */
    public Component[] getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(Component[] components) {
        this.components = components;
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

}
