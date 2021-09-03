package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface UserDataHeader extends Serializable {

    int _InformationElementIdentifier_ConcatenatedShortMessages8bit = 0x00;
    int _InformationElementIdentifier_ApplicationPortAddressingScheme16BitAddress = 0x05;
    int _InformationElementIdentifier_ConcatenatedShortMessages16bit = 0x08;
    int _InformationElementIdentifier_NationalLanguageSingleShift = 0x24;
    int _InformationElementIdentifier_NationalLanguageLockingShift = 0x25;

    byte[] getEncodedData();

    Map<Integer, byte[]> getAllData();

    void addInformationElement(int informationElementIdentifier, byte[] encodedData);

    void addInformationElement(UserDataHeaderElement userDataHeaderElement);

    byte[] getInformationElementData(int informationElementIdentifier);

    NationalLanguageLockingShiftIdentifier getNationalLanguageLockingShift();

    NationalLanguageSingleShiftIdentifier getNationalLanguageSingleShift();

    ConcatenatedShortMessagesIdentifier getConcatenatedShortMessagesIdentifier();

    ApplicationPortAddressing16BitAddress getApplicationPortAddressing16BitAddress();

}