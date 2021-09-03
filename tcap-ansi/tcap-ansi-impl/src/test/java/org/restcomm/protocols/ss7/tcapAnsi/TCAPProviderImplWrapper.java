
package org.restcomm.protocols.ss7.tcapAnsi;

import java.util.Map;

import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcapAnsi.DialogImpl;
import org.restcomm.protocols.ss7.tcapAnsi.PreviewDialogData;
import org.restcomm.protocols.ss7.tcapAnsi.PreviewDialogDataKey;
import org.restcomm.protocols.ss7.tcapAnsi.TCAPProviderImpl;
import org.restcomm.protocols.ss7.tcapAnsi.TCAPStackImpl;
import org.restcomm.protocols.ss7.tcapAnsi.api.TCAPException;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;

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
