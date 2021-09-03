
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;

/**
 *
<code>
tBusySpecificInfo [8] SEQUENCE {
  busyCause         [0] Cause {bound} OPTIONAL,
  callForwarded     [50] NULL OPTIONAL,
  routeNotPermitted [51] NULL OPTIONAL,
  forwardingDestinationNumber [52] CalledPartyNumber {bound} OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TBusySpecificInfo extends Serializable {

    CauseCap getBusyCause();

    boolean getCallForwarded();

    boolean getRouteNotPermitted();

    CalledPartyNumberCap getForwardingDestinationNumber();

}