
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 * <pre>
 * LSAIdentity ::= OCTET STRING (SIZE (3))
 *     -- Octets are coded according to TS 3GPP TS 23.003 [17]
 *
 * -- Cells may be grouped into specific localised service areas. Each localised service area is identified by a localised
 * -- service area identity (LSA ID). No restrictions are placed on what cells may be grouped into a given localised service
 * -- area.
 * -- The LSA ID can either be a PLMN significant number or a universal identity. This shall be known both in the networks
 * -- and in the SIM.
 * -- The LSA ID consists of 24 bits, numbered from 0 to 23, with bit 0 being the LSB. Bit 0 indicates whether the LSA is a
 * -- PLMN significant number or a universal LSA. If the bit is set to 0 the LSA is a PLMN significant number; if it is set to
 * -- 1 it is a universal LSA.
 *
 * --MSB                                          LSB
 *    ________________________________________________
 * --|    23 Bits                            | 1 bit  |
 * --|_______________________________________|________|
 *   <----------------- LSA ID ----------------------->
 * </pre>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LSAIdentity extends Serializable {

    byte[] getData();

    boolean isPlmnSignificantLSA();

}