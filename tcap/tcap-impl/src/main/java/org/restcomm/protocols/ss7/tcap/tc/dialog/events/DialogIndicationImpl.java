package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.DialogIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;

/**
 * @author baranowb
 *
 */
public abstract class DialogIndicationImpl implements DialogIndication {

    private Component[] components;
    private Dialog dialog;
    private Byte qos;
    private EventType type;

    protected DialogIndicationImpl(EventType type) {
        super();
        this.type = type;
    }

    /**
     * @return the components
     */
    public Component[] getComponents() {
        return this.components;
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

    /**
     * @return the qos
     */
    public Byte getQos() {
        return this.qos;
    }

    /**
     * @param qos the qos to set
     */
    public void setQos(Byte qos) {
        this.qos = qos;
    }

}
