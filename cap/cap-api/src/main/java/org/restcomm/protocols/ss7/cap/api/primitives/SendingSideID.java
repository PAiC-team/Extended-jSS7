
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.LegType;

/**
 *
 SendingSideID ::= CHOICE {sendingSideID [0] LegType} -- used to identify LegID in operations sent from gsmSCF to gsmSSF
 *
 * LegType ::= OCTET STRING (SIZE(1)) leg1 LegType ::= '01'H leg2 LegType ::= '02'H
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendingSideID extends Serializable {

    LegType getSendingSideID();

}