
package org.restcomm.protocols.ss7.map.api.datacoding;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.smstpdu.CharacterSet;
import org.restcomm.protocols.ss7.map.api.smstpdu.DataCodingSchemaMessageClass;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CBSDataCodingScheme extends Serializable {

    int getCode();

    CBSDataCodingGroup getDataCodingGroup();

    CBSNationalLanguage getNationalLanguageShiftTable();

    CharacterSet getCharacterSet();

    DataCodingSchemaMessageClass getMessageClass();

    boolean getIsCompressed();

}
