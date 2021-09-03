
package org.restcomm.protocols.ss7.map.service.mobility.oam;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeResponse_Mobility;
import org.restcomm.protocols.ss7.map.service.oam.ActivateTraceModeResponseImpl_Base;

/**
*
* @author sergey vetyutnev
*
*/
public class ActivateTraceModeResponseImpl_Mobility extends ActivateTraceModeResponseImpl_Base implements ActivateTraceModeResponse_Mobility {

    public ActivateTraceModeResponseImpl_Mobility() {
        super();
    }

    public ActivateTraceModeResponseImpl_Mobility(MAPExtensionContainer extensionContainer, boolean traceSupportIndicator) {
        super(extensionContainer, traceSupportIndicator);
    }

    public MAPDialogMobility getMAPDialog() {
        MAPDialog mapDialog = super.getMAPDialog();
        return (MAPDialogMobility) mapDialog;
    }

}
