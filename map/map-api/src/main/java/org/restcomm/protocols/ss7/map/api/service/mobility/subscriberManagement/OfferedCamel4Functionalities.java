
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
OfferedCamel4Functionalities ::= BIT STRING {
  initiateCallAttempt (0),
  splitLeg (1),
  moveLeg (2),
  disconnectLeg (3),
  entityReleased (4),
  dfc-WithArgument (5),
  playTone (6),
  dtmf-MidCall (7),
  chargingIndicator (8),
  alertingDP (9),
  locationAtAlerting (10),
  changeOfPositionDP (11),
  or-Interactions (12),
  warningToneEnhancements (13),
  cf-Enhancements (14),
  subscribedEnhancedDialledServices (15),
  servingNetworkEnhancedDialledServices (16),
  criteriaForChangeOfPositionDP (17),
  serviceChangeDP (18),
  collectInformation (19)
} (SIZE (15..64))
-- A node supporting Camel phase 4 shall mark in the BIT STRING all CAMEL4
-- functionalities it offers.
-- Other values than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OfferedCamel4Functionalities extends Serializable {

    boolean getInitiateCallAttempt();

    boolean getSplitLeg();

    boolean getMoveLeg();

    boolean getDisconnectLeg();

    boolean getEntityReleased();

    boolean getDfcWithArgument();

    boolean getPlayTone();

    boolean getDtmfMidCall();

    boolean getChargingIndicator();

    boolean getAlertingDP();

    boolean getLocationAtAlerting();

    boolean getChangeOfPositionDP();

    boolean getOrInteractions();

    boolean getWarningToneEnhancements();

    boolean getCfEnhancements();

    boolean getSubscribedEnhancedDialledServices();

    boolean getServingNetworkEnhancedDialledServices();

    boolean getCriteriaForChangeOfPositionDP();

    boolean getServiceChangeDP();

    boolean getCollectInformation();

}
