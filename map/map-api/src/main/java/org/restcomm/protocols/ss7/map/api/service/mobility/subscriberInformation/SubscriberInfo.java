package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
<code>
SubscriberInfo ::= SEQUENCE {
  locationInformation            [0] LocationInformation OPTIONAL,
  subscriberState                [1] SubscriberState OPTIONAL,
  extensionContainer             [2] ExtensionContainer OPTIONAL,
  ... ,
  locationInformationGPRS        [3] LocationInformationGPRS OPTIONAL,
  ps-SubscriberState             [4] PS-SubscriberState OPTIONAL,
  imei                           [5] IMEI OPTIONAL,
  ms-Classmark2                  [6] MS-Classmark2 OPTIONAL,
  gprs-MS-Class                  [7] GPRSMSClass OPTIONAL,
  mnpInfoRes                     [8] MNPInfoRes OPTIONAL
}
-- If the HLR receives locationInformation, subscriberState or ms-Classmark2 from an SGSN
-- it shall discard them.
-- If the HLR receives locationInformationGPRS, ps-SubscriberState or gprs-MS-Class from
-- a VLR it shall discard them.
-- If the HLR receives parameters which it has not requested, it shall discard them.
</code>
 *
 * @author amit bhayani
 *
 */
public interface SubscriberInfo extends Serializable {

    LocationInformation getLocationInformation();

    SubscriberState getSubscriberState();

    MAPExtensionContainer getExtensionContainer();

    LocationInformationGPRS getLocationInformationGPRS();

    PSSubscriberState getPSSubscriberState();

    IMEI getIMEI();

    MSClassmark2 getMSClassmark2();

    GPRSMSClass getGPRSMSClass();

    MNPInfoRes getMNPInfoRes();

}
