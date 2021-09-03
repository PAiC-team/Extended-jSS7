package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CommandData extends Serializable {

    byte[] getEncodedData();

    String getDecodedMessage();

    void encode() throws MAPException;

    void decode() throws MAPException;

}