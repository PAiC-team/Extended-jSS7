package org.restcomm.protocols.ss7.m3ua.message.aspsm;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;

/**
 * The ASP Down Ack message is used to acknowledge an ASP Down message received from a remote M3UA peer.
 *
 * @author amit bhayani
 *
 */
public interface ASPDownAck extends M3UAMessage {

    InfoString getInfoString();

    void setInfoString(InfoString infoString);

}
