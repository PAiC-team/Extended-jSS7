
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
 LAC ::= OCTET STRING (SIZE (2)) -- Refers to Location Area Code of the Location Area Identification defined in -- 3GPP TS
 * 23.003 [17]. -- Location Area Code according to 3GPP TS 24.008 [35]
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LAC extends Serializable {

    byte[] getData();

    int getLac() throws MAPException;

}
