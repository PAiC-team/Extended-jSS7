package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 * User data header for concatenates SMS messages
 *
 * @author sergey vetyutnev
 *
 */
public interface ConcatenatedShortMessagesIdentifier extends UserDataHeaderElement {

    boolean getReferenceIs16bit();

    int getReference();

    int getMessageSegmentCount();

    int getMessageSegmentNumber();

    byte[] getEncodedInformationElementData();

}