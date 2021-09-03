package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

import java.io.Serializable;

/**
<code>
IP-SM-GW-Guidance ::= SEQUENCE {
  minimumDeliveryTimeValue              SM-DeliveryTimerValue,
  recommendedDeliveryTimeValue          SM-DeliveryTimerValue,
  extensionContainer                    ExtensionContainer OPTIONAL,
  ...
}

SM-DeliveryTimerValue ::= INTEGER (30..600)
</code>
 *
 * @author eva ogallar
 */
public interface IpSmGwGuidance extends Serializable {

    int getMinimumDeliveryTimeValue();

    int getRecommendedDeliveryTimeValue();

    MAPExtensionContainer getExtensionContainer();

}
