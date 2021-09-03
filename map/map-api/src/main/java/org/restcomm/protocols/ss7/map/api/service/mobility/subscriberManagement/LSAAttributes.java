
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 LSAAttributes ::= OCTET STRING (SIZE (1)) -- Octets are coded according to TS 3GPP TS 48.008 [49]
 *
 * --Bits 1 to 4 of octet (x+1) define the priority of the LSA identification. --Bit 4321 -- 0000 priority 1 = lowest priority
 * -- 0001 priority 2 = second lowest priority -- : : : : -- 1111 priority 16= highest priority
 *
 * --If the preferential access indicator (bit 5 of octet (x+1)) is set to 1 the subscriber has preferential access in the LSA.
 * If --the active mode support indicator (bit 6 of octet (x+1)) is set to 1 the subscriber has active mode support in the LSA.
 *
 * --Coding of the i-th LSA identification with attributes: --8 7 6 5 4 3 2 1 --spare act pref priority
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LSAAttributes extends Serializable {

    int getData();

    LSAIdentificationPriorityValue getLSAIdentificationPriority();

    boolean isPreferentialAccessAvailable();

    boolean isActiveModeSupportAvailable();
}
