
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 APN-ConfigurationProfile ::= SEQUENCE { defaultContext ContextId, completeDataListIncluded NULL OPTIONAL, -- If segmentation
 * is used, completeDataListIncluded may only be present in the -- first segment of APN-ConfigurationProfile. epsDataList [1]
 * EPS-DataList, extensionContainer [2] ExtensionContainer OPTIONAL, ... }
 *
 * ContextId ::= INTEGER (1..50)
 *
 * EPS-DataList ::= SEQUENCE SIZE (1..50) OF APN-Configuration
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface APNConfigurationProfile extends Serializable {

    int getDefaultContext();

    boolean getCompleteDataListIncluded();

    ArrayList<APNConfiguration> getEPSDataList();

    MAPExtensionContainer getExtensionContainer();

}
