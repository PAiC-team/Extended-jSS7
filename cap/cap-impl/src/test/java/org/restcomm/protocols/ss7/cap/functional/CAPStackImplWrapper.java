package org.restcomm.protocols.ss7.cap.functional;

import org.restcomm.protocols.ss7.cap.CAPStackImpl;
import org.restcomm.protocols.ss7.sccp.SccpProvider;

public class CAPStackImplWrapper extends CAPStackImpl {

    public CAPStackImplWrapper(SccpProvider sccpPprovider, int ssn) {
        super("Test", sccpPprovider, ssn);
        this.capProvider = new CAPProviderImplWrapper(this.tcapStack.getProvider());
    }
}
