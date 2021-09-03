
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;

/**
 *
<code>
tNoAnswerSpecificInfo [9] SEQUENCE {
  callForwarded                [50] NULL OPTIONAL,
  forwardingDestinationNumber  [52] CalledPartyNumber {bound} OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TNoAnswerSpecificInfo extends Serializable {

    boolean getCallForwarded();

    CalledPartyNumberCap getForwardingDestinationNumber();

}