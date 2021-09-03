
package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

/**
 * See 3GPP TS 23.038 Chapter 4 SMS Data Coding Scheme
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DataCodingScheme extends Serializable {

    int getCode();

    DataCodingGroup getDataCodingGroup();

    DataCodingSchemaMessageClass getMessageClass();

    DataCodingSchemaIndicationType getDataCodingSchemaIndicationType();

    Boolean getSetIndicationActive();

    CharacterSet getCharacterSet();

    boolean getIsCompressed();

}