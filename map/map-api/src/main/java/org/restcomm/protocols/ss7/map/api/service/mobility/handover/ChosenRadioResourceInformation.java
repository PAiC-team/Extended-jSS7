
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 ChosenRadioResourceInformation ::= SEQUENCE { chosenChannelInfo [0] ChosenChannelInfo OPTIONAL, chosenSpeechVersion [1]
 * ChosenSpeechVersion OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChosenRadioResourceInformation extends Serializable {

    ChosenChannelInfo getChosenChannelInfo();

    ChosenSpeechVersion getChosenSpeechVersion();

}
