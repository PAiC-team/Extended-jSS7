package org.restcomm.protocols.ss7.map.dialog;

import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;

public class ServingCheckDataImpl implements ServingCheckData {

    private ServingCheckResult result;
    private ApplicationContextName alternativeApplicationContext = null;

    public ServingCheckDataImpl(ServingCheckResult result) {
        this.result = result;
    }

    public ServingCheckDataImpl(ServingCheckResult result, ApplicationContextName alternativeApplicationContext) {
        this.result = result;
        this.alternativeApplicationContext = alternativeApplicationContext;
    }

    public ServingCheckResult getResult() {
        return this.result;
    }

    public ApplicationContextName getAlternativeApplicationContext() {
        return this.alternativeApplicationContext;
    }
}
