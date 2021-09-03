
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 AoIPCodecsList ::= SEQUENCE { codec1 [1] AoIPCodec, codec2 [2] AoIPCodec OPTIONAL, codec3 [3] AoIPCodec OPTIONAL, codec4 [4]
 * AoIPCodec OPTIONAL, codec5 [5] AoIPCodec OPTIONAL, codec6 [6] AoIPCodec OPTIONAL, codec7 [7] AoIPCodec OPTIONAL, codec8 [8]
 * AoIPCodec OPTIONAL, extensionContainer [9] ExtensionContainer OPTIONAL, ...} -- Codecs are sent in priority order where
 * codec1 has highest priority
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AoIPCodecsList extends Serializable {

    AoIPCodec getCodec1();

    AoIPCodec getCodec2();

    AoIPCodec getCodec3();

    AoIPCodec getCodec4();

    AoIPCodec getCodec5();

    AoIPCodec getCodec6();

    AoIPCodec getCodec7();

    AoIPCodec getCodec8();

    MAPExtensionContainer getExtensionContainer();

}
