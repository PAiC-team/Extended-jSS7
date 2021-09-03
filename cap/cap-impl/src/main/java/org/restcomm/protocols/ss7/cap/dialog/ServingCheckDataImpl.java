
package org.restcomm.protocols.ss7.cap.dialog;

import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckResult;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ServingCheckDataImpl implements ServingCheckData {

    private ServingCheckResult result;
    private ApplicationContextName alternativeApplicationContextName = null;

    public ServingCheckDataImpl(ServingCheckResult result) {
        this.result = result;
    }

    public ServingCheckDataImpl(ServingCheckResult result, ApplicationContextName alternativeApplicationContextName) {
        this.result = result;
        this.alternativeApplicationContextName = alternativeApplicationContextName;
    }

    public ServingCheckResult getResult() {
        return this.result;
    }

    public ApplicationContextName getAlternativeApplicationContext() {
        return this.alternativeApplicationContextName;
    }
}
