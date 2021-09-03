
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.oam.ActivateTraceModeResponse_Oam;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPDialogOam;

/**
*
* @author sergey vetyutnev
*
*/
public class ActivateTraceModeResponseImpl_Oam extends ActivateTraceModeResponseImpl_Base implements ActivateTraceModeResponse_Oam {

    public ActivateTraceModeResponseImpl_Oam() {
        super();
    }

    public ActivateTraceModeResponseImpl_Oam(MAPExtensionContainer extensionContainer, boolean traceSupportIndicator) {
        super(extensionContainer, traceSupportIndicator);
    }

    public MAPDialogOam getMAPDialog() {
        MAPDialog mapDialog = super.getMAPDialog();
        return (MAPDialogOam) mapDialog;
    }

}
