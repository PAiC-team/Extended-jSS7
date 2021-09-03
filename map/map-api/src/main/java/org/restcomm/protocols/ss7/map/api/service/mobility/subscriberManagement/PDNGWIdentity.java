
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 PDN-GW-Identity ::= SEQUENCE { pdn-gw-ipv4-Address [0] PDP-Address OPTIONAL, pdn-gw-ipv6-Address [1] PDP-Address OPTIONAL,
 * pdn-gw-name [2] FQDN OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PDNGWIdentity extends Serializable {

    PDPAddress getPdnGwIpv4Address();

    PDPAddress getPdnGwIpv6Address();

    FQDN getPdnGwName();

    MAPExtensionContainer getExtensionContainer();

}
