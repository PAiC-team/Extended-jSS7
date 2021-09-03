
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PDPContext;

/**
 *
 GPRSSubscriptionData ::= SEQUENCE {
   completeDataListIncluded NULL OPTIONAL,
   -- If segmentation is used, completeDataListIncluded may only be present in the
   -- first segment of GPRSSubscriptionData.
   gprsDataList [1] GPRSDataList,
   extensionContainer [2] ExtensionContainer OPTIONAL,
   ...,
   apn-oi-Replacement [3] APN-OI-Replacement OPTIONAL
   -- this apn-oi-Replacement refers to the UE level apn-oi-Replacement.
}
 *
 * GPRSDataList ::= SEQUENCE SIZE (1..50) OF PDP-Context
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSSubscriptionData extends Serializable {

    boolean getCompleteDataListIncluded();

    ArrayList<PDPContext> getGPRSDataList();

    MAPExtensionContainer getExtensionContainer();

    APNOIReplacement getApnOiReplacement();

}
