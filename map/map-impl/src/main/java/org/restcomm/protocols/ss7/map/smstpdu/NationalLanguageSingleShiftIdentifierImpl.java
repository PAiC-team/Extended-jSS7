package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.datacoding.NationalLanguageIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.NationalLanguageSingleShiftIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.UserDataHeader;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class NationalLanguageSingleShiftIdentifierImpl extends Gsm7NationalLanguageIdentifierImpl implements NationalLanguageSingleShiftIdentifier {

    public NationalLanguageSingleShiftIdentifierImpl(NationalLanguageIdentifier nationalLanguageCode) {
        super(nationalLanguageCode);
    }

    public NationalLanguageSingleShiftIdentifierImpl(byte[] encodedInformationElementData) {
        super(encodedInformationElementData);
    }

    public int getEncodedInformationElementIdentifier() {
        return UserDataHeader._InformationElementIdentifier_NationalLanguageSingleShift;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NationalLanguageSingleShiftIdentifier [");
        sb.append("nationalLanguageCode=");
        sb.append(this.getNationalLanguageIdentifier());
        sb.append("]");

        return sb.toString();
    }
}
