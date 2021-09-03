package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface UserDataHeaderElement extends Serializable {

    int getEncodedInformationElementIdentifier();

    byte[] getEncodedInformationElementData();

}