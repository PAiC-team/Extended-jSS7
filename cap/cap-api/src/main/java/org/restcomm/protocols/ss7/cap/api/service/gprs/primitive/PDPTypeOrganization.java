package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 PDPTypeOrganization ::= OCTET STRING (1) -- refer to 3GPP TS 29.060 [12] for the encoding. -- The pDPTypeOrganization shall
 * use the least significant 4 bits of the octet encoded. -- The sender of this parameter shall set the most significant 4 bits
 * of the octet to 1. -- The receiver of this parameter shall ignore the most significant 4 bits of this octet.
 *
 * @author Lasith Waruna Perera
 *
 */
public interface PDPTypeOrganization extends Serializable {

    int getData();

    PDPTypeOrganizationValue getPDPTypeOrganizationValue();
}