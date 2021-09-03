
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
SubscriberState ::= CHOICE {
  assumedIdle         [0] NULL,
  camelBusy           [1] NULL,
  netDetNotReachable  NotReachableReason,
  notProvidedFromVLR  [2] NULL
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface SubscriberState extends Serializable {

    SubscriberStateChoice getSubscriberStateChoice();

    // return a value when getSubscriberStateChoice()==SubscriberStateChoice.netDetNotReachable
    NotReachableReason getNotReachableReason();

}
