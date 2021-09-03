
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

/**
 *
 AgeIndicator ::= OCTET STRING (SIZE (1..6)) -- The internal structure of this parameter is implementation specific.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AgeIndicator extends Serializable {

    byte[] getData();

}
