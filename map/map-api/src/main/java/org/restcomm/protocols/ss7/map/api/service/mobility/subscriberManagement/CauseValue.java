
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 CauseValue ::= OCTET STRING (SIZE(1)) -- Type extracted from Cause parameter in ITU-T Recommendation Q.763. -- For the use of
 * cause value refer to ITU-T Recommendation Q.850.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CauseValue extends Serializable {

    int getData();

    CauseValueCodeValue getCauseValueCodeValue();

}
