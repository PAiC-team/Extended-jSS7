
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 PDPID ::= OCTET STRING (SIZE (1)) -- PDP Identifier is a counter used to identify a specific PDP Context within a control --
 * relationship between gprsSSF and gsmSCF.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDPID extends Serializable {

    int getId();

}