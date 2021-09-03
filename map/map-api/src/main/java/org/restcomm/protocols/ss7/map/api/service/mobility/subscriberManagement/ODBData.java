
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 ODB-Data ::= SEQUENCE { odb-GeneralData ODB-GeneralData, odb-HPLMN-Data ODB-HPLMN-Data OPTIONAL, extensionContainer
 * ExtensionContainer OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ODBData extends Serializable {

    ODBGeneralData getODBGeneralData();

    ODBHPLMNData getOdbHplmnData();

    MAPExtensionContainer getExtensionContainer();

}
