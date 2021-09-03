
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
 AreaIdentification ::= OCTET STRING (SIZE (2..7)) -- The internal structure is defined as follows: -- octet 1 bits 4321
 * Mobile Country Code 1st digit -- bits 8765 Mobile Country Code 2nd digit -- octet 2 bits 4321 Mobile Country Code 3rd digit
 * -- bits 8765 Mobile Network Code 3rd digit if 3 digit MNC included -- or filler (1111) -- octet 3 bits 4321 Mobile Network
 * Code 1st digit -- bits 8765 Mobile Network Code 2nd digit -- octets 4 and 5 Location Area Code (LAC) for Local Area Id, --
 * Routing Area Id and Cell Global Id -- octet 6 Routing Area Code (RAC) for Routing Area Id -- octets 6 and 7 Cell Identity
 * (CI) for Cell Global Id -- octets 4 until 7 Utran Cell Identity (UC-Id) for Utran Cell Id
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AreaIdentification extends Serializable {

    byte[] getData();

    int getMCC() throws MAPException;

    int getMNC() throws MAPException;

    int getLac() throws MAPException;

    int getRac() throws MAPException;

    int getCellId() throws MAPException;

    int getUtranCellId() throws MAPException;

}
