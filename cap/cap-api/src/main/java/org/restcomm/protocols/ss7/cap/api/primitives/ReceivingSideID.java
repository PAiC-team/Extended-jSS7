
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.LegType;

/**
 *
 ReceivingSideID ::= CHOICE { receivingSideID [1] LegType } -- used to identify LegID in operations sent from gsmSSF to gsmSCF
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReceivingSideID extends Serializable {

    LegType getReceivingSideID();

}