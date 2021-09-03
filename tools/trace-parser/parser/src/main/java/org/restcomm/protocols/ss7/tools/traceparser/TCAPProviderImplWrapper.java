package org.restcomm.protocols.ss7.tools.traceparser;

import java.util.concurrent.ScheduledExecutorService;

import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.tcap.TCAPProviderImpl;
import org.restcomm.protocols.ss7.tcap.TCAPStackImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TCAPProviderImplWrapper extends TCAPProviderImpl {

    TCAPProviderImplWrapper(SccpProvider sccpProvider, TCAPStackImpl stack, int ssn) {
        super(sccpProvider, stack, ssn);

    }

    public ScheduledExecutorService getExecuter() {
        return this._EXECUTOR;
    }

}
