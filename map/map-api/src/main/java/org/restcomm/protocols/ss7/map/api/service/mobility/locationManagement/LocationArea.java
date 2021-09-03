
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;

/**
 *
 LocationArea ::= CHOICE { laiFixedLength [0] LAIFixedLength, lac [1] LAC}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LocationArea extends Serializable {

    LAIFixedLength getLAIFixedLength();

    LAC getLAC();

}
