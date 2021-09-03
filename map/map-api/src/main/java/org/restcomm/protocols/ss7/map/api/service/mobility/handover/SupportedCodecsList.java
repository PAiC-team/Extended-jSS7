
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SupportedCodecsList ::= SEQUENCE { utranCodecList [0] CodecList OPTIONAL, geranCodecList [1] CodecList OPTIONAL,
 * extensionContainer [2] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SupportedCodecsList extends Serializable {

    CodecList getUtranCodecList();

    CodecList getGeranCodecList();

    MAPExtensionContainer getExtensionContainer();

}
