
package org.restcomm.protocols.ss7.tcap;

import java.util.Map;

import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.DialogImpl;
import org.restcomm.protocols.ss7.tcap.PreviewDialogData;
import org.restcomm.protocols.ss7.tcap.PreviewDialogDataKey;
import org.restcomm.protocols.ss7.tcap.TCAPProviderImpl;
import org.restcomm.protocols.ss7.tcap.TCAPStackImpl;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TCAPProviderImplWrapper extends TCAPProviderImpl {

    protected TCAPProviderImplWrapper(SccpProvider sccpProvider, TCAPStackImpl stack, int ssn) {
        super(sccpProvider, stack, ssn);
    }

    public Map<PreviewDialogDataKey, PreviewDialogData> getDialogPreviewList() {
        return dialogPreviewList;
    }

    public Long getAvailableTxIdPreview() throws TCAPException {
        return super.getAvailableTxIdPreview();
    }

    public Dialog createPreviewDialog(PreviewDialogDataKey ky, SccpAddress localAddress, SccpAddress remoteAddress,
            int seqControl) throws TCAPException {
        return super.createPreviewDialog(ky, localAddress, remoteAddress, seqControl);
    }

    public Dialog getPreviewDialog(PreviewDialogDataKey ky1, PreviewDialogDataKey ky2, SccpAddress localAddress,
            SccpAddress remoteAddress, int seqControl) {
        return super.getPreviewDialog(ky1, ky2, localAddress, remoteAddress, seqControl);
    }

    public void removePreviewDialog(DialogImpl di) {
        super.removePreviewDialog(di);
    }

}
