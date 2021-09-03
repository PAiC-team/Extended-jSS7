
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 Ext-SS-Status ::= OCTET STRING (SIZE (1..5))
 *
 * -- OCTET 1: -- -- bits 8765: 0000 (unused) -- bits 4321: Used to convey the "P bit","R bit","A bit" and "Q bit", --
 * representing supplementary service state information -- as defined in TS 3GPP TS 23.011 [22]
 *
 * -- bit 4: "Q bit"
 *
 * -- bit 3: "P bit"
 *
 * -- bit 2: "R bit"
 *
 * -- bit 1: "A bit"
 *
 * -- OCTETS 2-5: reserved for future use. They shall be discarded if -- received and not understood.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtSSStatus extends Serializable {

    byte[] getData();

    boolean getBitQ();

    boolean getBitP();

    boolean getBitR();

    boolean getBitA();

}
