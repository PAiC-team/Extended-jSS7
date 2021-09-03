package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface UserData extends Serializable {

    DataCodingScheme getDataCodingScheme();

    byte[] getEncodedData();

    boolean getEncodedUserDataHeaderIndicator();

    int getEncodedUserDataLength();

    String getDecodedMessage();

    UserDataHeader getDecodedUserDataHeader();

    void encode() throws MAPException;

    void decode() throws MAPException;

}