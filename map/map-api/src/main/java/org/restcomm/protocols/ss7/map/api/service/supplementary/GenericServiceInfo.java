
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;

/**
 *
<code>
GenericServiceInfo ::= SEQUENCE {
  ss-Status               SS-Status,
  cliRestrictionOption    CliRestrictionOption OPTIONAL,
  ...,
  maximumEntitledPriority    [0] EMLPP-Priority OPTIONAL,
  defaultPriority            [1] EMLPP-Priority OPTIONAL,
  ccbs-FeatureList           [2] CCBS-FeatureList OPTIONAL,
  nbrSB                      [3] MaxMC-Bearers OPTIONAL,
  nbrUser                    [4] MC-Bearers OPTIONAL,
  nbrSN                      [5] MC-Bearers OPTIONAL
}

CCBS-FeatureList ::= SEQUENCE SIZE (1..5) OF CCBS-Feature

MC-Bearers ::= INTEGER (1..7)
MaxMC-Bearers ::= INTEGER (2..7)
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface GenericServiceInfo extends Serializable {

    SSStatus getSsStatus();

    CliRestrictionOption getCliRestrictionOption();

    EMLPPPriority getMaximumEntitledPriority();

    EMLPPPriority getDefaultPriority();

    ArrayList<CCBSFeature> getCcbsFeatureList();

    Integer getNbrSB();

    Integer getNbrUser();

    Integer getNbrSN();

}
