
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APN;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDNGWIdentity;

/**
 *
 PDN-GW-Update ::= SEQUENCE{ apn [0] APN OPTIONAL, pdn-gw-Identity [1] PDN-GW-Identity OPTIONAL, contextId [2] ContextId
 * OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ... } -- The pdn-gw-update IE shall include the
 * pdn-gw-Identity, and the apn or/and the contextID. -- The HSS shall ignore the eps-info IE if it includes a pdn-gw-update IE
 * which does not -- include pdn-gw-Identity. -- The pdn-gw-Identity is defined as OPTIONAL for backward compatility reason with
 * -- outdated earlier versions of this specification.
 *
 * ContextId ::= INTEGER (1..50)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDNGWUpdate extends Serializable {

    APN getAPN();

    PDNGWIdentity getPdnGwIdentity();

    Integer getContextId();

    MAPExtensionContainer getExtensionContainer();

}
