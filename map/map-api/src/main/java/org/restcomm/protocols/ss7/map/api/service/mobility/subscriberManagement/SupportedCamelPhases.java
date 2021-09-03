
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
SupportedCamelPhases ::= BIT STRING {
  phase1 (0),
  phase2 (1),
  phase3 (2),
  phase4 (3)} (SIZE (1..16))
-- A node shall mark in the BIT STRING all CAMEL Phases it supports.
-- Other values than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SupportedCamelPhases extends Serializable {

    boolean getPhase1Supported();

    boolean getPhase2Supported();

    boolean getPhase3Supported();

    boolean getPhase4Supported();

}
