
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
CallBarringInfo ::= SEQUENCE {
  ss-Code                  SS-Code OPTIONAL,
  callBarringFeatureList   CallBarringFeatureList,
  ...
}

CallBarringFeatureList ::= SEQUENCE SIZE (1..13) OF CallBarringFeature
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallBarringInfo extends Serializable {

    SSCode getSsCode();

    ArrayList<CallBarringFeature> getCallBarringFeatureList();

}
