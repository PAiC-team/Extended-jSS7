package org.restcomm.protocols.ss7.map.smstpdu;

import org.restcomm.protocols.ss7.map.api.datacoding.NationalLanguageIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.Gsm7NationalLanguageIdentifier;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class Gsm7NationalLanguageIdentifierImpl implements Gsm7NationalLanguageIdentifier {

    private NationalLanguageIdentifier nationalLanguageCode;

    public Gsm7NationalLanguageIdentifierImpl(NationalLanguageIdentifier nationalLanguageCode) {
        this.nationalLanguageCode = nationalLanguageCode;
    }

    public Gsm7NationalLanguageIdentifierImpl(byte[] encodedInformationElementData) {
        if (encodedInformationElementData != null && encodedInformationElementData.length > 0)
            this.nationalLanguageCode = NationalLanguageIdentifier.getInstance(encodedInformationElementData[0] & 0xFF);
    }

    public NationalLanguageIdentifier getNationalLanguageIdentifier() {
        return nationalLanguageCode;
    }

    public byte[] getEncodedInformationElementData() {
        return new byte[] { (byte) nationalLanguageCode.getCode() };
    }
}
