
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 VoiceBroadcastData ::= SEQUENCE { groupid GroupId, -- groupId shall be filled with six TBCD fillers (1111)if the longGroupId
 * is present broadcastInitEntitlement NULL OPTIONAL, extensionContainer ExtensionContainer OPTIONAL, ..., longGroupId [0]
 * Long-GroupId OPTIONAL }
 *
 * -- VoiceBroadcastData containing a longGroupId shall not be sent to VLRs that did not -- indicate support of long Group IDs
 * within the Update Location or Restore Data -- request message
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface VoiceBroadcastData extends Serializable {

    GroupId getGroupId();

    boolean getBroadcastInitEntitlement();

    MAPExtensionContainer getExtensionContainer();

    LongGroupId getLongGroupId();

}
