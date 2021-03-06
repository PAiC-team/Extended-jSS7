
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.AccessNetworkSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.restcomm.protocols.ss7.map.api.service.oam.TracePropagationList;

/**
 *
 MAP V1-2-3:
 *
 * MAP V3: forwardAccessSignalling OPERATION ::= { --Timer s ARGUMENT ForwardAccessSignalling-Arg CODE local:34 }
 *
 * MAP V2: ForwardAccessSignalling ::= OPERATION--Timer s ARGUMENT bss-APDU ExternalSignalInfo
 *
 *
 * MAP V3: ForwardAccessSignalling-Arg ::= [3] SEQUENCE { an-APDU AccessNetworkSignalInfo, integrityProtectionInfo [0]
 * IntegrityProtectionInformation OPTIONAL, encryptionInfo [1] EncryptionInformation OPTIONAL, keyStatus [2] KeyStatus OPTIONAL,
 * allowedGSM-Algorithms [4] AllowedGSM-Algorithms OPTIONAL, allowedUMTS-Algorithms [5] AllowedUMTS-Algorithms OPTIONAL,
 * radioResourceInformation [6] RadioResourceInformation OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ...,
 * radioResourceList [7] RadioResourceList OPTIONAL, bssmap-ServiceHandover [9] BSSMAP-ServiceHandover OPTIONAL,
 * ranap-ServiceHandover [8] RANAP-ServiceHandover OPTIONAL, bssmap-ServiceHandoverList [10] BSSMAP-ServiceHandoverList
 * OPTIONAL, currentlyUsedCodec [11] Codec OPTIONAL, iuSupportedCodecsList [12] SupportedCodecsList OPTIONAL,
 * rab-ConfigurationIndicator [13] NULL OPTIONAL, iuSelectedCodec [14] Codec OPTIONAL, alternativeChannelType [15]
 * RadioResourceInformation OPTIONAL, tracePropagationList [17] TracePropagationList OPTIONAL, aoipSupportedCodecsListAnchor
 * [18] AoIPCodecsList OPTIONAL, aoipSelectedCodecTarget [19] AoIPCodec OPTIONAL }
 *
 * RadioResourceList ::= SEQUENCE SIZE (1..7) OF RadioResource
 *
 * BSSMAP-ServiceHandoverList ::= SEQUENCE SIZE (1..7) OF BSSMAP-ServiceHandoverInfo
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ForwardAccessSignallingRequest extends MobilityMessage {

    AccessNetworkSignalInfo getAnApdu();

    IntegrityProtectionInformation getIntegrityProtectionInfo();

    EncryptionInformation getEncryptionInfo();

    KeyStatus getKeyStatus();

    AllowedGSMAlgorithms getAllowedGSMAlgorithms();

    AllowedUMTSAlgorithms getAllowedUMTSAlgorithms();

    RadioResourceInformation getRadioResourceInformation();

    MAPExtensionContainer getExtensionContainer();

    ArrayList<RadioResource> getRadioResourceList();

    BSSMAPServiceHandover getBSSMAPServiceHandover();

    RANAPServiceHandover getRANAPServiceHandover();

    ArrayList<BSSMAPServiceHandoverInfo> getBSSMAPServiceHandoverList();

    Codec getCurrentlyUsedCodec();

    SupportedCodecsList getIuSupportedCodecsList();

    boolean getRabConfigurationIndicator();

    Codec getIuSelectedCodec();

    RadioResourceInformation getAlternativeChannelType();

    TracePropagationList getTracePropagationList();

    AoIPCodecsList getAoipSupportedCodecsListAnchor();

    AoIPCodec getAoipSelectedCodecTarget();

    // for MAP V1-2 only
    ExternalSignalInfo getBssAPDU();

}
