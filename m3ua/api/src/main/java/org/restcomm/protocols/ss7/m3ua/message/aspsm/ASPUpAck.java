package org.restcomm.protocols.ss7.m3ua.message.aspsm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;

/**
 * The ASP UP Ack message is used to acknowledge an ASP Up message received from a remote M3UA peer. Both ASP Identifier and
 * INFO String are optional
 *
 * @author amit bhayani
 *
 */
public interface ASPUpAck extends M3UAMessage {

    ASPIdentifier getASPIdentifier();

    void setASPIdentifier(ASPIdentifier aspId);

    InfoString getInfoString();

    void setInfoString(InfoString infoString);
}
