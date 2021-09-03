
package org.restcomm.protocols.ss7.tcapAnsi.api;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;

public interface TCAPCounterEventsListener {

    void updateTcUniReceivedCount(Dialog dialog);

    void updateTcUniSentCount(Dialog dialog);

    void updateTcQueryReceivedCount(Dialog dialog);

    void updateTcQuerySentCount(Dialog dialog);

    void updateTcConversationReceivedCount(Dialog dialog);

    void updateTcConversationSentCount(Dialog dialog);

    void updateTcResponseReceivedCount(Dialog dialog);

    void updateTcResponseSentCount(Dialog dialog);

    void updateTcPAbortReceivedCount(Dialog dialog, PAbortCause cause);

    void updateTcPAbortSentCount(byte[] originatingTransactionId, PAbortCause cause);

    void updateTcUserAbortReceivedCount(Dialog dialog);

    void updateTcUserAbortSentCount(Dialog dialog);

    void updateInvokeLastReceivedCount(Dialog dialog, Invoke invoke);

    void updateInvokeNotLastReceivedCount(Dialog dialog, Invoke invoke);

    void updateInvokeLastSentCount(Dialog dialog, Invoke invoke);

    void updateInvokeNotLastSentCount(Dialog dialog, Invoke invoke);

    void updateReturnResultNotLastReceivedCount(Dialog dialog);

    void updateReturnResultNotLastSentCount(Dialog dialog);

    void updateReturnResultLastReceivedCount(Dialog dialog);

    void updateReturnResultLastSentCount(Dialog dialog);

    void updateReturnErrorReceivedCount(Dialog dialog);

    void updateReturnErrorSentCount(Dialog dialog);

    void updateRejectReceivedCount(Dialog dialog);

    void updateRejectSentCount(Dialog dialog);

    void updateDialogTimeoutCount(Dialog dialog);

    void updateDialogReleaseCount(Dialog dialog);
}
