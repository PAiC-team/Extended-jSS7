
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
 *
 CODEC-Info ::= OCTET STRING (SIZE (5..10)) -- Refers to channel type -- coded according to 3GPP TS 48.008 [49] and including
 * Element identifier and Length
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CODECInfo extends Serializable {

    byte[] getData();

}
