
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import java.io.Serializable;

import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;

/**
 * @author baranowb
 *
 */
public interface DialogRequest extends Serializable {

    Dialog getDialog();

    EventType getType();


    void setReturnMessageOnError(boolean val);

    boolean getReturnMessageOnError();

}
