
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

/**
 *
<code>
DpSpecificInfoAlt {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  ...,
  oServiceChangeSpecificInfo   [0] SEQUENCE {
    ext-basicServiceCode  [0] Ext-BasicServiceCode OPTIONAL,
    ...
  }
  collectedInfoSpecificInfo    [2] SEQUENCE {
    calledPartyNumber     [0] CalledPartyNumber OPTIONAL,
    ...
  }
  tServiceChangeSpecificInfo   [1] SEQUENCE {
    ext-basicServiceCode  [0] Ext-BasicServiceCode OPTIONAL,
    ...
  }
}
-- This datatype is for extension in future releases.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DpSpecificInfoAlt extends Serializable {

    OServiceChangeSpecificInfo getOServiceChangeSpecificInfo();

    CollectedInfoSpecificInfo getCollectedInfoSpecificInfo();

    TServiceChangeSpecificInfo getTServiceChangeSpecificInfo();

}
