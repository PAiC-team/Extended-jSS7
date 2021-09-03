
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 Allocation-Retention-Priority ::= SEQUENCE { priority-level [0] INTEGER, pre-emption-capability [1] BOOLEAN OPTIONAL,
 * pre-emption-vulnerability [2] BOOLEAN OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AllocationRetentionPriority extends Serializable {

    int getPriorityLevel();

    Boolean getPreEmptionCapability();

    Boolean getPreEmptionVulnerability();

    MAPExtensionContainer getExtensionContainer();

}
