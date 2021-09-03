
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;

/**
 *
<code>
tAnswerSpecificInfo [10] SEQUENCE {
  destinationAddress      [50] CalledPartyNumber {bound} OPTIONAL,
  or-Call                 [51] NULL OPTIONAL,
  forwardedCall           [52] NULL OPTIONAL,
  chargeIndicator         [53] ChargeIndicator OPTIONAL,
  ext-basicServiceCode    [54] Ext-BasicServiceCode OPTIONAL,
  ext-basicServiceCode2   [55] Ext-BasicServiceCode OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TAnswerSpecificInfo extends Serializable {

    CalledPartyNumberCap getDestinationAddress();

    boolean getOrCall();

    boolean getForwardedCall();

    ChargeIndicator getChargeIndicator();

    ExtBasicServiceCode getExtBasicServiceCode();

    ExtBasicServiceCode getExtBasicServiceCode2();

}