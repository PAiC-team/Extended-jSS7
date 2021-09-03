
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
LSAInformation ::= SEQUENCE {
  completeDataListIncluded   NULL OPTIONAL,
  -- If segmentation is used, completeDataListIncluded may only be present in the
  -- first segment.
  lsaOnlyAccessIndicator     [1] LSAOnlyAccessIndicator OPTIONAL,
  lsaDataList                [2] LSADataList OPTIONAL,
  extensionContainer         [3] ExtensionContainer OPTIONAL,
  ...
}

LSADataList ::= SEQUENCE SIZE (1..20) OF LSAData
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LSAInformation extends Serializable {

    boolean getCompleteDataListIncluded();

    LSAOnlyAccessIndicator getLSAOnlyAccessIndicator();

    ArrayList<LSAData> getLSADataList();

    MAPExtensionContainer getExtensionContainer();

}
