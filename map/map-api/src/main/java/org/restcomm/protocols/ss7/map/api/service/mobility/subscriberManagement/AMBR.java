
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
 AMBR ::= SEQUENCE {
   max-RequestedBandwidth-UL  [0] Bandwidth,
   max-RequestedBandwidth-DL  [1] Bandwidth,
   extensionContainer         [2] ExtensionContainer OPTIONAL,
   ...
}

Bandwidth ::= INTEGER
-- bits per second
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AMBR extends Serializable {

    int getMaxRequestedBandwidthUL();

    int getMaxRequestedBandwidthDL();

    MAPExtensionContainer getExtensionContainer();

}
