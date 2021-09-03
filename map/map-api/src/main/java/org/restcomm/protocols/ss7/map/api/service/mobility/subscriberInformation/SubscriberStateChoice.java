package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 *
<code>
SubscriberState ::= CHOICE {
  assumedIdle         [0] NULL,
  camelBusy           [1] NULL,
  netDetNotReachable  NotReachableReason,
  notProvidedFromVLR  [2] NULL}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public enum SubscriberStateChoice {

    assumedIdle, camelBusy, netDetNotReachable, notProvidedFromVLR;

}
