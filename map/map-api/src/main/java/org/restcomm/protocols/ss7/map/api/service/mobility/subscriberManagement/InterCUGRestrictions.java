
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 InterCUG-Restrictions ::= OCTET STRING (SIZE (1))
 *
 * -- bits 876543: 000000 (unused) -- Exception handling: -- bits 876543 shall be ignored if received and not understood
 *
 * -- bits 21 -- 00 CUG only facilities -- 01 CUG with outgoing access -- 10 CUG with incoming access -- 11 CUG with both
 * outgoing and incoming access
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InterCUGRestrictions extends Serializable {

    int getData();

    InterCUGRestrictionsValue getInterCUGRestrictionsValue();

}
