package org.restcomm.protocols.ss7.map.api.service.lsm;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;

/**
 * RoutingInfoForLCS-Res ::= SEQUENCE { targetMS [0] SubscriberIdentity, lcsLocationInfo [1] LCSLocationInfo, extensionContainer
 * [2] ExtensionContainer OPTIONAL, ..., v-gmlc-Address [3] GSN-Address OPTIONAL, h-gmlc-Address [4] GSN-Address OPTIONAL,
 * ppr-Address [5] GSN-Address OPTIONAL, additional-v-gmlc-Address [6] GSN-Address OPTIONAL }
 *
 *
 * @author amit bhayani
 *
 */
public interface SendRoutingInfoForLCSResponse extends LsmMessage {

    SubscriberIdentity getTargetMS();

    LCSLocationInfo getLCSLocationInfo();

    MAPExtensionContainer getExtensionContainer();

    GSNAddress getVgmlcAddress();

    GSNAddress getHGmlcAddress();

    GSNAddress getPprAddress();

    GSNAddress getAdditionalVGmlcAddress();

}
