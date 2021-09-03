
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.ASCICallReference;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;

/**
 *
 MAP V3:
 *
 * prepareGroupCall OPERATION ::= { --Timer m ARGUMENT PrepareGroupCallArg RESULT PrepareGroupCallRes ERRORS { systemFailure |
 * noGroupCallNumberAvailable | unexpectedDataValue} CODE local:39 }
 *
 * PrepareGroupCallArg ::= SEQUENCE { teleservice Ext-TeleserviceCode, asciCallReference ASCI-CallReference, codec-Info
 * CODEC-Info, cipheringAlgorithm CipheringAlgorithm, groupKeyNumber-Vk-Id [0] GroupKeyNumber OPTIONAL, groupKey [1] Kc
 * OPTIONAL, -- this parameter shall not be sent and shall be discarded if received priority [2] EMLPP-Priority OPTIONAL,
 * uplinkFree [3] NULL OPTIONAL, extensionContainer [4] ExtensionContainer OPTIONAL, ..., vstk [5] VSTK OPTIONAL, vstk-rand [6]
 * VSTK-RAND OPTIONAL, talkerChannelParameter [7] NULL OPTIONAL, uplinkReplyIndicator [8] NULL OPTIONAL}
 *
 * GroupKeyNumber ::= INTEGER (0..15)
 *
 * Kc ::= OCTET STRING (SIZE (8))
 *
 * VSTK ::= OCTET STRING (SIZE (16))
 *
 * VSTK-RAND ::= OCTET STRING (SIZE (5)) -- The 36 bit value is carried in bit 7 of octet 1 to bit 4 of octet 5 -- bits 3, 2, 1,
 * and 0 of octet 5 are padded with zeros.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PrepareGroupCallRequest extends CallHandlingMessage {

     ExtTeleserviceCode getTeleservice();

     ASCICallReference getAsciCallReference();

     CODECInfo getCodecInfo();

     CipheringAlgorithm getCipheringAlgorithm();

     Integer getGroupKeyNumberVkId();

     byte[] getGroupKey();

     EMLPPPriority getPriority();

     boolean getUplinkFree();

     MAPExtensionContainer getExtensionContainer();

     byte[] getVstk();

     byte[] getVstkRand();

     boolean getTalkerChannelParameter();

     boolean getUplinkReplyIndicator();

}
