
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;

/**
 *
<code>
SS-Data ::= SEQUENCE {
  ss-Code                 SS-Code OPTIONAL,
  ss-Status               [4] SS-Status OPTIONAL,
  ss-SubscriptionOption   SS-SubscriptionOption OPTIONAL,
  basicServiceGroupList   BasicServiceGroupList OPTIONAL,
  ...,
  defaultPriority         EMLPP-Priority OPTIONAL,
  nbrUser                 [5] MC-Bearers OPTIONAL
}

BasicServiceGroupList ::= SEQUENCE SIZE (1..13) OF BasicServiceCode

MC-Bearers ::= INTEGER (1..7)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSData extends Serializable {

    SSCode getSsCode();

    SSStatus getSsStatus();

    SSSubscriptionOption getSsSubscriptionOption();

    ArrayList<BasicServiceCode> getBasicServiceGroupList();

    EMLPPPriority getDefaultPriority();

    Integer getNbrUser();

}
