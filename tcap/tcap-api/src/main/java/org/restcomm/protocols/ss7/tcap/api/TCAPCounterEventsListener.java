
package org.restcomm.protocols.ss7.tcap.api;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

public interface TCAPCounterEventsListener {

    void updateTcUniReceivedCount(Dialog tcapDialog);

    void updateTcUniSentCount(Dialog tcapDialog);

    void updateTcBeginReceivedCount(Dialog tcapDialog);

    void updateTcBeginSentCount(Dialog tcapDialog);

    void updateTcContinueReceivedCount(Dialog tcapDialog);

    void updateTcContinueSentCount(Dialog tcapDialog);

    void updateTcEndReceivedCount(Dialog tcapDialog);

    void updateTcEndSentCount(Dialog tcapDialog);

    void updateTcPAbortReceivedCount(Dialog tcapDialog, PAbortCauseType cause);

    void updateTcPAbortSentCount(byte[] originatingTransactionId, PAbortCauseType cause);

    void updateTcUserAbortReceivedCount(Dialog tcapDialog);

    void updateTcUserAbortSentCount(Dialog tcapDialog);

    void updateInvokeReceivedCount(Dialog tcapDialog, Invoke invoke);

    void updateInvokeSentCount(Dialog tcapDialog, Invoke invoke);

    void updateReturnResultReceivedCount(Dialog tcapDialog);

    void updateReturnResultSentCount(Dialog tcapDialog);

    void updateReturnResultLastReceivedCount(Dialog tcapDialog);

    void updateReturnResultLastSentCount(Dialog tcapDialog);

    void updateReturnErrorReceivedCount(Dialog tcapDialog);

    void updateReturnErrorSentCount(Dialog tcapDialog);

    void updateRejectReceivedCount(Dialog tcapDialog);

    void updateRejectSentCount(Dialog tcapDialog);

    void updateDialogTimeoutCount(Dialog tcapDialog);

    void updateDialogReleaseCount(Dialog tcapDialog);
}
