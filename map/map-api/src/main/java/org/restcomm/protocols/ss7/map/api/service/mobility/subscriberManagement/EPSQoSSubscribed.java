
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 EPS-QoS-Subscribed ::= SEQUENCE { qos-Class-Identifier [0] QoS-Class-Identifier, allocation-Retention-Priority [1]
 * Allocation-Retention-Priority, extensionContainer [2] ExtensionContainer OPTIONAL, ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EPSQoSSubscribed extends Serializable {

    QoSClassIdentifier getQoSClassIdentifier();

    AllocationRetentionPriority getAllocationRetentionPriority();

    MAPExtensionContainer getExtensionContainer();

}
