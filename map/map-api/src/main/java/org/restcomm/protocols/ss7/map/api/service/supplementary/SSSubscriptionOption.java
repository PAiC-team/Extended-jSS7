
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

/**
 *
<code>
SS-SubscriptionOption ::= CHOICE {
  cliRestrictionOption [2] CliRestrictionOption,
  overrideCategory     [1] OverrideCategory
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSSubscriptionOption extends Serializable {

    CliRestrictionOption getCliRestrictionOption();

    OverrideCategory getOverrideCategory();

}