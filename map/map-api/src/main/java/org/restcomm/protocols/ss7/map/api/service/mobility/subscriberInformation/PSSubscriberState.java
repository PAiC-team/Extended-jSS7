
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;
import java.util.ArrayList;

/**
<code>
PS-SubscriberState ::= CHOICE {
  notProvidedFromSGSNorMME            [0] NULL,
  ps-Detached                         [1] NULL,
  ps-AttachedNotReachableForPaging    [2] NULL,
  ps-AttachedReachableForPaging       [3] NULL,
  ps-PDP-ActiveNotReachableForPaging  [4] PDP-ContextInfoList,
  ps-PDP-ActiveReachableForPaging     [5] PDP-ContextInfoList,
  netDetNotReachable                  NotReachableReason
}

PDP-ContextInfoList ::= SEQUENCE SIZE (1..50) OF PDP-ContextInfo
</code>
 *
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface PSSubscriberState extends Serializable {

    PSSubscriberStateChoice getChoice();

    ArrayList<PDPContextInfo> getPDPContextInfoList();

    NotReachableReason getNetDetNotReachable();

}
