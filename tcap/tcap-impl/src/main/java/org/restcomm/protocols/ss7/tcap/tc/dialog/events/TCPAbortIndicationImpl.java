package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.EventType;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCPAbortIndicationImpl extends DialogIndicationImpl implements TCPAbortIndication {

    // This indication is used to inform user of abnormal cases.
    private PAbortCauseType cause;

    // private boolean localProviderOriginated = false;

    TCPAbortIndicationImpl() {
        super(EventType.PAbort);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication#getPAbortCause()
     */
    public PAbortCauseType getPAbortCause() {
        return this.cause;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication#setPAbortCause(org.restcomm.protocols.ss7.tcap
     * .asn.comp.PAbortCauseType)
     */
    public void setPAbortCause(PAbortCauseType providerAbortCause) {
        this.cause = providerAbortCause;
    }

    // public boolean isLocalProviderOriginated() {
    // return localProviderOriginated;
    // }
    //
    // public void setLocalProviderOriginated(boolean val) {
    // localProviderOriginated = val;
    // }
}
