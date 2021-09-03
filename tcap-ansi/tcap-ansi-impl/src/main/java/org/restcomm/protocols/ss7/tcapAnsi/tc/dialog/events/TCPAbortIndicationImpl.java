
package org.restcomm.protocols.ss7.tcapAnsi.tc.dialog.events;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCPAbortIndication;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCPAbortIndicationImpl extends DialogIndicationImpl implements TCPAbortIndication {
    // This indication is used to inform user of abnormal cases.
    private PAbortCause cause;

    // private boolean localProviderOriginated = false;

    TCPAbortIndicationImpl() {
        super(EventType.Abort);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication#getPAbortCause()
     */
    public PAbortCause getPAbortCause() {
        return this.cause;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication#setPAbortCause(org.restcomm.protocols.ss7.tcap
     * .asn.comp.PAbortCauseType)
     */
    public void setPAbortCause(PAbortCause t) {
        this.cause = t;
    }

    // public boolean isLocalProviderOriginated() {
    // return localProviderOriginated;
    // }
    //
    // public void setLocalProviderOriginated(boolean val) {
    // localProviderOriginated = val;
    // }
}
