
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
 *
 CallReferenceNumber ::= OCTET STRING (SIZE (1..8))
 *
 * The use of this parameter and the conditions for its presence are specified in 3GPP TS 23.078 [98] and 3GPP TS 23.079 [99].
 *
 * This parameter gives the call reference number assigned to the call by the CCF. For encoding see GSM 09.02 [20].
 *
 * The CRN is an octet string with variable length between one and eight octets. An MSC uses an internal counter to generate the
 * CRN. Each CRN that is generated in an MSC is unique within that MSC. For every call that is established in an MSC, a CRN is
 * generated by that MSC. The CRN is strictly associated with the MSC that allocates the CRN.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallReferenceNumber extends Serializable {

    byte[] getData();

}
