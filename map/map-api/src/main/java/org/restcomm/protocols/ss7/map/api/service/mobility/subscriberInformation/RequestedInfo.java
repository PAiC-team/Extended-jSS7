package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
<code>
RequestedInfo ::= SEQUENCE {
  locationInformation  [0] NULL OPTIONAL,
  subscriberState      [1] NULL OPTIONAL,
  extensionContainer   [2] ExtensionContainer OPTIONAL,
  ...,
  currentLocation      [3] NULL OPTIONAL,
  requestedDomain      [4] DomainType OPTIONAL,
  imei                 [6] NULL OPTIONAL,
  ms-classmark         [5] NULL OPTIONAL,
  mnpRequestedInfo     [7] NULL OPTIONAL
}
--currentLocation shall be absent if locationInformation is absent
</code>
 *
 *
 * @author abhayani
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface RequestedInfo extends Serializable {

    boolean getLocationInformation();

    boolean getSubscriberState();

    MAPExtensionContainer getExtensionContainer();

    boolean getCurrentLocation();

    DomainType getRequestedDomain();

    boolean getImei();

    boolean getMsClassmark();

    boolean getMnpRequestedInfo();

    boolean getLocationInformationEPSSupported();

    boolean getTadsData();

    RequestedServingNode getRequestedNodes();

    boolean getServingNodeIndication();

    boolean getLocalTimeZoneRequest();
}

