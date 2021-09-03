
package org.restcomm.protocols.ss7.tcap;

import java.util.Map;

import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.tcap.PreviewDialogData;
import org.restcomm.protocols.ss7.tcap.PreviewDialogDataKey;
import org.restcomm.protocols.ss7.tcap.TCAPStackImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TCAPStackImplWrapper extends TCAPStackImpl {

    public TCAPStackImplWrapper(SccpProvider sccpProvider, int ssn, String stackName) {
        super(stackName);

        this.tcapProvider = new TCAPProviderImplWrapper(sccpProvider, this, ssn);
    }

    public Map<PreviewDialogDataKey, PreviewDialogData> getDialogPreviewList() {
        TCAPProviderImplWrapper prov = (TCAPProviderImplWrapper) this.getProvider();
        return prov.getDialogPreviewList();
    }

}
