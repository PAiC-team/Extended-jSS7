
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 EndUserAddress {PARAMETERS-BOUND: bound} ::= SEQUENCE { pDPTypeOrganization [0] PDPTypeOrganization, pDPTypeNumber [1]
 * PDPTypeNumber, pDPAddress [2] PDPAddress OPTIONAL } -- Indicates the EndUserAddress, refer to 3GPP TS 29.060 [12] for the
 * encoding.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EndUserAddress extends Serializable {

    PDPTypeOrganization getPDPTypeOrganization();

    PDPTypeNumber getPDPTypeNumber();

    PDPAddress getPDPAddress();

}