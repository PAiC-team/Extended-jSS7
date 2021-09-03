
package org.restcomm.protocols.ss7.cap.api.dialog;

import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;

/**
 * @author sergey vetyutnev
 *
 */
public interface ServingCheckData {

    ServingCheckResult getResult();

    ApplicationContextName getAlternativeApplicationContext();

}
