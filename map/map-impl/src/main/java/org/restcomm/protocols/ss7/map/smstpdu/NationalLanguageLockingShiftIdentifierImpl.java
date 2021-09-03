package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.datacoding.NationalLanguageIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.NationalLanguageLockingShiftIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.UserDataHeader;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class NationalLanguageLockingShiftIdentifierImpl extends Gsm7NationalLanguageIdentifierImpl implements NationalLanguageLockingShiftIdentifier {

    public NationalLanguageLockingShiftIdentifierImpl(NationalLanguageIdentifier nationalLanguageCode) {
        super(nationalLanguageCode);
    }

    public NationalLanguageLockingShiftIdentifierImpl(byte[] encodedInformationElementData) {
        super(encodedInformationElementData);
    }

    public int getEncodedInformationElementIdentifier() {
        return UserDataHeader._InformationElementIdentifier_NationalLanguageLockingShift;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NationalLanguageLockingShiftIdentifier [");
        sb.append("nationalLanguageCode=");
        sb.append(this.getNationalLanguageIdentifier());
        sb.append("]");

        return sb.toString();
    }
}
