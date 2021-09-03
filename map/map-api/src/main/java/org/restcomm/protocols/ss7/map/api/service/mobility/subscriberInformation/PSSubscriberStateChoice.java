
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

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
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum PSSubscriberStateChoice {

    notProvidedFromSGSNorMME, psDetached, psAttachedNotReachableForPaging, psAttachedReachableForPaging,
    psPDPActiveNotReachableForPaging, psPDPActiveReachableForPaging, netDetNotReachable;

}
